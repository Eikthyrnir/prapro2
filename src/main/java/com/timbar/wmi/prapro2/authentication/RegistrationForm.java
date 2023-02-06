package com.timbar.wmi.prapro2.authentication;

import lombok.Data;

//TODO validation тут или в user, наверное тут, раз это с фронта приходит
@Data
public class RegistrationForm {

    private String login;
    private String password;

}