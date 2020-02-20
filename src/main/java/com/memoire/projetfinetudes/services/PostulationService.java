package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Postulation;
import com.memoire.projetfinetudes.repositories.PostulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostulationService {

    @Autowired
    private PostulationRepository postulationRepository;

    public Postulation savePostulation(Postulation postulation) {
        return postulationRepository.saveAndFlush(postulation);
    }

    public void deleteByIdAndUserId(Long postulation, Long userId) {
        postulationRepository.deleteByIdAndUserId(postulation, userId);
    }

    public Optional<List<Postulation>> findPostulationsByOffreEmploiAndUserId(Long id) {
        return postulationRepository.findPostulationsByOffreEmploiAndUserId(id);
    }

    public Optional<Postulation> findPostulationById(Long id) {
        return postulationRepository.findById(id);
    }

    public List<Postulation> findPostulationsByUser_Id(Long id) {
        return postulationRepository.findAllByUser_Id(id);
    }
}
