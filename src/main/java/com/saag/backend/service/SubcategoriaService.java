package com.saag.backend.service;

import com.saag.backend.dto.subcategoria.SubcategoriaRequestDTO;
import com.saag.backend.dto.subcategoria.SubcategoriaResponseDTO;

import java.util.List;

public interface SubcategoriaService {
    SubcategoriaResponseDTO createSubcategoria(SubcategoriaRequestDTO subcategoriaRequestDTO);
    SubcategoriaResponseDTO getSubcategoriaById(Integer id);
    List<SubcategoriaResponseDTO> getAllSubcategorias();
    SubcategoriaResponseDTO updateSubcategoria(Integer id, SubcategoriaRequestDTO subcategoriaRequestDTO);
    void deleteSubcategoria(Integer id);
}