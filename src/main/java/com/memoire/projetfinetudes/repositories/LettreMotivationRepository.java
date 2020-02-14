package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.LettreMotivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface LettreMotivationRepository extends JpaRepository<LettreMotivation, Long> {
    Optional<LettreMotivation> findLettreMotivationByUserId(Long id);
}
