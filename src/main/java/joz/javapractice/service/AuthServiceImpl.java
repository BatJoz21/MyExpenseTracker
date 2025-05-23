package joz.javapractice.service;

import joz.javapractice.dto.AppUserDTO;
import joz.javapractice.dto.AuthDTO;
import joz.javapractice.dto.AuthResponseDTO;
import joz.javapractice.model.AppUser;
import joz.javapractice.model.Role;
import joz.javapractice.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDTO registerUser(AppUserDTO appUserDTO) {
        if (userService.findByUsername(appUserDTO.getUsername()) != null){
            return new AuthResponseDTO(null, "Error: username is already taken!");
        }
        AppUser appUser = new AppUser();
        appUser.setFullName(appUserDTO.getFullName());
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        appUser.setRole(Role.USER);
        userService.saveUser(appUser);

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(appUserDTO.getUsername());
        authDTO.setPassword(appUserDTO.getPassword());

        //return new AuthResponseDTO(jwtUtil.generateToken(authDTO.getUsername()), "success");
        return loginUser(authDTO);
    }

    @Override
    public AuthResponseDTO loginUser(AuthDTO authDTO) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
            );
            final String token = jwtUtil.generateToken(authDTO.getUsername());
            return new AuthResponseDTO(token, "success");
        } catch (BadCredentialsException e) {
            return new AuthResponseDTO(null, "error: Invalid username or password");
        }
    }
}
