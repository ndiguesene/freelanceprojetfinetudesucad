package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Admin;
import com.memoire.projetfinetudes.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Lazy
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Admin save(Admin admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
    public Admin findAdminByUserNameOrEmail(String username, String email) {
        return adminRepository.findAdminByUserNameOrEmail(username, email);
    }
}
