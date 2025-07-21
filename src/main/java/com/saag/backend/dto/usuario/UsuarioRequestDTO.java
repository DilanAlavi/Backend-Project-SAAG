package com.saag.backend.dto.usuario;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {
    private String nombreUsuario;
    private String password;
    private String rol;  // Agregamos el rol para la creación
}