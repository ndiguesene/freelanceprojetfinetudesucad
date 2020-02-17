package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Recruteur;

import javax.transaction.Transactional;


@Transactional
public interface RecruteurRepository extends UserRepository<Recruteur>{
    Recruteur findRecruteurByUserName(String username);
}