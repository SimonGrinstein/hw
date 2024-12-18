package ait.cohort49.shop_49.security.controller;

import ait.cohort49.shop_49.security.dto.LoginRequestDTO;
import ait.cohort49.shop_49.security.dto.RefreshRequestDTO;
import ait.cohort49.shop_49.security.dto.TokenResponseDTO;
import ait.cohort49.shop_49.security.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        try {
            return authService.login(loginRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/refresh")
    public TokenResponseDTO refreshToken(@RequestBody RefreshRequestDTO refreshRequestDTO){
        try {
            return authService.refreshAccessToken(refreshRequestDTO.getRefreshToken());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
