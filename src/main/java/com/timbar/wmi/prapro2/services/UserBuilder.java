package com.timbar.wmi.prapro2.services;

import com.timbar.wmi.prapro2.authentication.RegistrationForm;
import com.timbar.wmi.prapro2.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//TODO переименовать/переделать/убрать нахер, заменить на что-то другое?

@Service
@RequiredArgsConstructor
public class UserBuilder {

    private final PasswordEncoder encoder;

    public User parseForm(RegistrationForm form) {
        return new User(
            form.getLogin(),
            encoder.encode(form.getPassword())
        );
    }

}