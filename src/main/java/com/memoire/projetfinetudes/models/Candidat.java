package com.memoire.projetfinetudes.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Candidat extends User {
    @Column(name = "nombre_experience")
    private Long nombreExperience;

    public Long getCompanyName() {
        return nombreExperience;
    }

    public void setCompanyName(Long nombreExperience) {
        this.nombreExperience = nombreExperience;
    }
}
