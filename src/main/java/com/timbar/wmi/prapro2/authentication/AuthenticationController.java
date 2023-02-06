package com.timbar.wmi.prapro2.authentication;

import com.timbar.wmi.prapro2.configuration.jwt.JwtTokenProvider;
import com.timbar.wmi.prapro2.entities.User;
import com.timbar.wmi.prapro2.services.AuthenticationService;
import com.timbar.wmi.prapro2.services.UserBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserBuilder userBuilder;
    private final AuthenticationService auth;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginForm loginForm){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getLogin(), loginForm.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getLogin());

            String token = jwtTokenProvider.createToken(userDetails);

            LoginResponse loginResponse = new LoginResponse(token);

            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неправильный login или пароль");
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //TODO to catch SQLException if can't create such an account?
    //  example: org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "users_login_key"
    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody RegistrationForm form) {
        User user = userBuilder.parseForm(form);
        User registeredUser = auth.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}