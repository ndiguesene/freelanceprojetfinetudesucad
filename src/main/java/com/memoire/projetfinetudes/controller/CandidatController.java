package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.*;
import com.memoire.projetfinetudes.services.*;
import com.memoire.projetfinetudes.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class CandidatController {
    @Autowired
    private OffreService offreService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostulationService postulationService;
    @Autowired
    private CvService cvService;
    @Autowired
    private LettreMotivationService lettreMotivationService;

    @GetMapping(value = "/candidat/consulter_offre")
    public String consulterOffres(final Model model) {
        model.addAttribute("offres", offreService.getAllOffres());
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        model.addAttribute("currentUser", userService.findUserByUserName(userName));
        return "candidat/consulter_offre";
    }
    @GetMapping(value = "/candidat/postuler")
    public String postulerOffre(@RequestParam("offre") Long offreId) {
        User currentUser = getCurrentUser();
        Postulation postulation = new Postulation();
        postulation.setDatePostulation(LocalDateTime.now());
        postulation.setOffreEmploi(offreService.findOffreById(offreId).orElseGet(null));
        postulation.setUser(currentUser);

        postulation.setLettreMotivation(lettreMotivationService.findLettreMotivationByUserId(currentUser.getId()));
        postulation.setCv(cvService.findCvByUserId(currentUser.getId()));

        System.out.println(postulation.toString());
        // postulationService.savePostulation(postulation);

        return "redirect:/candidat/consulter_offre";
    }
    public User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        System.out.println("User ====> " + userName);
        User user = userService.findUserByUserName(userName);
        System.out.println(user.toString());
        return user;
    }
    @GetMapping(value = "/candidat/deposer_cv")
    public String deposerCv(final Model model) {
        Cv cv = new Cv();
        ExperienceProfessionnelle experienceProfessionnelle = new ExperienceProfessionnelle();
        Formation formation = new Formation();
        ConnaissanceLinguistique connaissanceLinguistique = new ConnaissanceLinguistique();

        model.addAttribute("cv", cv);
        model.addAttribute("experienceProfessionnelle", experienceProfessionnelle);
        model.addAttribute("formation", formation);
        model.addAttribute("connaissanceLinguistique", connaissanceLinguistique);
        return "candidat/deposer_cv";
    }

    @PostMapping(value = "/candidat/postuler/resume")
    public String deposerCvResume(@Valid Cv cv) {
       System.out.println(cv.getObjectif());
       return "redirect:/candidat/deposer_cv";
    }
    @PostMapping(value = "/candidat/postuler/experience")
    public String deposerCvExperience(@Valid ExperienceProfessionnelle experienceProfessionnelle) {
        experienceProfessionnelle.setUser(getCurrentUser());
        System.out.println(experienceProfessionnelle.toString());
        return "redirect:/candidat/deposer_cv";
    }
    @PostMapping(value = "/candidat/postuler/formation")
    public String deposerCvFormation(@Valid Formation formation) {
        formation.setUser(getCurrentUser());
        System.out.println(formation.toString());
        return "redirect:/candidat/deposer_cv";
    }
    @PostMapping(value = "/candidat/postuler/linguistiques")
    public String deposerCvFormation(@Valid ConnaissanceLinguistique connaissanceLinguistique) {
        connaissanceLinguistique.setUser(getCurrentUser());
        System.out.println(connaissanceLinguistique.toString());
        return "redirect:/candidat/deposer_cv";
    }

    @PostMapping(value = "/candidat/deposer_cv")
    public String deposerCv(@Valid Cv cv) {
        return "candidat/deposer_cv";
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
