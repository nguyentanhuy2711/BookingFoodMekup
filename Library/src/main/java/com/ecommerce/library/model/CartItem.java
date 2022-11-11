package com.ecommerce.library.model;

import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;
    private int quantity;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
