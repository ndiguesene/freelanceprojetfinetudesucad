package com.memoire.projetfinetudes.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Candidat extends User {
    @Column(name = "nombre_experience")
    private Long nombreExperience;

    public Long getNombreExperience() {
        return nombreExperience;
    }

    public void setNombreExperience(Long nombreExperience) {
        this.nombreExperience = nombreExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidat)) return false;
        Candidat candidat = (Candidat) o;
        return getNombreExperience().equals(candidat.getNombreExperience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombreExperience());
    }

    @Override
    public String toString() {
        return "Candidat{" +
                ""+super.toString()+""+
                "nombreExperience=" + nombreExperience +
                '}';
    }
}
