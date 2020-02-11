package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.OffreEmploi;
import com.memoire.projetfinetudes.models.Postulation;
import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.OffreService;
import com.memoire.projetfinetudes.services.PostulationService;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
public class RecruteurController {
    @Autowired
    private UserService userService;
    @Autowired
    private OffreService offreService;
    @Autowired
    private PostulationService postulationService;

    private User currentUser = null;
    private OffreEmploi offreEmploi = new OffreEmploi();

    @GetMapping(value = "/recruteur/postuler_offre")
    public String consulterOffres(final Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("offre", offreEmploi);
        List<OffreEmploi> offres = this.currentUser == null ? this.offreService.getOffresByUser(getCurrentUser().getId()) : this.offreService.getOffresByUser(this.currentUser.getId());
        model.addAttribute("offres", offres);
        return "recruteur/postuler_offre";
    }

    @PostMapping(value = "/recruteur/offre")
    public String consulterOffresPost(@Valid OffreEmploi offreEmploi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/recruteur/postuler_offre";
        }
        offreEmploi.setRecruteur(getCurrentUser());
        offreEmploi.setDateOffreEmploi(LocalDateTime.now());
        offreService.saveOffreEmploi(offreEmploi);
        return "redirect:/recruteur/postuler_offre";
    }

    @GetMapping(value = "/recruteur/consulter_candidatures")
    public String consulterCandidature(final Model model, @RequestParam(value = "postulation", defaultValue = "0") String id) {
        Optional<Postulation> postulation;
        if (!id.equals("0")) {
            postulation = this.postulationService.findPostulationById(Long.parseLong(id));
            model.addAttribute("postulation", postulation.orElse(null));
        }
        Optional<List<Postulation>> postulations = this.postulationService.findPostulationsByOffreEmploiAndUserId(getCurrentUser().getId());
        // postulations.orElse(null).stream().forEach(p -> System.out.println("=======> P = " + p.toString()));
        model.addAttribute("postulations", postulations.orElse(null));
        return "recruteur/consulter_candidatures";
    }

    public User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        return userService.findUserByUserName(userName);
    }
}
