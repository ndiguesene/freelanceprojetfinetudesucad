package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Postulation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime datePostulation;
    @ManyToOne
    private Cv cv;
    @ManyToOne
    private OffreEmploi offreEmploi;
    @ManyToOne
    private LettreMotivation lettreMotivation;
    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Postulation{" +
                "id=" + id +
                ", datePostulation=" + datePostulation +
                ", cv=" + cv +
                ", offreEmploi=" + offreEmploi +
                ", lettreMotivation=" + lettreMotivation +
                ", user=" + user +
                '}';
    }

    public Postulation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(LocalDateTime datePostulation) {
        this.datePostulation = datePostulation;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public OffreEmploi getOffreEmploi() {
        return offreEmploi;
    }

    public void setOffreEmploi(OffreEmploi offreEmploi) {
        this.offreEmploi = offreEmploi;
    }

    public LettreMotivation getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(LettreMotivation lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Postulation)) return false;
        Postulation that = (Postulation) o;
        return getId().equals(that.getId()) &&
                getDatePostulation().equals(that.getDatePostulation()) &&
                getCv().equals(that.getCv()) &&
                getOffreEmploi().equals(that.getOffreEmploi()) &&
                getLettreMotivation().equals(that.getLettreMotivation()) &&
                getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDatePostulation(), getCv(), getOffreEmploi(), getLettreMotivation(), getUser());
    }
}
