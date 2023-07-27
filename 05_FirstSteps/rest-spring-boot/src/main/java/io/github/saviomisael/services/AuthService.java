package io.github.saviomisael.services;

import io.github.saviomisael.dto.AccountCredentialsDto;
import io.github.saviomisael.dto.TokenResponseDto;
import io.github.saviomisael.exceptions.ResourceNotFoundException;
import io.github.saviomisael.repositories.UserRepository;
import io.github.saviomisael.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class AuthService {
    private JwtTokenProvider tokenProvider;
    private AuthenticationManager authenticationManager;
    private UserRepository repository;

    @Autowired
    public AuthService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, UserRepository repository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    public ResponseEntity signIn(AccountCredentialsDto dto) throws UsernameNotFoundException, BadCredentialsException {
        var user = repository.findByUsername(dto.getUsername());

        if (user == null) {
            throw new ResourceNotFoundException("Username " + dto.getUsername() + " not found.");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        var tokenResponse = new TokenResponseDto();

        tokenResponse = tokenProvider.generateToken(user.getUsername(), user.getRoles());

        return ResponseEntity.ok(tokenResponse);
    }
}
