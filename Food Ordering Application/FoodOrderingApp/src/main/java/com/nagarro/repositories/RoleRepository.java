package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
