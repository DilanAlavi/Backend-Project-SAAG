package com.saag.backend.dto.categoria;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaResponseDTO {
    private Integer idCategoria;
    private String nombre;
    private Boolean activa;
}