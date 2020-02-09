package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.ExperienceProfessionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceProfessionnelle, Long> {
    Optional<List<ExperienceProfessionnelle>> findExperienceProfessionnellesByUserId(Long id);
}