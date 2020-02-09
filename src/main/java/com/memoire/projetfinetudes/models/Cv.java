package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Cv {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCv;
    private String objectif;
    @ManyToOne
    private ConnaissanceLinguistique connaissanceLinguistique;
    @ManyToOne
    private ExperienceProfessionnelle experienceProfessionnelle;
    @ManyToOne
    private Formation formation;
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cv{" +
                "id=" + id +
                ", dateCv=" + dateCv +
                ", objectif='" + objectif + '\'' +
                ", connaissanceLinguistique=" + connaissanceLinguistique.toString() +
                ", experienceProfessionnelle=" + experienceProfessionnelle.toString() +
                ", formation=" + formation.toString() +
                ", formation=" + user.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cv)) return false;
        Cv cv = (Cv) o;
        return getId().equals(cv.getId()) &&
                getDateCv().equals(cv.getDateCv()) &&
                getObjectif().equals(cv.getObjectif()) &&
                getConnaissanceLinguistique().equals(cv.getConnaissanceLinguistique()) &&
                getExperienceProfessionnelle().equals(cv.getExperienceProfessionnelle()) &&
                getFormation().equals(cv.getFormation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateCv(), getObjectif(), getConnaissanceLinguistique(), getExperienceProfessionnelle(), getFormation());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
