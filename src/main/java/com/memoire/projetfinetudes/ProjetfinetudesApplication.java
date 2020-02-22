package com.memoire.projetfinetudes;

import com.memoire.projetfinetudes.models.Admin;
import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import com.memoire.projetfinetudes.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ProjetfinetudesApplication implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminService adminService;

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
        Admin admin = new Admin();
        Set s = new HashSet();
        s.add(roleRepository.findByRole("ROLE_ADMIN").orElse(null));
        admin.setRoles(s);
        admin.setActive(true);
        admin.setEmail("admin@admin.com");
        admin.setLastName("admin");
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setPays("Sénégal");
        admin.setSexe("Masculin");
        admin.setUserName("admin");
        admin.setVille("Dakar");
        admin.setTelephone("772083746");
        admin.setSecteurActivite("Informatique");
        admin.setTitre_fonction("informaticien");

        if (adminService.findAdminByUserNameOrEmail(admin.getUserName(), admin.getEmail()) == null) {
            adminService.save(admin);
        }
    }
}
