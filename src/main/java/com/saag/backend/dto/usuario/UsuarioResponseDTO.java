package com.saag.backend.dto.usuario;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private Integer idUsuario;
    private String nombreUsuario;
    private String rol;
}