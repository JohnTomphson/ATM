package com.example.demoatm.repository;

import com.example.demoatm.models.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepo extends JpaRepository<Addresses,Long> {
    List<Addresses> findAllByActiveTrue();
  Optional<Addresses> findAddressesByZip_code(Integer zipCode);
}
