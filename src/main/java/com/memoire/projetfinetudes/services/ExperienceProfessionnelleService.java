package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.ExperienceProfessionnelle;
import com.memoire.projetfinetudes.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceProfessionnelleService {
    @Autowired
    private ExperienceRepository experienceRepository;

    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }
    public Optional<List<ExperienceProfessionnelle>> findExperienceProfessionnellesByUserId(Long id) {
        return experienceRepository.findExperienceProfessionnellesByUserId(id);
    }
    public ExperienceProfessionnelle saveExperience(ExperienceProfessionnelle experienceProfessionnelle) {
        return experienceRepository.saveAndFlush(experienceProfessionnelle);
    }
}
