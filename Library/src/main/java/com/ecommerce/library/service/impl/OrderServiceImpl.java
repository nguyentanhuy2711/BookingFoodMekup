package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.OrderDto;
import com.ecommerce.library.model.Order;
import com.ecommerce.library.repository.OrderRepository;
import com.ecommerce.library.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public List<OrderDto> findAllByCustomerId (long customerId){
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByUserId(customerId);
        for(Order order:orders){
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getId());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setDeliveryDate(order.getDeliveryDate());
            orderDto.setStatusOrder(order.getStatusOrder());
            orderDto.setNotes(order.getNotes());
            orderDto.setShippingFee(order.getShippingFee());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setCustomerId(order.getCustomer().getId());
            orderDto.setStoreId(order.getStore().getId());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}
