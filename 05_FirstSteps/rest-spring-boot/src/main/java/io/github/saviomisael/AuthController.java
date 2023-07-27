package io.github.saviomisael;

import io.github.saviomisael.dto.AccountCredentialsDto;
import io.github.saviomisael.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/signIn")
    public ResponseEntity signIn(@RequestBody AccountCredentialsDto dto) {
        if (dto == null || dto.getUsername() == null || dto.getUsername().isBlank() || dto.getPassword() == null || dto.getPassword().isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request.");

        return authService.signIn(dto);
    }
}
