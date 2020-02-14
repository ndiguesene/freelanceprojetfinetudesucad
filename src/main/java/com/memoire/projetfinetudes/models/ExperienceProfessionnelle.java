package com.memoire.projetfinetudes.models;

import javax.persistence.*;

@Entity
public class ExperienceProfessionnelle {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nomEntreprise;
    private String secteurActivite;
    private String poste;
    private String region;
    private String debutTravail;
    private String competence;

    @ManyToOne
    private User user;

    public ExperienceProfessionnelle() {

    }

    @Override
    public String toString() {
        return "ExperienceProfessionnelle{" +
                "id=" + id +
                ", nomEntreprise='" + nomEntreprise + '\'' +
                ", secteurActivite='" + secteurActivite + '\'' +
                ", poste='" + poste + '\'' +
                ", region='" + region + '\'' +
                ", debutTravail='" + debutTravail + '\'' +
                ", competence='" + competence + '\'' +
                ", user=" + user.toString() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
