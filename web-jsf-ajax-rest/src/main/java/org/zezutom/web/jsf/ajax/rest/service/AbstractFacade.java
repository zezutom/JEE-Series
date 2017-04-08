package org.zezutom.web.jsf.ajax.rest.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll(String... orderBy) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = criteriaBuilder.createQuery();
        final Root from = cq.from(entityClass);
        cq.select(from);
        orderBy(orderBy, criteriaBuilder, from, cq);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range, String... orderBy) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = criteriaBuilder.createQuery();
        final Root from = cq.from(entityClass);
        cq.select(from);
        orderBy(orderBy, criteriaBuilder, from, cq);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private void orderBy(String[] orderBy, final CriteriaBuilder criteriaBuilder, final Root from, CriteriaQuery cq) {
        if (orderBy != null && orderBy.length > 0) {
            final List<Order> orderByExprs = Arrays.asList(orderBy)
                    .stream().map(field -> criteriaBuilder.asc(from.get(field)))
                    .collect(Collectors.toList());
            cq.orderBy(orderByExprs);
        }
    }
}
