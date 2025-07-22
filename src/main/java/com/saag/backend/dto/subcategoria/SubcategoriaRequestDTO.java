package com.saag.backend.dto.subcategoria;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoriaRequestDTO {
    private String nombreSubcategoria;
    @Schema(example = "1")
    private Integer idCategoria;
    private Boolean activa;
}