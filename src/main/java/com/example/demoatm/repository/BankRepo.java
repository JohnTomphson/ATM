package com.example.demoatm.repository;

import com.example.demoatm.models.Bank;
import com.example.demoatm.projections.BankPro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepo extends JpaRepository<Bank, Long> {
    List<BankPro> findAllByActiveTrue();

}
