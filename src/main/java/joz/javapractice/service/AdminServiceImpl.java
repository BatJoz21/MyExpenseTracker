package joz.javapractice.service;

import joz.javapractice.model.AppUser;
import joz.javapractice.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private final AppUserRepository userRepository;

    public AdminServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
