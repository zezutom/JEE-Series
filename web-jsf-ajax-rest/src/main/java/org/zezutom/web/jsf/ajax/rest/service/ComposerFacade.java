package org.zezutom.web.jsf.ajax.rest.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.zezutom.web.jsf.ajax.rest.model.Composer;

@Stateless
public class ComposerFacade extends AbstractFacade<Composer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComposerFacade() {
        super(Composer.class);
    }
    
    public List<Composer> findAll(String filter) {
        if (filter == null || filter.isEmpty())
            return super.findAll();
        else
            return getEntityManager()
                    .createNamedQuery(Composer.SEARCH_QUERY_NAME)
                    .setParameter("searchText", filter)
                    .getResultList();
    }
    
}
