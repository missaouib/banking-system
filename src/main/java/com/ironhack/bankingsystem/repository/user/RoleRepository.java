package com.ironhack.bankingsystem.repository.user;


import com.ironhack.bankingsystem.model.user.Role;
import com.ironhack.bankingsystem.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
