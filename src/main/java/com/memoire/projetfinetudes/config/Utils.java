package com.memoire.projetfinetudes.config;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> getPoste() {
        List<String> l = new ArrayList<>();
        l.add("Développeur Java");
        l.add("Développeur Javascript");
        l.add("Data Engineer");
        l.add("Data Scientist");
        l.add("Génie Civil");
        l.add("Génie Mécanique");
        l.add("Génie Mécanique");
        return l;
    }
    public static List<String> getSecteurActivite() {
        List<String> l = new ArrayList<>();
        l.add("Informatique");
        l.add("Telecom");
        l.add("Agroalimentaire");
        l.add("Banque");
        l.add("Commerce");
        l.add("Transport");
        l.add("BTS");
        return l;
    }
    public static List<String> getTypeOffre() {
        List<String> l = new ArrayList<>();
        l.add("CDI");
        l.add("CDD");
        l.add("STAGE");
        return l;
    }
}
