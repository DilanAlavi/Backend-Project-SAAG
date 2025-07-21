
package com.saag.backend.controller;

import com.saag.backend.dto.auth.LoginRequestDTO;
import com.saag.backend.dto.auth.LoginResponseDTO;
import com.saag.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        LoginResponseDTO response = authService.autenticar(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Lógica de logout si es necesaria
        return ResponseEntity.ok("Logout exitoso");
    }
}
