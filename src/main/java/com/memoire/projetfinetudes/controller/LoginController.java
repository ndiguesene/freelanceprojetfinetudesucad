package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.Candidat;
import com.memoire.projetfinetudes.models.Recruteur;
import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.CandidatService;
import com.memoire.projetfinetudes.services.RecruteurService;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecruteurService recruteurService;
    @Autowired
    private CandidatService candidatService;

    @GetMapping(value = {"/", "/login"})
    public String login(Model model, HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        model.addAttribute("user", new User(0L));
        return "/login";
    }

    @PostMapping(value = {"/", "/login"})
    public String loginPost() {
        return "home";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if (user.getRoles().parallelStream().anyMatch(p -> p.getRole().equals("ROLE_CANDIDAT"))) {
            return "redirect:/candidat/consulter_offre";
        } else if (user.getRoles().parallelStream().anyMatch(p -> p.getRole().equals("ROLE_RECRUTEUR"))) {
            return "redirect:/recruteur/consulter_candidatures";
        } else if (user.getRoles().parallelStream().anyMatch(p -> p.getRole().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/users";
        }
        return "/login";
    }

    @GetMapping(value = "/registration/candidat")
    public String registrationCandidat(Model model) {
        Candidat candidat = new Candidat();
        model.addAttribute("candidat", candidat);
        return "registrationCandidat";
    }
    @GetMapping(value = "/registration/recruteur")
    public String registrationRecruteur(Model model) {
        Recruteur recruteur = new Recruteur();
        model.addAttribute("recruteur", recruteur);
        return "registrationRecruteur";
    }

    @PostMapping(value = "/registration/candidat")
    public String registrationCandidat(@Valid Candidat candidat, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        User userExists = userService.findUserByUserName(candidat.getUserName());
        String errorValue = "username";
        if (userExists == null) {
            userExists = userService.findUserByEmail(candidat.getEmail());
            errorValue = "Email";
        }
        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user", "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("usernameExist", "username existe déjà.");
            return "redirect:/registration/candidat";
        }
        candidatService.save(candidat);
        model.addAttribute("successMessage", "Votre compte a été créé avec success, Connectez - vous à votre nouveau compte.");
        return "login";
    }

    @PostMapping(value = "/registration/recruteur")
    public String registrationRecruteur(@Valid Recruteur recruteur, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        User userExists = userService.findUserByUserName(recruteur.getUserName());
        String errorValue = "username";
        if (userExists == null) {
            userExists = userService.findUserByEmail(recruteur.getEmail());
            errorValue = "Email";
        }
        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user", "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("usernameExist", errorValue + " existe déjà.");
            return "redirect:/registration/recruteur";
        }
        recruteurService.save(recruteur);
        model.addAttribute("successMessage", "Votre compte a été créé avec success, Connectez - vous à votre nouveau compte.");
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
