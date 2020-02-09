package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    Optional<List<Formation>> findFormationsByUserId(Long id);
}