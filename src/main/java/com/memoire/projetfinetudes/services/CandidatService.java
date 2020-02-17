package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Candidat;
import com.memoire.projetfinetudes.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;

    public Candidat findCandidatByUsername(String username) {
        return candidatRepository.findCandidatByUserName(username);
    }
    public Candidat save(Candidat candidat) {
        return candidatRepository.saveAndFlush(candidat);
    }
}
