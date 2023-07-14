package com.codecool.seasonalproductdiscounter.service.users;

import com.codecool.seasonalproductdiscounter.model.users.User;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService{
    private final List<User> users;

    public AuthenticationServiceImpl() {
       users = new ArrayList<>();
       users.add(new User("Hamza","Hamza'sPassword"));
       users.add(new User("Walker","WalkersPassword"));
    }

    @Override
    public boolean authenticate(User user) {
        return users.contains(user);
    }
}
