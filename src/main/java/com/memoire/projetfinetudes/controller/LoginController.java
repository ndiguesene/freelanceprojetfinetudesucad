package com.memoire.projetfinetudes.controller;

import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.RoleService;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        model.addAttribute("user", new User(0L));
        return "/login";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.POST)
    public String loginPost() {
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getEmail() + " " + user.getLastName() + " (" + user.getRoles() + ")");
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        List<Role> roleAll = roleService.getRoles().stream().filter(role -> !role.getRole().equals("ROLE_ADMIN")).collect(Collectors.toList());
        modelAndView.addObject("roleAll", roleAll);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            System.out.println("Error 1 " + user.toString());
            bindingResult.rejectValue("userName", "error.user", "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            List<Role> roleAll = roleService.getRoles();
            modelAndView.addObject("roleAll", roleAll);
            System.out.println("Error 2 " + user.toString());
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Votre compte a été créé avec success, Connectez - vous à votre nouveau compte.");
            modelAndView.addObject("user", user);
            List<Role> roleAll = roleService.getRoles();
            modelAndView.addObject("roleAll", roleAll);
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
