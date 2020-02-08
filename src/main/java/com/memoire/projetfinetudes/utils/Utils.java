package com.memoire.projetfinetudes.utils;

import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    @Autowired
    static
    UserService userService;

    public static User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        System.out.println("UserName " + userName);
        return userService.findUserByUserName(userName);
    }
}
