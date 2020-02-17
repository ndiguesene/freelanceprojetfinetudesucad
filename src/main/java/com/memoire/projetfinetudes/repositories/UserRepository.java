package com.memoire.projetfinetudes.repositories;

import com.memoire.projetfinetudes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    User findByEmailAndActiveIsTrue(String email);
    User findByUserNameAndActiveIsTrue(String userName);
}