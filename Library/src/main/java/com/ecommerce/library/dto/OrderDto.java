package com.ecommerce.library.dto;

import com.ecommerce.library.Enum.EnumStatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private double shippingFee;
    private String notes;
    private Long customerId;
    private Long storeId;
    private EnumStatusOrder statusOrder;
}
