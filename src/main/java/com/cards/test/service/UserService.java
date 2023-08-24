package com.cards.test.service;

import com.cards.test.entity.Roles;
import com.cards.test.entity.User;
import com.cards.test.respository.RoleRepository;
import com.cards.test.respository.UserRepository;
import com.cards.test.util.Erole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<Roles> findByName(Erole role) {
        return roleRepository.findByName(role);
    }

    public boolean checkIfRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean findUserByID(Long user_id) {
        return userRepository.findById(user_id).isPresent();
    }
}
