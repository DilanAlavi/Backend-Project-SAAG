package com.saag.backend.dto.producto;

import lombok.Data;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "DTO para crear un nuevo producto",
    example = "{\n  \"nombreProducto\": \"string\",\n  \"descripcionProducto\": \"string\",\n  \"imagenUrl\": \"string\",\n  \"idCategoria\": 1,\n  \"idSubcategoria\": 1,\n  \"idMarca\": 1,\n  \"precio\": 10.0,\n  \"activo\": true\n}"
)
@Data
public class ProductoRequestDTO {
    private String nombreProducto;
    private String descripcionProducto;
    private String imagenUrl;
    private Long idCategoria;
    private Long idSubcategoria;
    private Long idMarca;
    private BigDecimal precio;
    private Boolean activo;
}