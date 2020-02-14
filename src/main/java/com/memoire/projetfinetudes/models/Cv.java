package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cv {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateCv;
    private String objectif;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ConnaissanceLinguistique> connaissanceLinguistique;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ExperienceProfessionnelle> experienceProfessionnelle;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Formation> formation;
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cv{" +
                "id=" + id +
                ", dateCv=" + dateCv +
                ", objectif='" + objectif + '\'' +
                ", connaissanceLinguistique=" + connaissanceLinguistique +
                ", experienceProfessionnelle=" + experienceProfessionnelle +
                ", formation=" + formation +
                ", formation=" + user +
                '}';
    }

    public List<ConnaissanceLinguistique> getConnaissanceLinguistique() {
        return connaissanceLinguistique;
    }

    public void setConnaissanceLinguistique(List<ConnaissanceLinguistique> connaissanceLinguistique) {
        this.connaissanceLinguistique = connaissanceLinguistique;
    }

    public List<ExperienceProfessionnelle> getExperienceProfessionnelle() {
        return experienceProfessionnelle;
    }

    public void setExperienceProfessionnelle(List<ExperienceProfessionnelle> experienceProfessionnelle) {
        this.experienceProfessionnelle = experienceProfessionnelle;
    }

    public List<Formation> getFormation() {
        return formation;
    }

    public void setFormation(List<Formation> formation) {
        this.formation = formation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCv() {
        return dateCv;
    }

    public void setDateCv(LocalDateTime dateCv) {
        this.dateCv = dateCv;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }


}
