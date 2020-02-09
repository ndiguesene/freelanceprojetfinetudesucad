package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.LettreMotivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LettreMotivationRepository extends JpaRepository<LettreMotivation, Long> {
    LettreMotivation findLettreMotivationByUserId(Long id);
}
