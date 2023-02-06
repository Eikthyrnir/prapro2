package com.timbar.wmi.prapro2.services;

import com.timbar.wmi.prapro2.entities.User;
import com.timbar.wmi.prapro2.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepoUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByLogin(login);
        return user.orElseThrow(() ->
                new UsernameNotFoundException("User with such login (" + login + ") not found")
        );
    }

    public User loadUserByLogin(String login) {
        Optional<User> user = userRepo.findUserByLogin(login);
        return user.orElse(null);
    }
}