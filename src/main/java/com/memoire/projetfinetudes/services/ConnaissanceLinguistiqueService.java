package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.ConnaissanceLinguistique;
import com.memoire.projetfinetudes.repositories.ConnaissanceLinguistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnaissanceLinguistiqueService {
    @Autowired
    private ConnaissanceLinguistiqueRepository connaissanceLinguistiqueRepository;

    public Optional<List<ConnaissanceLinguistique>> findCompetenceLinguistiquesByUserId(Long id) {
        return connaissanceLinguistiqueRepository.findConnaissanceLinguistiquesByUserId(id);
    }
}
