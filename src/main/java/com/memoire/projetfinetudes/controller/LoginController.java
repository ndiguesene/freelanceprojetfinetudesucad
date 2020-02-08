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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User(0L));
        return "login";
    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.POST)
    public String loginPost() {
        return "home";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getEmail() + " " + user.getLastName() + " (" + user.getRoles() + ")");
        model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        List<Role> roleAll = roleService.getRoles();
        modelAndView.addObject("roleAll", roleAll);
        List<String> pays = new ArrayList<>();
        pays.add("SENEGAL");
        pays.add("MALI");
        pays.add("FRANCE");
        pays.add("USA");
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user", "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            List<Role> roleAll = roleService.getRoles();
            modelAndView.addObject("roleAll", roleAll);
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", user);
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}
