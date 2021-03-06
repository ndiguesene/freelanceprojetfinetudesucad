package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Candidat;
import com.memoire.projetfinetudes.models.OffreEmploi;
import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.repositories.CandidatRepository;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

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
