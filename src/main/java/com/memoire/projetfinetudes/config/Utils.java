package com.memoire.projetfinetudes.config;

import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    @Autowired
    static
    private UserService userService;

    private static User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByUserName(loggedInUser.getName());
        return currentUser;
    }
}
