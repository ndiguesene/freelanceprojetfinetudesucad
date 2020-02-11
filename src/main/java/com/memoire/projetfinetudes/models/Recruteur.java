package com.memoire.projetfinetudes.models;

import javax.persistence.Entity;

@Entity
public class Recruteur extends User {
    private String societeUrl;
    private String societeDescription;

    public Recruteur() {

    }


    public String getSocieteUrl() {
        return societeUrl;
    }

    public void setSocieteUrl(String societeUrl) {
        this.societeUrl = societeUrl;
    }

    public String getSocieteDescription() {
        return societeDescription;
    }

    public void setSocieteDescription(String societeDescription) {
        this.societeDescription = societeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recruteur)) return false;
        Recruteur recruteur = (Recruteur) o;
        return getId().equals(recruteur.getId()) &&
                getSocieteUrl().equals(recruteur.getSocieteUrl()) &&
                getSocieteDescription().equals(recruteur.getSocieteDescription());
    }

    @Override
    public String toString() {
        return "Recruteur{" +
                ", societeUrl='" + societeUrl + '\'' +
                ", societeDescription='" + societeDescription + '\'' +
                '}';
    }
}
