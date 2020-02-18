package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Recruteur;
import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.repositories.RecruteurRepository;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class RecruteurService {
    @Autowired
    private RecruteurRepository recruteurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Recruteur findRecruteurByUserName(String username) {
        return recruteurRepository.findRecruteurByUserName(username);
    }

    public Recruteur save(Recruteur recruteur) {
        recruteur.setPassword(bCryptPasswordEncoder.encode(recruteur.getPassword()));
        recruteur.setActive(true);
        Role userRole = roleRepository.findByRole(new Role("ROLE_RECRUTEUR").getRole()).orElse(null);
        recruteur.setRoles(new HashSet(Arrays.asList(userRole)));
        return recruteurRepository.saveAndFlush(recruteur);
    }
    public Recruteur updatePasswordUser(Recruteur recruteur) {
        recruteur.setPassword(recruteur.getPassword());
        return recruteurRepository.saveAndFlush(recruteur);
    }
}