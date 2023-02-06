package com.timbar.wmi.prapro2.services;

import com.timbar.wmi.prapro2.entities.User;
import com.timbar.wmi.prapro2.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepo userRepo;

    public User register(User user) {
        return userRepo.save(user);
    }

}