package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Postulation;
import com.memoire.projetfinetudes.repositories.PostulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostulationService {

    @Autowired
    private PostulationRepository postulationRepository;

    public Postulation savePostulation(Postulation postulation) {
        return postulationRepository.saveAndFlush(postulation);
    }
}
