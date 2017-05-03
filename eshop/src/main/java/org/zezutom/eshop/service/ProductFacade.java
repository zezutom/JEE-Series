package org.zezutom.eshop.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.zezutom.eshop.model.Product;

@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    public List<Product> findAll(int categoryId) {
        return em.createNamedQuery("Product.findByCategoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }
}
