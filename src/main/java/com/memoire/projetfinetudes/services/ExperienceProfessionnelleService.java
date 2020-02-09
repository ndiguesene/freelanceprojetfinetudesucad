package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.ExperienceProfessionnelle;
import com.memoire.projetfinetudes.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceProfessionnelleService {
    @Autowired
    private ExperienceRepository experienceRepository;

    public Optional<List<ExperienceProfessionnelle>> findExperienceProfessionnellesByUserId(Long id) {
        return experienceRepository.findExperienceProfessionnellesByUserId(id);
    }
}
