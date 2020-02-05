package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Candidat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;
    private LocalDate dateNaissance;
    @OneToOne
    private User user;

    public Candidat() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidat)) return false;
        Candidat candidat = (Candidat) o;
        return Objects.equals(getId(), candidat.getId()) &&
                getPrenom().equals(candidat.getPrenom()) &&
                getNom().equals(candidat.getNom()) &&
                getAdresse().equals(candidat.getAdresse()) &&
                getCodePostal().equals(candidat.getCodePostal()) &&
                getVille().equals(candidat.getVille()) &&
                getPays().equals(candidat.getPays()) &&
                getDateNaissance().equals(candidat.getDateNaissance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrenom(), getNom(), getAdresse(), getCodePostal(), getVille(), getPays(), getDateNaissance());
    }
}
