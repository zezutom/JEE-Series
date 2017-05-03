package org.zezutom.eshop.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.zezutom.eshop.model.Product;
import org.zezutom.eshop.service.ProductFacade;

@Path("products")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    
    @EJB
    private ProductFacade productFacade;
    
    @GET
    public List<Product> findAll(@QueryParam("categoryId") int categoryId) {
        List<Product> products = productFacade.findAll(categoryId);
        return products;
    }

}
