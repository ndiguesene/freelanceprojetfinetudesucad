package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Recruteur;
import com.memoire.projetfinetudes.repositories.RecruteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruteurService {
    @Autowired
    private RecruteurRepository recruteurRepository;

    public Recruteur findCandidatByUsername(String username) {
        return recruteurRepository.findRecruteurByUserName(username);
    }
    public Recruteur save(Recruteur recruteur) {
        return recruteurRepository.saveAndFlush(recruteur);
    }
}