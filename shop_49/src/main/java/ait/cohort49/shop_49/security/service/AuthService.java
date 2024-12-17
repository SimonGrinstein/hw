package ait.cohort49.shop_49.security.service;

import ait.cohort49.shop_49.model.entity.User;
import ait.cohort49.shop_49.security.dto.LoginRequestDTO;
import ait.cohort49.shop_49.security.dto.TokenResponseDTO;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final TokenService tokenService;
    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;
    // username : refreshToken
    private final Map<String,String> refreshStorage;

    public AuthService(TokenService tokenService, UserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.refreshStorage = new HashMap<>();
    }


    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthException {

        String username = loginRequestDTO.username();

        UserDetails foundUser = userService.loadUserByUsername(username);

        if(passwordEncoder.matches(loginRequestDTO.password(), foundUser.getPassword())){
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(username, refreshToken);

            return new TokenResponseDTO(accessToken, refreshToken);
        }

        throw new AuthException("Incorrect Login and/ or password");

    }

    public TokenResponseDTO refreshAccessToken(String refreshToken) throws AuthException {

        boolean isValid = tokenService.validateRefreshToken(refreshToken);

        Claims refreshClaims =tokenService.getRefreshClaimsFromToken(refreshToken);

        String username = refreshClaims.getSubject();

        String savedToken = refreshStorage.getOrDefault(username,null);
        boolean isSaved = savedToken != null && savedToken.equals(refreshToken);

        if(!isValid || isSaved){
            UserDetails foundUser = userService.loadUserByUsername(username);

            String accessToken = tokenService.generateAccessToken(foundUser);

            return new TokenResponseDTO(accessToken, refreshToken);
        }

        throw new AuthException("Incorrect refresh token. Re login please");

    }


}
