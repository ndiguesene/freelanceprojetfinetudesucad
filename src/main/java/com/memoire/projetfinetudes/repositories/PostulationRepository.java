package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {

}
