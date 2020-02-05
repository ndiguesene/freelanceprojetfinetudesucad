package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cv {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCv;
    private String objectif;
    @ManyToOne
    private Candidat candidat;
    @ManyToOne
    private ConnaissanceLinguistique connaissanceLinguistique;
    @ManyToOne
    private ExperienceProfessionnelle experienceProfessionnelle;
    @ManyToOne
    private Formation formation;

    public Cv() {

    }

    public ConnaissanceLinguistique getConnaissanceLinguistique() {
        return connaissanceLinguistique;
    }

    public void setConnaissanceLinguistique(ConnaissanceLinguistique connaissanceLinguistique) {
        this.connaissanceLinguistique = connaissanceLinguistique;
    }

    public ExperienceProfessionnelle getExperienceProfessionnelle() {
        return experienceProfessionnelle;
    }

    public void setExperienceProfessionnelle(ExperienceProfessionnelle experienceProfessionnelle) {
        this.experienceProfessionnelle = experienceProfessionnelle;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCv() {
        return dateCv;
    }

    public void setDateCv(LocalDateTime dateCv) {
        this.dateCv = dateCv;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
}
