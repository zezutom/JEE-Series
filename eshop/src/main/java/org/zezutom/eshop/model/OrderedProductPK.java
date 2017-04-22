package org.zezutom.eshop.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tom
 */
@Embeddable
public class OrderedProductPK implements Serializable {

    @NotNull
    @Column(name = "product_id")
    private int productId;

    @NotNull
    @Column(name = "customer_order_id")
    private int customerOrderId;

    public OrderedProductPK() {
    }

    public OrderedProductPK(int productId, int customerOrderId) {
        this.productId = productId;
        this.customerOrderId = customerOrderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productId;
        hash += (int) customerOrderId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedProductPK)) {
            return false;
        }
        OrderedProductPK other = (OrderedProductPK) object;
        if (this.productId != other.productId) {
            return false;
        }
        return this.customerOrderId == other.customerOrderId;
    }

    @Override
    public String toString() {
        return "org.zezutom.eshop.model.OrderedProductPK[ productId=" + productId + ", customerOrderId=" + customerOrderId + " ]";
    }

}
