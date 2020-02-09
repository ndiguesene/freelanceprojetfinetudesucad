package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.OffreEmploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<OffreEmploi, Long> {
    @Query("SELECT offre FROM OffreEmploi offre WHERE offre.user.id = :id")
    List<OffreEmploi> findOffreEmploisByUser(@Param("id") Long id);
}