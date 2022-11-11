package com.ecommerce.library.repository;

import com.ecommerce.library.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query(value = "select  CI.order_detail_id, CI.quantity, CI.total_price, P.name, P.image\n" +
            "FROM ecommerce_springboot.cart_item CI INNER JOIN ecommerce_springboot.products P INNER JOIN ecommerce_springboot.shopping_cart S\n" +
            "ON CI.product_id = P.product_id AND S.shopping_cart_id = CI.shopping_cart_id\n" +
            "WHERE S.shopping_cart_id = :shoppingCartId",nativeQuery = true)
    List<ShoppingCart> getAllByShoppingCart(Long shoppingCartId);

}
