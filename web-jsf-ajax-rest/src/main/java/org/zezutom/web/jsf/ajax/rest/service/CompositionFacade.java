package org.zezutom.web.jsf.ajax.rest.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.zezutom.web.jsf.ajax.rest.model.Composition;

@Stateless
public class CompositionFacade extends AbstractFacade<Composition> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompositionFacade() {
        super(Composition.class);
    }
    
}
