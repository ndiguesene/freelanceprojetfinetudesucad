package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.OffreEmploi;
import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.OffreService;
import com.memoire.projetfinetudes.services.UserService;
import com.memoire.projetfinetudes.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class RecruteurController {
    @Autowired
    UserService userService;
    @Autowired
    OffreService offreService;

    private User currentUser = null;
    private OffreEmploi offreEmploi = new OffreEmploi();

    @GetMapping(value = "/recruteur/postuler_offre")
    public String consulterOffres(final Model model) {
        model.addAttribute("currentUser", Utils.getCurrentUser());
        model.addAttribute("offre", offreEmploi);
        List<OffreEmploi> offres = this.currentUser == null ?  this.offreService.getOffresByUser(Utils.getCurrentUser().getId()): this.offreService.getOffresByUser(this.currentUser.getId());
        model.addAttribute("offres",  offres);
        return "recruteur/postuler_offre";
    }
    @PostMapping(value = "/recruteur/offre")
    public String consulterOffresPost(@Valid OffreEmploi offreEmploi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/recruteur/postuler_offre";
        }
        offreEmploi.setRecruteur(Utils.getCurrentUser());
        offreEmploi.setDateOffreEmploi(LocalDateTime.now());
        offreService.saveOffreEmploi(offreEmploi);
        return "redirect:/recruteur/postuler_offre";
    }
    @GetMapping(value = "/recruteur/consulter_candidature")
    public String consulterCandidature(final Model model) {
        return "redirect:/recruteur/postuler_offre";
    }
}
