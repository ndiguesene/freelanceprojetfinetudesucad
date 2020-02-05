package com.memoire.projetfinetudes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConnaissanceLinguistique {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nomLangue;
    private String niveauLangue;

    public ConnaissanceLinguistique() {

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomLangue() {
        return nomLangue;
    }

    public void setNomLangue(String nomLangue) {
        this.nomLangue = nomLangue;
    }

    public String getNiveauLangue() {
        return niveauLangue;
    }

    public void setNiveauLangue(String niveauLangue) {
        this.niveauLangue = niveauLangue;
    }
}
