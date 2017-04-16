package org.zezutom.web.jsf.ajax.rest.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.zezutom.web.jsf.ajax.rest.model.OrderRule;

public abstract class AbstractFacade<T> {
    
    public static final int MIN_QUERY_LENGTH = 2;
    
    private final Class<T> entityClass;
    
    List<String> searcheableFields() {
        return Collections.emptyList();
    }
    
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

    public List<T> findAll() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = criteriaBuilder.createQuery();
        final Root from = cq.from(entityClass);
        cq.select(from);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        return findRange(range, null, Collections.emptyList());
    }

    public List<T> findRange(int[] range, String query) {
        return findRange(range, query, Collections.emptyList());
    }
    
    public List<T> findRange(int[] range, String query, List<OrderRule> orderRules) {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        final Root from = cq.from(entityClass);
        cq.select(from);
        
        final List<String> searcheableFields = searcheableFields();
        if (isValid(query) && isValid(searcheableFields)) {
            final String pattern = "%" + query.toLowerCase().trim() + "%";
            
            List<Predicate> predicates = (List<Predicate>) searcheableFields
                    .stream()
                    .map(f -> cb.like(cb.lower(from.get(f)), pattern))
                    .collect(Collectors.toList());
            
            cq = cq.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        }

        if (isValid(orderRules)) {
            List<Order> orderList = orderRules.stream().map(rule -> {
                Path field = from.get(rule.getField());
                return rule.isAsc() ? cb.asc(field) : cb.desc(field);
            }).collect(Collectors.toList());
            cq.orderBy(orderList);
        }
       
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
    
    private boolean isValid(String query) {
        return query != null && query.trim().length() >= MIN_QUERY_LENGTH;
    }
    
    private<V> boolean isValid(List<V> list) {
        return list != null && !list.isEmpty();
    }
}
