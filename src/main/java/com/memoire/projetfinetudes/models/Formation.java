package com.memoire.projetfinetudes.models;

import javax.persistence.*;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String etablissement;
    private int duree;
    private String nomDiplome;
    @ManyToOne
    private User user;

    public Formation() {

    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", etablissement='" + etablissement + '\'' +
                ", duree=" + duree +
                ", nomDiplome='" + nomDiplome + '\'' +
                ", user=" + user.toString() +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
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
}
