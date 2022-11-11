package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.CartItemDto;
import com.ecommerce.library.model.CartItem;
import com.ecommerce.library.repository.CartItemRepository;
import com.ecommerce.library.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public List<CartItemDto> getAllCartItem1(Long shoppingCartId){
        List<CartItemDto> cartItemDtos = new ArrayList<>();
     List<CartItem> cartItems = cartItemRepository.getAllCartItem(shoppingCartId);
         for(CartItem cartItem: cartItems){
             CartItemDto cartItemDto = new CartItemDto();
             cartItemDto.setId(cartItem.getId());
             cartItemDto.setQuantity(cartItem.getQuantity());
             cartItemDto.setImage(cartItem.getProduct().getImage());
             cartItemDto.setProductName(cartItem.getProduct().getName());
             cartItemDto.setTotalPrice(cartItem.getTotalPrice());
             cartItemDtos.add(cartItemDto);
         }
    return cartItemDtos;
    }
}
