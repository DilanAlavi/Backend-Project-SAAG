package com.saag.backend.dto.producto;

import lombok.Data;

@Data
public class ProductoRequestDTO {
    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private Long idMarca;
    private Long idCategoria;
    private Integer stock;
}