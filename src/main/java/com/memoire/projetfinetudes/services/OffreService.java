package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.OffreEmploi;
import com.memoire.projetfinetudes.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreService {
    @Autowired
    private OffreRepository offreRepository;

    public List<OffreEmploi> getAllOffres() {
        return offreRepository.findAll();
    }
}
