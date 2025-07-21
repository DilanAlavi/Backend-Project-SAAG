package com.saag.backend.service;

import com.saag.backend.dto.producto.ProductoRequestDTO;
import com.saag.backend.dto.producto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    ProductoResponseDTO createProducto(ProductoRequestDTO productoRequestDTO);
    ProductoResponseDTO getProductoById(Long id);
    List<ProductoResponseDTO> getAllProductos();
    ProductoResponseDTO updateProducto(Long id, ProductoRequestDTO productoRequestDTO);
    void deleteProducto(Long id);
}