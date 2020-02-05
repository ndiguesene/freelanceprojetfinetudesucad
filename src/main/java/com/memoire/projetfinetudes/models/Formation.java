package com.memoire.projetfinetudes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String etablissement;
    private int duree;
    private String nomDiplome;

    public Formation() {

    }

    public Long getId() {
        return id;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNomDiplome() {
        return nomDiplome;
    }

    public void setNomDiplome(String nomDiplome) {
        this.nomDiplome = nomDiplome;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", etablissement='" + etablissement + '\'' +
                ", duree=" + duree +
                ", nomDiplome='" + nomDiplome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formation)) return false;
        Formation formation = (Formation) o;
        return getDuree() == formation.getDuree() &&
                getId().equals(formation.getId()) &&
                getEtablissement().equals(formation.getEtablissement()) &&
                getNomDiplome().equals(formation.getNomDiplome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEtablissement(), getDuree(), getNomDiplome());
    }
}
