package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class OffreEmploi {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOffreEmploi;
    private String poste;
    private String description;
    private String qualiteRequise;
    private String connaissanceTechnique;
    private String region;
    @ManyToOne
    private Recruteur recruteur;

    public OffreEmploi() {

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateOffreEmploi() {
        return dateOffreEmploi;
    }

    public void setDateOffreEmploi(LocalDateTime dateOffreEmploi) {
        this.dateOffreEmploi = dateOffreEmploi;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualiteRequise() {
        return qualiteRequise;
    }

    public void setQualiteRequise(String qualiteRequise) {
        this.qualiteRequise = qualiteRequise;
    }

    public String getConnaissanceTechnique() {
        return connaissanceTechnique;
    }

    public void setConnaissanceTechnique(String connaissanceTechnique) {
        this.connaissanceTechnique = connaissanceTechnique;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Recruteur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffreEmploi)) return false;
        OffreEmploi that = (OffreEmploi) o;
        return getId().equals(that.getId()) &&
                getDateOffreEmploi().equals(that.getDateOffreEmploi()) &&
                getPoste().equals(that.getPoste()) &&
                getDescription().equals(that.getDescription()) &&
                getQualiteRequise().equals(that.getQualiteRequise()) &&
                getConnaissanceTechnique().equals(that.getConnaissanceTechnique()) &&
                getRegion().equals(that.getRegion()) &&
                getRecruteur().equals(that.getRecruteur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateOffreEmploi(), getPoste(), getDescription(), getQualiteRequise(), getConnaissanceTechnique(), getRegion(), getRecruteur());
    }
}
