package com.example.demoatm.repository;

import com.example.demoatm.models.Atm;
import com.example.demoatm.projections.AtmPro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtmRepo extends JpaRepository<Atm,Long> {
  List<AtmPro>findAllByActiveTrue();
}
