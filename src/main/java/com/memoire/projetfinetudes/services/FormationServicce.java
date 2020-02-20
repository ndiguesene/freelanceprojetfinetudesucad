package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Formation;
import com.memoire.projetfinetudes.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormationServicce {
    @Autowired
    private FormationRepository formationRepository;

    public Optional<List<Formation>> findFormationsById(Long id) {
        return formationRepository.findFormationsByUserId(id);
    }
}
