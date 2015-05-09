package org.zezutom.blog.series.jee.ajaxrest.web;

import org.zezutom.blog.series.jee.ajaxrest.service.ComposerService;
import org.zezutom.blog.series.jee.ajaxrest.model.Composer;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tom
 */
@Path("composers")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComposerResource {
    
    @Inject
    private ComposerService service;
    
    @GET
    public List<Composer> composers() {
        return service.getComposers();
    }
}
