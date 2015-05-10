package org.zezutom.blog.series.jee.ajaxrest.web;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.zezutom.blog.series.jee.ajaxrest.model.Genre;

/**
 *
 * @author tom
 */
@Path("genres")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenreResource {
        
    @GET
    public List<Genre> genres() {
        return Arrays.asList(Genre.values());
    }
}
