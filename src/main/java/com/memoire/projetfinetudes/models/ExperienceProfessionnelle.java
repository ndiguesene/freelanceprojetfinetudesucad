package com.memoire.projetfinetudes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ExperienceProfessionnelle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nomEntrprise;
    private String secteurActivite;
    private String poste;
    private String region;
    private String debutTravail;
    private String competence;


    public ExperienceProfessionnelle() {
    }

    @Override
    public String toString() {
        return "ExperienceProfessionnelle{" +
                "id=" + id +
                ", nomEntrprise='" + nomEntrprise + '\'' +
                ", secteurActivite='" + secteurActivite + '\'' +
                ", poste='" + poste + '\'' +
                ", region='" + region + '\'' +
                ", debutTravail='" + debutTravail + '\'' +
                ", competence='" + competence + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienceProfessionnelle)) return false;
        ExperienceProfessionnelle that = (ExperienceProfessionnelle) o;
        return getId().equals(that.getId()) &&
                getNomEntrprise().equals(that.getNomEntrprise()) &&
                getSecteurActivite().equals(that.getSecteurActivite()) &&
                getPoste().equals(that.getPoste()) &&
                getRegion().equals(that.getRegion()) &&
                getDebutTravail().equals(that.getDebutTravail()) &&
                getCompetence().equals(that.getCompetence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNomEntrprise(), getSecteurActivite(), getPoste(), getRegion(), getDebutTravail(), getCompetence());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEntrprise() {
        return nomEntrprise;
    }

    public void setNomEntrprise(String nomEntrprise) {
        this.nomEntrprise = nomEntrprise;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDebutTravail() {
        return debutTravail;
    }

    public void setDebutTravail(String debutTravail) {
        this.debutTravail = debutTravail;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }
}
