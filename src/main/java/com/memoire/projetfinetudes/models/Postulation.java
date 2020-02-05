package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Postulation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datePostulation;
    @ManyToOne
    private Cv cv;
    @ManyToOne
    private OffreEmploi offreEmploi;
    @ManyToOne
    private LettreMotivation lettreMotivation;
    @OneToOne
    private Candidat candidat;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(LocalDateTime datePostulation) {
        this.datePostulation = datePostulation;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public OffreEmploi getOffreEmploi() {
        return offreEmploi;
    }

    public void setOffreEmploi(OffreEmploi offreEmploi) {
        this.offreEmploi = offreEmploi;
    }
}
