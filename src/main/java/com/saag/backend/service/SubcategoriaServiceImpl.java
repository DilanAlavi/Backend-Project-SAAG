package com.saag.backend.service;

import com.saag.backend.dto.subcategoria.SubcategoriaRequestDTO;
import com.saag.backend.dto.subcategoria.SubcategoriaResponseDTO;
import com.saag.backend.entity.Categoria;
import com.saag.backend.entity.Subcategoria;
import com.saag.backend.mapper.SubcategoriaMapper;
import com.saag.backend.repository.SubcategoriaRepository;
import com.saag.backend.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubcategoriaServiceImpl implements SubcategoriaService {

    private final SubcategoriaRepository subcategoriaRepository;
    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaMapper subcategoriaMapper;

    @Override
    public SubcategoriaResponseDTO createSubcategoria(SubcategoriaRequestDTO subcategoriaRequestDTO) {
        Categoria categoria = categoriaRepository.findById(subcategoriaRequestDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + subcategoriaRequestDTO.getIdCategoria()));
        Subcategoria subcategoria = subcategoriaMapper.toEntity(subcategoriaRequestDTO);
        subcategoria.setCategoria(categoria);
        Subcategoria savedSubcategoria = subcategoriaRepository.save(subcategoria);
        return subcategoriaMapper.toDto(savedSubcategoria);
    }

    @Override
    public SubcategoriaResponseDTO getSubcategoriaById(Integer id) {
        Subcategoria subcategoria = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada con ID: " + id));
        return subcategoriaMapper.toDto(subcategoria);
    }

    @Override
    public List<SubcategoriaResponseDTO> getAllSubcategorias() {
        return subcategoriaRepository.findAll().stream()
                .map(subcategoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoriaResponseDTO updateSubcategoria(Integer id, SubcategoriaRequestDTO subcategoriaRequestDTO) {
        Subcategoria existingSubcategoria = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada"));

        Categoria categoria = categoriaRepository.findById(subcategoriaRequestDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existingSubcategoria.setNombreSubcategoria(subcategoriaRequestDTO.getNombre());
        existingSubcategoria.setCategoria(categoria);

        Subcategoria updatedSubcategoria = subcategoriaRepository.save(existingSubcategoria);
        return subcategoriaMapper.toDto(updatedSubcategoria);
    }

    @Override
    public void deleteSubcategoria(Integer id) {
        subcategoriaRepository.deleteById(id);
    }
}