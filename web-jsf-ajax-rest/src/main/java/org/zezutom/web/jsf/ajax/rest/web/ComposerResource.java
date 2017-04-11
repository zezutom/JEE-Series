package org.zezutom.web.jsf.ajax.rest.web;

import java.util.Collections;
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
import javax.ws.rs.QueryParam;
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
    @Path("/list")
    public void list(
            @Suspended AsyncResponse asyncResponse,
            @QueryParam("page") int page) {
        int offset = page * PAGE_SIZE;
        int[] range = new int[]{offset, offset + PAGE_SIZE - 1};           
        Runnable runnable = getAsyncResponse(
                composerFacade.findRange(range), 
                asyncResponse);
        executorService.submit(runnable);
    }

    @GET
    @Path("/filter")
    public void filter(
            @Suspended AsyncResponse asyncResponse,
            @QueryParam("query") String query) {
        Runnable runnable = getAsyncResponse(
                composerFacade.findAll(query), 
                asyncResponse);
        executorService.submit(runnable);
    }
    
    @GET
    @Path("{id}")
    public void findOne(
            @Suspended AsyncResponse asyncResponse,
            @PathParam("id") Long id) {
        Runnable runnable = getAsyncResponse(
                Collections.singletonList(composerFacade.find(id)), 
                asyncResponse);
        executorService.submit(runnable);        
    }
        
    private Runnable getAsyncResponse(List<Composer> composers, AsyncResponse asyncResponse) {
        Runnable runnable = () -> {
            JsonArray json = composers
                    .stream()
                    .map(c -> Json.createObjectBuilder()
                    .add("id", c.getId())
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
        return runnable;
    }
}
