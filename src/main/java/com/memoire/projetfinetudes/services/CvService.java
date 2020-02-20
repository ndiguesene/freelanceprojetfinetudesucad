package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Cv;
import com.memoire.projetfinetudes.repositories.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CvService {
    @Autowired
    private CvRepository cvRepository;

    public Optional<Cv> findCvByUserId(Long id) {
        return cvRepository.findCvByUserId(id);
    }

    public Cv saveCv(Cv cv) {
        return cvRepository.save(cv);
    }
}
