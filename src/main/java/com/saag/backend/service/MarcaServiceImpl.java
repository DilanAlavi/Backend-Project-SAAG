package com.saag.backend.service;

import com.saag.backend.dto.marca.MarcaRequestDTO;
import com.saag.backend.dto.marca.MarcaResponseDTO;
import com.saag.backend.entity.Marca;
import com.saag.backend.mapper.MarcaMapper;
import com.saag.backend.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Override
    public MarcaResponseDTO createMarca(MarcaRequestDTO marcaRequestDTO) {
        Marca marca = marcaMapper.toEntity(marcaRequestDTO);
        Marca savedMarca = marcaRepository.save(marca);
        return marcaMapper.toDto(savedMarca);
    }

    @Override
    public MarcaResponseDTO getMarcaById(Integer id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
        return marcaMapper.toDto(marca);
    }

    @Override
    public List<MarcaResponseDTO> getAllMarcas() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MarcaResponseDTO updateMarca(Integer id, MarcaRequestDTO marcaRequestDTO) {
        Marca existingMarca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        existingMarca.setNombreMarca(marcaRequestDTO.getNombre()); // Corregido de setNombre a setNombreMarca
        Marca updatedMarca = marcaRepository.save(existingMarca);
        return marcaMapper.toDto(updatedMarca);
    }

    @Override
    public void deleteMarca(Integer id) {
        marcaRepository.deleteById(id);
    }
}