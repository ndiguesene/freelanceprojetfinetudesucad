package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.config.Utils;
import com.memoire.projetfinetudes.dto.PasswordDTO;
import com.memoire.projetfinetudes.models.*;
import com.memoire.projetfinetudes.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/candidat/consulter_offre")
    public String consulterOffres(final Model model) {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String userName = loggedInUser.getName();
            User user = userService.findUserByUserName(userName);
            Optional<List<Postulation>> postulations = Optional.ofNullable(postulationService.findPostulationsByUser_Id(user.getId()));

            List<OffreEmploi> offreEmplois = offreService.getAllOffres();
            List<OffreEmploi> offres = null;

            if (!postulations.isPresent()) {
                offres = offreEmplois;
            } else {
                List<OffreEmploi> offrePostuler = postulations.orElse(null).stream()
                        .map(p -> p.getOffreEmploi())
                        .distinct().collect(Collectors.toList());
                offres = offreEmplois.stream().filter(it -> !offrePostuler.contains(it)).collect(Collectors.toList());
            }
            model.addAttribute("offres", offres);
            model.addAttribute("currentUser", user);
            model.addAttribute("successMessage", "");
            return "candidat/consulter_offre";
        } catch (Exception e) {
            return "/login";
        }
    }

    @GetMapping(value = "/candidat/postuler")
    public String postulerOffre(@RequestParam("offre") Long offreId, Model model) {
        try {
            Postulation postulation = new Postulation();

            postulation.setDatePostulation(LocalDateTime.now());
            postulation.setOffreEmploi(offreService.findOffreById(offreId).orElseGet(null));
            postulation.setUser(getCurrentUser());
            LettreMotivation lettreMotivation = lettreMotivationService.findLettreMotivationByUserId(getCurrentUser().getId()).orElse(null);
            Cv cv = cvService.findCvByUserId(getCurrentUser().getId()).orElse(null);
            if (cv == null) {
                model.addAttribute("cvIsNull", true);
                model.addAttribute("currentUser", userService.findUserByUserName(getCurrentUser().getName()));
                model.addAttribute("offres", offreService.getAllOffres());
                return "/candidat/consulter_offre";
            }
            postulation.setLettreMotivation(lettreMotivation);
            postulation.setCv(cv);

            postulationService.savePostulation(postulation);
            model.addAttribute("successMessage", "Offre " + postulation.getOffreEmploi().getPoste() + " postulé avec succes.");

            return "redirect:/candidat/consulter_offre";
        } catch (Exception e) {
            return "/login";
        }
    }

    public User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        return userService.findUserByUserName(userName);
    }

    @GetMapping(value = "/candidat/deposer_cv")
    public String deposerCv(final Model model, HttpServletRequest request) {
        try {
            Long id = getCurrentUser().getId();

            Long experience = 0L;
            if (request.getParameter("experience") != null && !request.getParameter("experience").isEmpty()) {
                experience = Long.parseLong(request.getParameter("experience"));
            }
            ExperienceProfessionnelle experienceProfessionnelle = new ExperienceProfessionnelle();
            Formation formation = new Formation();
            ConnaissanceLinguistique connaissanceLinguistique = new ConnaissanceLinguistique();
            // LettreMotivation lettreMotivation = new LettreMotivation();

            List<ExperienceProfessionnelle> experienceProfessionnelles = experienceProfessionnelleService.findExperienceProfessionnellesByUserId(id).orElse(null);
            Long finalExperience = experience;
            Optional<ExperienceProfessionnelle> exPro = Optional.of(new ExperienceProfessionnelle());
            if (experienceProfessionnelles != null) {
                exPro = experienceProfessionnelles
                        .stream()
                        .filter(ex -> ex.getId() == finalExperience)
                        .findFirst();
            }

            if (exPro.isPresent()) {
                experienceProfessionnelle = exPro.orElse(new ExperienceProfessionnelle());
            }

            Optional<List<Formation>> formations = formationServicce.findFormationsById(id);
            Optional<List<ConnaissanceLinguistique>> connaissanceLinguistiques = connaissanceLinguistiqueService.findCompetenceLinguistiquesByUserId(id);
            Optional<Cv> cv = cvService.findCvByUserId(id);

            model.addAttribute("experienceProfessionnelle", experienceProfessionnelle);
            model.addAttribute("formation", formation);
            model.addAttribute("connaissanceLinguistique", connaissanceLinguistique);
            model.addAttribute("cv", cv.orElse(new Cv()));

            LettreMotivation lettreMotivation = lettreMotivationService.findLettreMotivationByUserId(id).orElse(null);
            model.addAttribute("lettreMotivation", lettreMotivation);

            model.addAttribute("experienceProfessionnelles", experienceProfessionnelles);
            model.addAttribute("formations", formations);
            model.addAttribute("connaissanceLinguistiques", connaissanceLinguistiques);
            List<String> secteurActivites = Utils.getSecteurActivite();
            List<String> postes = Utils.getPoste();
            model.addAttribute("secteurActivites", secteurActivites);
            model.addAttribute("postes", postes);

            return "/candidat/deposer_cv";
        } catch (Exception e) {
            return "/login";
        }
    }

    @PostMapping(value = "/candidat/postuler/resume")
    public String deposerCvResume(@Valid Cv cv) {
        try {
            Cv cvUpdate = cvService.findCvByUserId(getCurrentUser().getId()).orElse(new Cv());

            cvUpdate.setObjectif(cv.getObjectif());
            cvUpdate.setDateCv(LocalDateTime.now());
            cvUpdate.setUser(getCurrentUser());

            cvService.saveCv(cvUpdate);
            return "redirect:/candidat/deposer_cv";
        } catch (Exception e) {
            return "/login";
        }
    }

    @PostMapping(value = "/candidat/postuler/experience")
    public String deposerCvExperience(@Valid ExperienceProfessionnelle experienceProfessionnelle) {
        try {
            Cv cvUpdate = cvService.findCvByUserId(getCurrentUser().getId()).orElse(new Cv());
            experienceProfessionnelle.setUser(getCurrentUser());
            List<ExperienceProfessionnelle> experList = cvUpdate.getExperienceProfessionnelle();

            if (experienceProfessionnelle.getId() != null) {
                experienceProfessionnelleService.saveExperience(experienceProfessionnelle);
            } else {
                experList.add(experienceProfessionnelle);
            }
            cvUpdate.setExperienceProfessionnelle(experList);

            cvService.saveCv(cvUpdate);
            return "redirect:/candidat/deposer_cv";
        } catch (Exception z) {
            return "/login";
        }
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

    @PostMapping(value = "/candidat/postuler/lettreMotivation")
    public String deposerCvFormation(@Valid LettreMotivation lettreMotivation) {
        LettreMotivation lettre = lettreMotivationService.findLettreMotivationByUserId(getCurrentUser().getId()).orElse(new LettreMotivation());
        lettre.setTexte(lettreMotivation.getTexte());
        lettre.setUser(getCurrentUser());

        lettreMotivationService.saveLettre(lettre);
        return "redirect:/candidat/deposer_cv";
    }

    @PostMapping(value = "/candidat/deposer_cv")
    public String deposerCv(@Valid Cv cv) {
        return "/candidat/deposer_cv";
    }

    @GetMapping(value = "/candidat/postulation/annulerPostulation")
    public String infosPostulation(Model model, HttpServletRequest request) {
        Long postulationId = 0L;
        if (request.getParameter("postulation") != null && !request.getParameter("postulation").isEmpty()) {
            postulationId = Long.parseLong(request.getParameter("postulation"));
        }
        postulationService.deleteByIdAndUserId(postulationId, getCurrentUser().getId());
        return "redirect:/candidat/infos_postulation";
    }

    @GetMapping(value = "/candidat/infos_postulation")
    public String infosPostulation(Model model) {
        List<Postulation> postulations = postulationService.findPostulationsByUser_Id(getCurrentUser().getId());
        model.addAttribute("postulations", postulations);
        return "/candidat/infos_postulation";
    }

    @GetMapping(value = "/candidat/mon_profil")
    public String monProfil(Model model) {
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("userPassword", new PasswordDTO());
        model.addAttribute("password", "");
        model.addAttribute("typeMessage", "");
        return "/candidat/mon_profil";
    }

    @PostMapping(value = "/candidat/mon_profil")
    public String monProfilPost(User user) {
        User u = getCurrentUser();

        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setSexe(user.getSexe());
        u.setPays(user.getPays());
        u.setVille(user.getVille());
        u.setSecteurActivite(user.getSecteurActivite());
        u.setTitre_fonction(user.getTitre_fonction());
        u.setTelephone(user.getTelephone());

        userService.saveUser(u);
        return "redirect:/candidat/mon_profil";
    }

    @PostMapping(value = "/candidat/modifypassword")
    public String modifypassword(PasswordDTO user, Model model, RedirectAttributes redirectAttributes) {
        User userFull = userService.findUserByUserName(getCurrentUser().getUserName());
        if ((user.getNewPassword().equals(user.getConfirmPassword())) && (bCryptPasswordEncoder.matches(user.getCurrentPassword(), userFull.getPassword()))) {
            userFull.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
            userService.updatePasswordUser(userFull);
            model.addAttribute("password", "Mot de passe modifié avec succes.");
            model.addAttribute("typeMessage", "success");
        } else {
            if (!(user.getNewPassword().equals(user.getConfirmPassword()))) {
                model.addAttribute("password", "Les mots de passe ne sont pas identiques.");
            } else if (!(bCryptPasswordEncoder.matches(user.getCurrentPassword(), userFull.getPassword()))) {
                model.addAttribute("password", "Mot de passe courant est incorrect.");
            }
            model.addAttribute("typeMessage", "danger");
        }
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("userPassword", new PasswordDTO());
        return "/candidat/mon_profil";
    }
}
