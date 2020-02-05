package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
