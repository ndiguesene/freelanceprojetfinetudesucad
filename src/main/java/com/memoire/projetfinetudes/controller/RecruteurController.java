package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.config.Utils;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
        try {
            model.addAttribute("currentUser", getCurrentUser());
            model.addAttribute("offre", offreEmploi);
            List<OffreEmploi> offres = this.currentUser == null ? this.offreService.getOffresByUser(getCurrentUser().getId()) : this.offreService.getOffresByUser(this.currentUser.getId());
            List<String> postes = Utils.getPoste();
            List<String> typeOffres = Utils.getTypeOffre();
            model.addAttribute("offres", offres);
            model.addAttribute("postes", postes);
            model.addAttribute("typeOffres", typeOffres);
        } catch (Exception e) {
            return "redirect:/login";
        }
        return "recruteur/postuler_offre";
    }
    @GetMapping(value = "/recruteur/editerOffre")
    public String modifierOffre(Model model, HttpServletRequest request) {
        Long offreId = 0L;
        if (request.getParameter("offre") != null && !request.getParameter("offre").isEmpty()) {
            offreId = Long.parseLong(request.getParameter("offre"));
        }
        OffreEmploi offre = offreService.findOffreById(offreId).orElse(null);
        model.addAttribute("offre", offre);
        model.addAttribute("postes", Utils.getPoste());
        model.addAttribute("typeOffres", Utils.getTypeOffre());

        return "/recruteur/editerOffre";
    }

    @PostMapping(value = "/recruteur/editerOffre/{id}")
    public String modifierOffrePost(OffreEmploi offre, @PathVariable String id) {
        Optional<OffreEmploi> of = offreService.findOffreById(Long.parseLong(id));
        if (of.isPresent()) {
            OffreEmploi o = of.orElse(null);

            o.setPoste(offre.getPoste());
            o.setConnaissanceTechnique(offre.getConnaissanceTechnique());
            o.setDescription(offre.getDescription());
            o.setTypeOffre(offre.getTypeOffre());
            o.setRegion(offre.getRegion());
            o.setQualiteRequise(offre.getQualiteRequise());

            offreService.saveOffreEmploi(o);
        }
        return "redirect:/recruteur/postuler_offre";
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

    @GetMapping(value = "/recruteur/supprimerOffre")
    public String supprimerOffre(HttpServletRequest request) {
        Long offre = 0L;
        if (request.getParameter("offre") != null && !request.getParameter("offre").isEmpty()) {
            offre = Long.parseLong(request.getParameter("offre"));
        }
        offreService.deleteOffre(offre);
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
        model.addAttribute("postulations", postulations.orElse(null));
        return "recruteur/consulter_candidatures";
    }

    public User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        return userService.findUserByUserName(userName);
    }
}
