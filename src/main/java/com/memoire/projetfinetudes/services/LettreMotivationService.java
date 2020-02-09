package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.LettreMotivation;
import com.memoire.projetfinetudes.repositories.LettreMotivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LettreMotivationService {
    @Autowired
    private LettreMotivationRepository lettreMotivationRepository;

    public LettreMotivation findLettreMotivationByUserId(Long id) {
        return lettreMotivationRepository.findLettreMotivationByUserId(id);
    }
}
