package com.ecommerce.library.repository;

import com.ecommerce.library.dto.CartItemDto;
import com.ecommerce.library.model.CartItem;
import com.ecommerce.library.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "select  * \n" +
            "FROM ecommerce_springboot.cart_item CI INNER JOIN ecommerce_springboot.products P INNER JOIN ecommerce_springboot.shopping_cart S\n" +
            "ON CI.product_id = P.product_id AND S.shopping_cart_id = CI.shopping_cart_id\n" +
            "WHERE S.shopping_cart_id = :shoppingCartId",nativeQuery = true)
    List<CartItem> getAllCartItem(Long shoppingCartId);

    @Query(value = "select new com.ecommerce.library.dto.CartItemDto( CI.id, P.image, P.name,  CI.quantity, CI.totalPrice) \n" +
            "FROM CartItem CI INNER JOIN Product P INNER JOIN ShoppingCart S\n" +
            "ON CI.product.id = P.id AND S.id = CI.shoppingCart.id \n" +
            "WHERE S.id = :shoppingCartId",nativeQuery = false)
    List<CartItemDto> getAllCartItem2(Long shoppingCartId);
}
