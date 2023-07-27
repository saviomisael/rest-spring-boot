package io.github.saviomisael.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private boolean isAuthenticated;
    private Date createdAt;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenResponseDto(String username, boolean isAuthenticated, Date createdAt, Date expiration, String accessToken, String refreshToken) {
        this.username = username;
        this.isAuthenticated = isAuthenticated;
        this.createdAt = createdAt;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenResponseDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenResponseDto that = (TokenResponseDto) o;
        return isAuthenticated == that.isAuthenticated && Objects.equals(username, that.username) && Objects.equals(createdAt, that.createdAt) && Objects.equals(expiration, that.expiration) && Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, isAuthenticated, createdAt, expiration, accessToken, refreshToken);
    }
}
