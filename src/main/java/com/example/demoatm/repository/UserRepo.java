package com.example.demoatm.repository;

import com.example.demoatm.models.Users;
import com.example.demoatm.projections.UserPro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Users,Long> {
    List<UserPro> findAllByActiveTrue();

}
