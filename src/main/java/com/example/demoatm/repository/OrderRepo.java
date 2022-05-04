package com.example.demoatm.repository;

import com.example.demoatm.models.Order;
import com.example.demoatm.projections.OrderPro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    List<OrderPro> findAllByActiveTrue();
}
