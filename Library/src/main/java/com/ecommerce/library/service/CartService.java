package com.ecommerce.library.service;

import com.ecommerce.library.dto.CartItemDto;

import java.util.List;

public interface CartService {

    List<CartItemDto> getAllCartItem1(Long shoppingCartId);
}
