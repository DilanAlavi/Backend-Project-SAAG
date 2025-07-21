package com.saag.backend.service;

import com.saag.backend.dto.categoria.CategoriaRequestDTO;
import com.saag.backend.dto.categoria.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequestDTO);
    CategoriaResponseDTO getCategoriaById(Integer id);
    List<CategoriaResponseDTO> getAllCategorias();
    CategoriaResponseDTO updateCategoria(Integer id, CategoriaRequestDTO categoriaRequestDTO);
    void deleteCategoria(Integer id);
}