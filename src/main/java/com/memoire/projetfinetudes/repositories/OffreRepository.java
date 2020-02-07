package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.OffreEmploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OffreRepository extends JpaRepository<OffreEmploi, Long> {

}