package org.zezutom.jeeseries.webjsf.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.zezutom.jeeseries.webjsf.model.Message;

@Stateless
public class MessageFacade extends AbstractFacade<Message> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
}
