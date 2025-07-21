package com.saag.backend.controller;

import com.saag.backend.dto.subcategoria.SubcategoriaRequestDTO;
import com.saag.backend.dto.subcategoria.SubcategoriaResponseDTO;
import com.saag.backend.service.SubcategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
@RequiredArgsConstructor
public class SubcategoriaController {

    private final SubcategoriaService subcategoriaService;

    @PostMapping
    public ResponseEntity<SubcategoriaResponseDTO> createSubcategoria(@RequestBody SubcategoriaRequestDTO subcategoriaRequestDTO) {
        SubcategoriaResponseDTO createdSubcategoria = subcategoriaService.createSubcategoria(subcategoriaRequestDTO);
        return new ResponseEntity<>(createdSubcategoria, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoriaResponseDTO> getSubcategoriaById(@PathVariable Integer id) {
        SubcategoriaResponseDTO subcategoria = subcategoriaService.getSubcategoriaById(id);
        return ResponseEntity.ok(subcategoria);
    }

    @GetMapping
    public ResponseEntity<List<SubcategoriaResponseDTO>> getAllSubcategorias() {
        List<SubcategoriaResponseDTO> subcategorias = subcategoriaService.getAllSubcategorias();
        return ResponseEntity.ok(subcategorias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoriaResponseDTO> updateSubcategoria(@PathVariable Integer id, @RequestBody SubcategoriaRequestDTO subcategoriaRequestDTO) {
        SubcategoriaResponseDTO updatedSubcategoria = subcategoriaService.updateSubcategoria(id, subcategoriaRequestDTO);
        return ResponseEntity.ok(updatedSubcategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategoria(@PathVariable Integer id) {
        subcategoriaService.deleteSubcategoria(id);
        return ResponseEntity.noContent().build();
    }
}