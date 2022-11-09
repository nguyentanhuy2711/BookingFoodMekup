package com.ecommerce.library.service;

import com.ecommerce.library.dto.OrderDto;
import com.ecommerce.library.model.Order;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAllByCustomerId (long customerId);
}
