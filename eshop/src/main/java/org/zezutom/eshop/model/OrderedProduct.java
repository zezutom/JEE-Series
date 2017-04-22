package org.zezutom.eshop.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "ordered_product")
@NamedQueries({
    @NamedQuery(name = "OrderedProduct.findAll", query = "SELECT o FROM OrderedProduct o")
    , @NamedQuery(name = "OrderedProduct.findByProductId", query = "SELECT o FROM OrderedProduct o WHERE o.orderedProductPK.productId = :productId")
    , @NamedQuery(name = "OrderedProduct.findByCustomerOrderId", query = "SELECT o FROM OrderedProduct o WHERE o.orderedProductPK.customerOrderId = :customerOrderId")
    , @NamedQuery(name = "OrderedProduct.findByQuantity", query = "SELECT o FROM OrderedProduct o WHERE o.quantity = :quantity")})
public class OrderedProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected OrderedProductPK orderedProductPK;
    
    @NotNull
    private short quantity;
    
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomerOrder customerOrder;
    
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public OrderedProduct() {
    }

    public OrderedProduct(OrderedProductPK orderedProductPK) {
        this.orderedProductPK = orderedProductPK;
    }

    public OrderedProduct(OrderedProductPK orderedProductPK, short quantity) {
        this.orderedProductPK = orderedProductPK;
        this.quantity = quantity;
    }

    public OrderedProduct(int productId, int customerOrderId) {
        this.orderedProductPK = new OrderedProductPK(productId, customerOrderId);
    }

    public OrderedProductPK getOrderedProductPK() {
        return orderedProductPK;
    }

    public void setOrderedProductPK(OrderedProductPK orderedProductPK) {
        this.orderedProductPK = orderedProductPK;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderedProductPK != null ? orderedProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedProduct)) {
            return false;
        }
        OrderedProduct other = (OrderedProduct) object;
        return !((this.orderedProductPK == null && other.orderedProductPK != null) || (this.orderedProductPK != null && !this.orderedProductPK.equals(other.orderedProductPK)));
    }

    @Override
    public String toString() {
        return "org.zezutom.eshop.model.OrderedProduct[ orderedProductPK=" + orderedProductPK + " ]";
    }

}
