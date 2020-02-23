package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.OffreEmploi;
import com.memoire.projetfinetudes.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OffreService {
    @Autowired
    private OffreRepository offreRepository;

    public List<OffreEmploi> getAllOffres() {
        return offreRepository.findAll();
    }

    @Transactional
    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);
    }

    public List<OffreEmploi> getOffresByUser(Long id) {
        return offreRepository.findOffreEmploisByUser(id);
    }

    public OffreEmploi saveOffreEmploi(OffreEmploi offreEmploi) {
        return offreRepository.saveAndFlush(offreEmploi);
    }

    public Optional<OffreEmploi> findOffreById(Long id) {
        return offreRepository.findById(id);
    }
    public List<OffreEmploi> findByPosteContainingOrRegionOrderByPosteAsc(String poste, String region) {
        return offreRepository.findByPosteContainingOrRegionOrderByPosteAsc(poste, region);
    }
}
