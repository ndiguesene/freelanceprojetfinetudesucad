package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Cv;
import com.memoire.projetfinetudes.repositories.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvService {
    @Autowired
    private CvRepository cvRepository;

    public Cv findCvByUserId(Long id) {
        return cvRepository.findCvByUserId(id);
    }
}
