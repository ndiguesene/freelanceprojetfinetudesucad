package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import com.memoire.projetfinetudes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setEmail(user.getEmail());
        user.setLastName(user.getLastName());
        Role userRole = roleRepository.findByRole(user.getRoles().iterator().next().getRole());
        user.setRoles(new HashSet(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}