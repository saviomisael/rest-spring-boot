package io.github.saviomisael.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.saviomisael.dto.TokenResponseDto;
import io.github.saviomisael.exceptions.InvalidJwtAuthenticationException;
import io.github.saviomisael.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
    @Value(value = "${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    private final long validityInMilliseconds = 3600000; // 1hr
    private final UserDetailsService userService;
    private Algorithm algorithm = null;

    @Autowired
    public JwtTokenProvider(UserDetailsService userService) {
        this.userService = userService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    // gera a response com o token e o refresh token
    public TokenResponseDto generateToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        var token = getToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);

        return new TokenResponseDto(username, true, now, validity, token, refreshToken);
    }

    // gera um token a partir de um token
    public Authentication authenticate(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userService.loadUserByUsername(decodedJWT.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //pega o token da requisição
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.split(" ")[1];
        }

        return null;
    }

    // valida o token
    public boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = decodedToken(token);

            if (decodedJWT.getExpiresAt().before(new Date())) {
                throw new InvalidJwtAuthenticationException("Token expired.");
            }

            return true;
        } catch (Exception ex) {
            throw new InvalidJwtAuthenticationException("Invalid token.");
        }
    }

    // decodifica o token
    private DecodedJWT decodedToken(String token) throws JWTVerificationException {
        var alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        return verifier.verify(token);
    }

    // cria token de três horas
    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validity = new Date(now.getTime() + (validityInMilliseconds * 3));

        return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validity).withSubject(username).sign(algorithm).strip();
    }

    // cria token de 1 hora
    private String getToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validity).withSubject(username).withIssuer(issuerUrl).sign(algorithm).strip();
    }
}
