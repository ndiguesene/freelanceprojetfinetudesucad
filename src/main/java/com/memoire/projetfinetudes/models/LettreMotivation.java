package com.memoire.projetfinetudes.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class LettreMotivation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String texte;
    @ManyToOne
    private User user;

    public LettreMotivation() {

    }

    @Override
    public String toString() {
        return "LettreMotivation{" +
                "id=" + id +
                ", texte='" + texte + '\'' +
                ", user=" + user.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LettreMotivation)) return false;
        LettreMotivation that = (LettreMotivation) o;
        return getId().equals(that.getId()) &&
                getTexte().equals(that.getTexte()) &&
                getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTexte(), getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
