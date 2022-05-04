package com.example.demoatm.repository;

import com.example.demoatm.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByActiveTrue();

}
