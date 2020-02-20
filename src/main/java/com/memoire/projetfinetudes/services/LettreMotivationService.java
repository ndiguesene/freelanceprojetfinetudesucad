package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.LettreMotivation;
import com.memoire.projetfinetudes.repositories.LettreMotivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LettreMotivationService {
    @Autowired
    private LettreMotivationRepository lettreMotivationRepository;

    public Optional<LettreMotivation> findLettreMotivationByUserId(Long id) {
        return lettreMotivationRepository.findLettreMotivationByUserId(id);
    }

    public LettreMotivation saveLettre(LettreMotivation lettre) {
        return lettreMotivationRepository.saveAndFlush(lettre);
    }
}
