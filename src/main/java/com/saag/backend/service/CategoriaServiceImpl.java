package com.saag.backend.service;

import com.saag.backend.dto.categoria.CategoriaRequestDTO;
import com.saag.backend.dto.categoria.CategoriaResponseDTO;
import com.saag.backend.entity.Categoria;
import com.saag.backend.mapper.CategoriaMapper;
import com.saag.backend.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaRequestDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(savedCategoria);
    }

    @Override
    public CategoriaResponseDTO getCategoriaById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + id));
        return categoriaMapper.toDto(categoria);
    }

    @Override
    public List<CategoriaResponseDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponseDTO updateCategoria(Integer id, CategoriaRequestDTO categoriaRequestDTO) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + id));
        existingCategoria.setNombre(categoriaRequestDTO.getNombre());
        Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
        return categoriaMapper.toDto(updatedCategoria);
    }

    @Override
    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}