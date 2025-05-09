package joz.javapractice.service;

import joz.javapractice.model.AppUser;
import joz.javapractice.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final AppUserRepository userRepository;

    public UserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Optional<AppUser> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
