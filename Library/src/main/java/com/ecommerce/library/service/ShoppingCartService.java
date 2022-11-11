package com.ecommerce.library.service;

import com.ecommerce.library.dto.ShoppingCartDto;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Product product, int quantity, Customer customer);

    ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);



}
