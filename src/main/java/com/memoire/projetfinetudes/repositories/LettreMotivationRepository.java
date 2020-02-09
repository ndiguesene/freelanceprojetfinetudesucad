package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.LettreMotivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LettreMotivationRepository extends JpaRepository<LettreMotivation, Long> {
    Optional<LettreMotivation> findLettreMotivationByUserId(Long id);
}
