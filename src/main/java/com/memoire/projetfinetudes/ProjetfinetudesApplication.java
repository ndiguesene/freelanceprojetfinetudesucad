package com.memoire.projetfinetudes;

import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetfinetudesApplication implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjetfinetudesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_ADMIN"));
        roles.add(new Role("ROLE_CANDIDAT"));
        roles.add(new Role("ROLE_RECRUTEUR"));
        roles.add(new Role("ROLE_EMPLOYE"));
        roles.forEach(role -> {
            if (roleRepository.findByRole(role.getRole()).orElse(null) == null) {
                roleRepository.save(role);
            }
        });
    }
}
