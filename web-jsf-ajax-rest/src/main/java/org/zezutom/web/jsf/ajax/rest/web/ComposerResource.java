package org.zezutom.web.jsf.ajax.rest.web;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.zezutom.web.jsf.ajax.rest.model.Composer;
import org.zezutom.web.jsf.ajax.rest.service.ComposerFacade;

@Path("composers")
@RequestScoped
public class ComposerResource {

    public static final int PAGE_SIZE = 10;
    
    @EJB
    private ComposerFacade composerFacade;
    
    @Resource
    private ManagedExecutorService executorService;
        
    @GET
    @Path("{page}")    
    public void composers(
            @Suspended AsyncResponse asyncResponse, 
            @PathParam("page") Integer page) {
        
        Runnable runnable = () -> {
            int offset = page * PAGE_SIZE;
            List<Composer> composers = composerFacade.findRange(
                    new int[] {offset, offset + PAGE_SIZE - 1});        
            JsonArray json = composers
                    .stream()
                    .map(c -> Json.createObjectBuilder()
                            .add("firstName", c.getFirstName())
                            .add("lastName", c.getLastName())
                            .add("genre", c.getGenre())
                            .add("thumbnail", c.getThumbnail())
                            .add("born", c.getBorn())
                            .add("died", c.getDied())
                            .add("compositions", c.getCompositions()
                                    .stream()
                                    .map(cp -> Json.createObjectBuilder()
                                            .add("name", cp.getName()))
                                    .collect(
                                            Json::createArrayBuilder, 
                                            JsonArrayBuilder::add, 
                                            JsonArrayBuilder::add)
                                    .build())
                    .build())
                    .collect(
                            Json::createArrayBuilder, 
                            JsonArrayBuilder::add, 
                            JsonArrayBuilder::add)
                    .build();
            Response response = Response.ok(json).build();
            asyncResponse.resume(response);
        };
        executorService.submit(runnable);
    }   
}
