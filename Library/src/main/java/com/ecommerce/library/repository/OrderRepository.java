package com.ecommerce.library.repository;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT * FROM ecommerce_springboot.orders where customer_id = :customerId order by order_id desc ", nativeQuery = true)
    List<Order> findAllByUserId(Long customerId);
}
