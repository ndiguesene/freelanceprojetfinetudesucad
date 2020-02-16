package com.memoire.projetfinetudes.services;

import com.memoire.projetfinetudes.models.Role;
import com.memoire.projetfinetudes.models.User;
import com.memoire.projetfinetudes.repositories.RoleRepository;
import com.memoire.projetfinetudes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
        return userRepository.findByEmailAndActiveIsTrue(email);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserNameAndActiveIsTrue(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(user.getRoles().iterator().next().getRole()).orElse(null);
        user.setRoles(new HashSet(Arrays.asList(userRole)));
        return userRepository.saveAndFlush(user);
    }
    public User updatePasswordUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.saveAndFlush(user);
    }

}