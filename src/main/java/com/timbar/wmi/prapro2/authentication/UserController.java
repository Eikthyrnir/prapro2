package com.timbar.wmi.prapro2.authentication;

import com.timbar.wmi.prapro2.entities.User;
import com.timbar.wmi.prapro2.services.UserRepoUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepoUserDetailsService service;

    public UserController(UserRepoUserDetailsService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/get")
    public ResponseEntity<User> getUser(Principal principal) {
        return new ResponseEntity<>(service.loadUserByLogin(principal.getName()), HttpStatus.OK);
    }

}