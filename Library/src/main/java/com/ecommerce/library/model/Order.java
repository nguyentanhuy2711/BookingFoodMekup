package com.ecommerce.library.model;

import com.ecommerce.library.Enum.EnumStatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private double shippingFee;
    private String notes;

    @Column(name= "statusOrder")
    private EnumStatusOrder statusOrder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetailList;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="store_id", referencedColumnName = "store_id")
    private Store store;


}
