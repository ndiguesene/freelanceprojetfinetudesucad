package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Candidat;

import javax.transaction.Transactional;

@Transactional
public interface CandidatRepository extends UserRepository<Candidat>{
    Candidat findCandidatByUserName(String username);
}
