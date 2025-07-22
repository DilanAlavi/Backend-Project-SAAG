package com.saag.backend.dto.cotizacion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


// Agregar estos imports
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para los detalles de una cotización")
public class DetalleCotizacionRequestDTO {
    @NotNull(message = "El ID del producto es requerido")
    @Schema(description = "ID del producto", example = "1")
    private Integer idProducto;

    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad del producto", example = "2")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
    @Schema(description = "Precio unitario del producto", example = "100.00")
    private Double precioUnitario;

    @NotNull(message = "El subtotal es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El subtotal debe ser mayor a 0")
    @Schema(description = "Subtotal del detalle (cantidad * precioUnitario)", example = "200.00")
    private Double subtotal;
}

