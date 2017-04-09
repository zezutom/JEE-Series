package org.zezutom.web.jsf.ajax.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade<T> {

    private static final int MIN_SEARCH_LENGTH = 2;
    
    private static final String GETTER_PREFIX = "get";
    
    private static final String SEARCH_TEMPLATE = "%%%s%%";
    
    private final Class<T> entityClass;

    // Needed for fuzzy search
    private final List<String> searcheableFields;
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
        searcheableFields = Arrays.asList(entityClass.getMethods())
                .stream()
                .filter(method -> method.getName().startsWith(GETTER_PREFIX))
                .filter(method -> method.getReturnType().isAssignableFrom(String.class))
                .filter(method -> !method.getName().matches("getClass|getId"))
                .map(method -> {
                    // Convert a getter into a field name, i.e. lower-case the 1st letter
                    final String fieldName = method.getName().replaceFirst(GETTER_PREFIX, "");                    
                    return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
                })
                .filter(fieldName -> !fieldName.isEmpty())
                .collect(Collectors.toList());
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

    public List<T> search(String searchText) {
        
        if (searchText == null) return Collections.emptyList();
     
        // TODO sanitize
        final String sanitizedSearchText = searchText.trim().toLowerCase();
        if (sanitizedSearchText.length() < MIN_SEARCH_LENGTH) 
            return Collections.emptyList();
        
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = criteriaBuilder.createQuery();
        final Root from = cq.from(entityClass);        
        
        final List<Predicate> predicates = new ArrayList<>();
        searcheableFields
                .stream()
                .forEach(field -> { 
                        Predicate p = criteriaBuilder.like(
                                        criteriaBuilder.lower(
                                                criteriaBuilder.concat(from.get(field), "")                                                
                                        ),
                                        String.format(SEARCH_TEMPLATE, sanitizedSearchText));
                        predicates.add(p);
                });
        Predicate p = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(cq.where(p)).getResultList();
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
