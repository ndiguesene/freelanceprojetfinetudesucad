package com.memoire.projetfinetudes.models;

import javax.persistence.*;

@Entity
public class ConnaissanceLinguistique {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nomLangue;
    private String niveauLangue;
    @ManyToOne
    private User user;

    public ConnaissanceLinguistique() {

    }

    @Override
    public String toString() {
        return "ConnaissanceLinguistique{" +
                "id=" + id +
                ", nomLangue='" + nomLangue + '\'' +
                ", niveauLangue='" + niveauLangue + '\'' +
                ", user=" + user.toString() +
                '}';
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
