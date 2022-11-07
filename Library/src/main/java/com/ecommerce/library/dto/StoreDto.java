package com.ecommerce.library.dto;

import com.ecommerce.library.model.Order;
import com.ecommerce.library.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
    private Product product;
    private Order order;

}
