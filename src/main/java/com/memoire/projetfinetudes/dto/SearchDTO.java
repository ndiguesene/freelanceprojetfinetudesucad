package com.memoire.projetfinetudes.dto;

public class SearchDTO {
    private String poste;
    private String region;

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

    @Override
    public String toString() {
        return "SearchDTO{" +
                "poste='" + poste + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
