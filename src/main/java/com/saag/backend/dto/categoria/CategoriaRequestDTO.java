package com.saag.backend.dto.categoria;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    description = "DTO para crear una nueva categoría",
    example = "{\n  \"nombre\": \"string\",\n  \"activa\": true\n}"
)
public class CategoriaRequestDTO {
    private String nombre;
    private Boolean activa;
}