package com.example.springtest;

import com.example.springtest.models.UserRole;
import com.example.springtest.models.enums.Role;
import com.example.springtest.repositories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRoleRepository userRoleRepository;

    public DataInitializer(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) {
        initRoles();
    }
    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new UserRole(Role.ADMIN);
            var normalUserRole = new UserRole(Role.USER);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }
}
