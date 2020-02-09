package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.*;
import com.memoire.projetfinetudes.services.*;
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
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private FormationServicce formationServicce;
    @Autowired
    private ConnaissanceLinguistiqueService connaissanceLinguistiqueService;
    @Autowired
    private ExperienceProfessionnelleService experienceProfessionnelleService;

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
        postulation.setCv(cvService.findCvByUserId(currentUser.getId()).orElse(new Cv()));

        postulationService.savePostulation(postulation);

        return "redirect:/candidat/consulter_offre";
    }
    public User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        User user = userService.findUserByUserName(userName);
        return user;
    }
    @GetMapping(value = "/candidat/deposer_cv")
    public String deposerCv(final Model model) {
        Long id = getCurrentUser().getId();
        Optional<List<ExperienceProfessionnelle>> experienceProfessionnelles = experienceProfessionnelleService.findForfindExperienceProfessionnellesByUserIdmationsById(id);
        Optional<List<Formation>> formations = formationServicce.findFormationsById(id);
        Optional<List<ConnaissanceLinguistique>> connaissanceLinguistiques = connaissanceLinguistiqueService.findCompetenceLinguistiquesByUserId(id);

        ExperienceProfessionnelle experienceProfessionnelle = new ExperienceProfessionnelle();
        Formation formation = new Formation();
        ConnaissanceLinguistique connaissanceLinguistique = new ConnaissanceLinguistique();
        Cv cv = new Cv();

        model.addAttribute("experienceProfessionnelle", experienceProfessionnelle);
        model.addAttribute("formation", formation);
        model.addAttribute("connaissanceLinguistique", connaissanceLinguistique);
        model.addAttribute("cv", cv);

        model.addAttribute("experienceProfessionnelles", experienceProfessionnelles);
        model.addAttribute("formations", formations);
        model.addAttribute("connaissanceLinguistiques", connaissanceLinguistiques);

        return "candidat/deposer_cv";
    }

    @PostMapping(value = "/candidat/postuler/resume")
    public String deposerCvResume(@Valid Cv cv) {
       Cv cvUpdate = cvService.findCvByUserId(getCurrentUser().getId()).orElse(new Cv());

       cvUpdate.setObjectif(cv.getObjectif());
       cvUpdate.setDateCv(LocalDateTime.now());
       cvUpdate.setUser(getCurrentUser());

       System.out.println(cvUpdate.toString());
       cvService.saveCv(cvUpdate);
       return "redirect:/candidat/deposer_cv";
    }
    @PostMapping(value = "/candidat/postuler/experience")
    public String deposerCvExperience(@Valid ExperienceProfessionnelle experienceProfessionnelle) {
        Cv cvUpdate = cvService.findCvByUserId(getCurrentUser().getId()).orElse(new Cv());
        experienceProfessionnelle.setUser(getCurrentUser());
        List<ExperienceProfessionnelle> experList = cvUpdate.getExperienceProfessionnelle();
        experList.add(experienceProfessionnelle);
        cvUpdate.setExperienceProfessionnelle(experList);

        cvService.saveCv(cvUpdate);
        return "redirect:/candidat/deposer_cv";
    }
    @PostMapping(value = "/candidat/postuler/formation")
    public String deposerCvFormation(@Valid Formation formation) {
        Cv cvUpdate = cvService.findCvByUserId(getCurrentUser().getId()).orElse(new Cv());
        formation.setUser(getCurrentUser());
        List<Formation> formaList = cvUpdate.getFormation();
        formaList.add(formation);
        cvUpdate.setFormation(formaList);

        cvService.saveCv(cvUpdate);
        return "redirect:/candidat/deposer_cv";
    }
    @PostMapping(value = "/candidat/postuler/linguistiques")
    public String deposerCvFormation(@Valid ConnaissanceLinguistique connaissanceLinguistique) {
        connaissanceLinguistique.setUser(getCurrentUser());
        Cv cvUpdate = cvService.findCvByUserId(getCurrentUser().getId()).orElse(new Cv());
        connaissanceLinguistique.setUser(getCurrentUser());
        List<ConnaissanceLinguistique> connaiList = cvUpdate.getConnaissanceLinguistique();
        connaiList.add(connaissanceLinguistique);
        cvUpdate.setConnaissanceLinguistique(connaiList);

        cvService.saveCv(cvUpdate);
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
