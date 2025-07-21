
package com.saag.backend.service;

import com.saag.backend.dto.auth.LoginRequestDTO;
import com.saag.backend.dto.auth.LoginResponseDTO;
import com.saag.backend.entity.Usuario;
import com.saag.backend.repository.UsuarioRepository;
import com.saag.backend.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDTO autenticar(LoginRequestDTO loginRequest) {
        // Autenticar con Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getNombreUsuario(),
                        loginRequest.getPassword()
                )
        );

        // Buscar el usuario
        Usuario usuario = usuarioRepository.findByNombreUsuario(loginRequest.getNombreUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generar token JWT
        String token = jwtUtil.generateToken(usuario.getNombreUsuario(), usuario.getRol().name());

        // Crear respuesta
        LoginResponseDTO.UsuarioInfo usuarioInfo = LoginResponseDTO.UsuarioInfo.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .rol(usuario.getRol().name())
                .build();

        return LoginResponseDTO.builder()
                .mensaje("Login exitoso")
                .token(token)
                .tokenType("Bearer")
                .expiresIn(86400000L) // 24 horas
                .usuario(usuarioInfo)
                .build();
    }
}