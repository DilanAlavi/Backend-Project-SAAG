package com.saag.backend.dto.marca;

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
    description = "DTO para crear una nueva marca",
    example = "{\n  \"nombre\": \"string\"\n}"
)
public class MarcaRequestDTO {
    private String nombre;
}