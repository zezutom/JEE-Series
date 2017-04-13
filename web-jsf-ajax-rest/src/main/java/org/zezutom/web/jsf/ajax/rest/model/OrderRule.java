package org.zezutom.web.jsf.ajax.rest.model;

public class OrderRule {
    
    private final String field;
    
    private final boolean asc;

    public OrderRule(String field, boolean asc) {
        this.field = field;
        this.asc = asc;
    }

    public String getField() {
        return field;
    }

    public boolean isAsc() {
        return asc;
    }
   
}
