package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Candidat;
import com.memoire.projetfinetudes.models.Recruteur;
import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.repositories.CandidatRepository;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Candidat findCandidatByUsername(String username) {
        return candidatRepository.findCandidatByUserName(username);
    }
    public Candidat save(Candidat candidat) {
        candidat.setPassword(bCryptPasswordEncoder.encode(candidat.getPassword()));
        candidat.setActive(true);
        Role userRole = roleRepository.findByRole(new Role("ROLE_CANDIDAT").getRole()).orElse(null);
        candidat.setRoles(new HashSet(Arrays.asList(userRole)));
        return candidatRepository.saveAndFlush(candidat);
    }
    public Candidat updatePasswordUser(Candidat candidat) {
        candidat.setPassword(candidat.getPassword());
        return candidatRepository.saveAndFlush(candidat);
    }
}
