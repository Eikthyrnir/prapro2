package com.timbar.wmi.prapro2.repositories;

import com.timbar.wmi.prapro2.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
}