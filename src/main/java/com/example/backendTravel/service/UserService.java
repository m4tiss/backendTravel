package com.example.backendTravel.service;

import com.example.backendTravel.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService(){
        userList = new ArrayList<>();
        User user = new User(1,"Mateusz");
        userList.add(user);
    }
    public Optional<Object> getUser(Integer id){
        Optional<Object> optional = Optional.empty();
        for(User user:userList){
            if(id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
