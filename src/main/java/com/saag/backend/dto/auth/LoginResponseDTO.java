package com.saag.backend.dto.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String mensaje;
    private String token;
    private String tokenType;
    private Long expiresIn; // en milisegundos
    private UsuarioInfo usuario;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UsuarioInfo {
        private Integer idUsuario;
        private String nombreUsuario;
        private String rol;
    }
}