package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.ConnaissanceLinguistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConnaissanceLinguistiqueRepository extends JpaRepository<ConnaissanceLinguistique, Long> {
    Optional<List<ConnaissanceLinguistique>> findConnaissanceLinguistiquesByUserId(Long id);
}