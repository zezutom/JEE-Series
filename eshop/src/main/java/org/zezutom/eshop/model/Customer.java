package org.zezutom.eshop.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "full_name")
    private String name;
    
    private String email;
    
    private String phone;
    
    private String address;
    
    private String cityRegion;
    
    private String ccNumber;
    
    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrders;
}
