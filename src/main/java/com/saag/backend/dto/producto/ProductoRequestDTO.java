package com.saag.backend.dto.producto;

import lombok.Data;

import java.math.BigDecimal;

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