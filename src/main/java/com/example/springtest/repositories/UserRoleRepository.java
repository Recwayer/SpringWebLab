package com.example.springtest.repositories;

import com.example.springtest.models.UserRole;
import com.example.springtest.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findUserRoleByRole(Role role);
}
