package com.example.demo.infrastructure;

import com.example.demo.domain.Role;
import com.example.demo.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
