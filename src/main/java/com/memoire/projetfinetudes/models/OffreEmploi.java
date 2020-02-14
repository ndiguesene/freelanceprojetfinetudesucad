package com.memoire.projetfinetudes.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class OffreEmploi {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @CreatedDate
    @Column(name = "date_offre_emploi")
    private LocalDateTime dateOffreEmploi;
    @Column(name = "poste")
    @NotEmpty(message = "*Please provide poste")
    private String poste;
    @Column(name = "description")
    @Length(min = 4, message = "*Describe your poste")
    @NotEmpty(message = "*")
    private String description;
    @Column(name = "type_offre")
    @Length(message = "*")
    @NotEmpty(message = "*")
    private String typeOffre;
    @Column(name = "qualite_requise")
    @NotEmpty(message = "*")
    private String qualiteRequise;
    @Column(name = "connaissance_technique")
    @NotEmpty(message = "*")
    private String connaissanceTechnique;
    @Column(name = "region")
    @NotEmpty(message = "*")
    private String region;

    @ManyToOne
    private User user;

    public OffreEmploi() {

    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateOffreEmploi() {
        return dateOffreEmploi;
    }

    public void setDateOffreEmploi(LocalDateTime dateOffreEmploi) {
        this.dateOffreEmploi = dateOffreEmploi;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualiteRequise() {
        return qualiteRequise;
    }

    public void setQualiteRequise(String qualiteRequise) {
        this.qualiteRequise = qualiteRequise;
    }

    public String getConnaissanceTechnique() {
        return connaissanceTechnique;
    }

    public void setConnaissanceTechnique(String connaissanceTechnique) {
        this.connaissanceTechnique = connaissanceTechnique;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public User getRecruteur() {
        return user;
    }

    public void setRecruteur(User recruteur) {
        this.user = recruteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffreEmploi)) return false;
        OffreEmploi that = (OffreEmploi) o;
        return getId().equals(that.getId()) &&
                getDateOffreEmploi().equals(that.getDateOffreEmploi()) &&
                getPoste().equals(that.getPoste()) &&
                getDescription().equals(that.getDescription()) &&
                getQualiteRequise().equals(that.getQualiteRequise()) &&
                getConnaissanceTechnique().equals(that.getConnaissanceTechnique()) &&
                getRegion().equals(that.getRegion()) &&
                getRecruteur().equals(that.getRecruteur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateOffreEmploi(), getPoste(), getDescription(), getQualiteRequise(), getConnaissanceTechnique(), getRegion(), getRecruteur());
    }

    @Override
    public String toString() {
        return "OffreEmploi{" +
                "id=" + id +
                ", dateOffreEmploi=" + dateOffreEmploi +
                ", poste='" + poste + '\'' +
                ", description='" + description + '\'' +
                ", typeOffre='" + typeOffre + '\'' +
                ", qualiteRequise='" + qualiteRequise + '\'' +
                ", connaissanceTechnique='" + connaissanceTechnique + '\'' +
                ", region='" + region + '\'' +
                ", user=" + user.getEmail() +
                '}';
    }
}
