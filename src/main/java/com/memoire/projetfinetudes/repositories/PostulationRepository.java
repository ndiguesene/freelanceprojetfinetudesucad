package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {
    @Query("SELECT postulation FROM OffreEmploi offre, Postulation postulation WHERE offre.id = postulation.offreEmploi.id and offre.user.id = ?1")
    Optional<List<Postulation>> findPostulationsByOffreEmploiAndUserId(Long id);
    Optional<Postulation> findById(Long id);
    List<Postulation> findAllByUser_Id(Long id);
    List<Postulation> deleteByIdAndUserId(Long id, Long userId);
}
