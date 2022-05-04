package com.example.demoatm.repository;

import com.example.demoatm.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    List<Invoice> findAllByActiveTrue();

}
