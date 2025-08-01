package com.saag.backend.dto.subcategoria;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoriaResponseDTO {
    private Integer idSubcategoria;
    private String nombreSubcategoria;
    private Boolean activa;
    private Integer idCategoria;
    private String nombreCategoria;
}
