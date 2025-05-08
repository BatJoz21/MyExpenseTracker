package joz.javapractice.service;

import joz.javapractice.dto.AppUserDTO;
import joz.javapractice.dto.AuthDTO;
import joz.javapractice.dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO registerUser(AppUserDTO appUserDTO);
    AuthResponseDTO loginUser(AuthDTO authDTO);
}
