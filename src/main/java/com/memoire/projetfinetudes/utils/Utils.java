package com.memoire.projetfinetudes.utils;

import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    static
    @Autowired
    UserService userService;

    public static User getCurrentUsers() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        User user = userService.findUserByUserName(userName);
        return user;
    }
}
