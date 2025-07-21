package com.saag.backend.dto.marca;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarcaResponseDTO {
    private Integer idMarca;
    private String nombre;
}