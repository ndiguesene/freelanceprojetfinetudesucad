package com.memoire.projetfinetudes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExperienceProfessionnelle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
