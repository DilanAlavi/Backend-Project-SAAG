package com.saag.backend.dto.producto;

import com.saag.backend.dto.categoria.CategoriaResponseDTO;
import com.saag.backend.dto.marca.MarcaResponseDTO;
import lombok.Data;

@Data
public class ProductoResponseDTO {
    private Long idProducto;
    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private MarcaResponseDTO marca;
    private CategoriaResponseDTO categoria;
    private Integer stock;
}