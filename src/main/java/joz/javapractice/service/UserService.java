package joz.javapractice.service;

import joz.javapractice.model.AppUser;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    AppUser findByUsername(String username);
}
