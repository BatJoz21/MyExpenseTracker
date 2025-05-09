package joz.javapractice.config;

import joz.javapractice.model.AppUser;
import joz.javapractice.model.Role;
import joz.javapractice.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()){
            AppUser adminUser = new AppUser();
            adminUser.setFullName("Admin User");
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRole(Role.ADMIN);
            userRepository.save(adminUser);
            System.out.println("Admin user created!");
        }
    }
}
