package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.Candidat;
import com.memoire.projetfinetudes.models.OffreEmploi;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CandidatRepository extends UserRepository<Candidat>{
    Candidat findCandidatByUserName(String username);
}
