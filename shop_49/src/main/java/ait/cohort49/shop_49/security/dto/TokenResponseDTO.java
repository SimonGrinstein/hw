package ait.cohort49.shop_49.security.dto;

import java.util.Objects;

public class TokenResponseDTO {

    private String refreshToken;
    private String accessToken;

    public TokenResponseDTO() {}

    public TokenResponseDTO(String accessToken, String refreshToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return String.format("TokenResponseDTO [refreshToken=%s, accessToken=%s]", refreshToken, accessToken);
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof TokenResponseDTO that)) return false;

        return Objects.equals(refreshToken, that.refreshToken) && Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(refreshToken);
        result = 31 * result + Objects.hashCode(accessToken);
        return result;
    }
}