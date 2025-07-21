package com.saag.backend.service;

import com.saag.backend.dto.marca.MarcaRequestDTO;
import com.saag.backend.dto.marca.MarcaResponseDTO;

import java.util.List;

public interface MarcaService {
    MarcaResponseDTO createMarca(MarcaRequestDTO marcaRequestDTO);
    MarcaResponseDTO getMarcaById(Integer id);
    List<MarcaResponseDTO> getAllMarcas();
    MarcaResponseDTO updateMarca(Integer id, MarcaRequestDTO marcaRequestDTO);
    void deleteMarca(Integer id);
}