package com.example.springApi.repository;

import com.example.springApi.domain.member.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
    boolean existsByName(String roleName);
}
