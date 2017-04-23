package org.zezutom.eshop.web;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.zezutom.eshop.service.CategoryFacade;

@Path("categories")
@RequestScoped
public class CategoryResource {
    
    @EJB
    private CategoryFacade categoryFacade;
    
    @Resource
    private ManagedExecutorService executorService;
    
    @GET
    public void findAll(@Suspended AsyncResponse asyncResponse) {
        Runnable runnable = () -> {
            JsonArray jsonArray = categoryFacade.findAll().stream()
                    .map(c -> Json.createObjectBuilder()
                            .add("id", c.getId())
                            .add("name", c.getName())
                            .build())
                    .collect(
                            Json::createArrayBuilder,
                            JsonArrayBuilder::add,
                            JsonArrayBuilder::add)
                    .build();
            Response response = Response.ok(jsonArray).build();
            asyncResponse.resume(response);            
        };
        executorService.submit(runnable);
    }

}
