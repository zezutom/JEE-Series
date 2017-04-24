package org.zezutom.eshop.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.zezutom.eshop.model.Category;
import org.zezutom.eshop.service.CategoryFacade;

@Path("categories")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    
    @EJB
    private CategoryFacade categoryFacade;
        
    @GET
    public List<Category> list() {
        return categoryFacade.findAll();
    }

}
