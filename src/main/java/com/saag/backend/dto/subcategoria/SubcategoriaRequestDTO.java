package com.saag.backend.dto.subcategoria;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoriaRequestDTO {
    private String nombre;
    private Integer idCategoria;
    private Boolean activa;
}