package joz.javapractice.service;

import joz.javapractice.model.AppUser;

import java.util.Optional;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    AppUser findByUsername(String username);
    Optional<AppUser> findUserById(Long userId);
}
