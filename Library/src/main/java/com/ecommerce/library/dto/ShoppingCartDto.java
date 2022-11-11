package com.ecommerce.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private String image;
    private String productName;
    private int quantity;
    private Double totalPrice;
}
