package com.saag.backend.dto.cotizacion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

// Agregar estos imports
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    description = "DTO para crear una nueva cotización",
    example = "{\n  \"idUsuario\": 1,\n  \"nombreCompleto\": \"string\",\n  \"detalles\": [ { \"idProducto\": 1, \"cantidad\": 2, \"precioUnitario\": 10, \"subtotal\": 20 } ]\n}"
)
public class CotizacionRequestDTO {
    @NotNull(message = "El ID del usuario es requerido")
    @Schema(description = "ID del usuario que crea la cotización", example = "1")
    private Integer idUsuario;

    @NotBlank(message = "El nombre completo es requerido")
    @Size(max = 200, message = "El nombre completo no debe exceder los 200 caracteres")
    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
    private String nombreCompleto;

    @NotNull(message = "La lista de detalles es requerida")
    @Size(min = 1, message = "Debe incluir al menos un detalle")
    @Valid
    @Schema(description = "Lista de detalles de la cotización")
    private List<DetalleCotizacionRequestDTO> detalles;
}