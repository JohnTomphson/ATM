package com.example.demoatm.repository;

import com.example.demoatm.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
    List<Payment> findAllByActiveTrue();

}
