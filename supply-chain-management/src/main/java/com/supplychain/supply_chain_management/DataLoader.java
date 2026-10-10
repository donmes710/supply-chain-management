package com.supplychain.supply_chain_management;

import com.supplychain.supply_chain_management.entity.Role;
import com.supplychain.supply_chain_management.entity.User;
import com.supplychain.supply_chain_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist to avoid creating them on every startup
        if (userRepository.count() == 0) {
            System.out.println("Creating initial users...");

            // Create an ADMIN user
            User admin = new User();
            admin.setUsername("admin");
            // Set the password by ENCRYPTING it first
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setEmail("admin@example.com");
            admin.setRoles(Set.of(Role.ROLE_ADMIN, Role.ROLE_MANAGER));

            userRepository.save(admin);

            // Create a MANAGER user
            User manager = new User();
            manager.setUsername("manager");
            manager.setPassword(passwordEncoder.encode("managerpass"));
            manager.setEmail("manager@example.com");
            manager.setRoles(Set.of(Role.ROLE_MANAGER));

            userRepository.save(manager);
            
            System.out.println("Initial users created.");
        }
    }
}