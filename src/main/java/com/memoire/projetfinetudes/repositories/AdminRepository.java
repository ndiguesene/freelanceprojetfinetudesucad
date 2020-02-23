package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByUserNameOrEmail(String username, String email);
}
