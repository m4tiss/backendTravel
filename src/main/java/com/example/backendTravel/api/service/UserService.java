package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.User;
import com.example.backendTravel.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(Integer id){
        return userRepository.findById(id);
    }


    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User save(User user) { return userRepository.save(user); }

}

