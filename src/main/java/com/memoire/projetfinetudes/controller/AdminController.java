package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/admin/users")
    public String getListUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin/users";
    }
    @GetMapping("/admin/user/delete")
    public String deleteUsers(@RequestParam(name = "user") String user, Model model) {
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/user/edit")
    public String editUser(@RequestParam(name = "user") String user, Model model) {
        Optional<User> userEdit = userService.findById(Long.parseLong(user));
        model.addAttribute("user", userEdit.orElse(null));
        model.addAttribute("userInit", new User());
        return "/admin/editUser";
    }
    @PostMapping("/admin/user/edit")
    public String editUser(User user) {
        User userForReset = userService.findUserByEmail(user.getEmail());

        userForReset.setName(user.getName());
        userForReset.setLastName(user.getLastName());
        userForReset.setSexe(user.getSexe());
        userForReset.setPays(user.getPays());
        userForReset.setVille(user.getVille());
        userForReset.setSecteurActivite(user.getSecteurActivite());
        userForReset.setTitre_fonction(user.getTitre_fonction());
        userForReset.setTelephone(user.getTelephone());

        userService.saveUser(userForReset);
        return "redirect:/admin/users";
    }
}
