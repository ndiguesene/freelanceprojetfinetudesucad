package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {
    Optional<Cv> findCvByUserId(Long id);
}