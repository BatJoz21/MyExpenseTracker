package joz.javapractice.controller;

import joz.javapractice.dto.AppUserDTO;
import joz.javapractice.dto.AuthDTO;
import joz.javapractice.dto.AuthResponseDTO;
import joz.javapractice.model.AppUser;
import joz.javapractice.service.AuthService;
import joz.javapractice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody AppUserDTO appUserDTO){
        AuthResponseDTO response = authService.registerUser(appUserDTO);

        if ("success".equals(response.getMessage())){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthDTO authDTO){
        AuthResponseDTO response = authService.loginUser(authDTO);

        if ("success".equals(response.getMessage())){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }
}
