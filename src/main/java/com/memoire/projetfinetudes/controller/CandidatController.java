package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.OffreService;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CandidatController {
    @Autowired
    OffreService offreService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/candidat/consulter_offre")
    public String consulterOffres(final Model model) {
        model.addAttribute("offres", offreService.getAllOffres());
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        System.out.println(userName);
        model.addAttribute("currentUser", userService.findUserByUserName(userName));
        return "candidat/consulter_offre";
    }
    @GetMapping(value = "/candidat/deposer_cv")
    public String deposerCv() {
        return "candidat/deposer_cv";
    }
    @GetMapping(value = "/candidat/deposer_lm")
    public String deposerLM() {
        return "candidat/deposer_lm";
    }
    @GetMapping(value = "/candidat/infos_postulation")
    public String infosPostulation() {
        return "candidat/infos_postulation";
    }
    @GetMapping(value = "/candidat/mon_profil")
    public String monProfil() {
        return "candidat/mon_profil";
    }
}
