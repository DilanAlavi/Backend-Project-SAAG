package com.saag.backend.controller;

import com.saag.backend.dto.marca.MarcaRequestDTO;
import com.saag.backend.dto.marca.MarcaResponseDTO;
import com.saag.backend.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> createMarca(@RequestBody MarcaRequestDTO marcaRequestDTO) {
        MarcaResponseDTO createdMarca = marcaService.createMarca(marcaRequestDTO);
        return new ResponseEntity<>(createdMarca, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> getMarcaById(@PathVariable Integer id) {
        MarcaResponseDTO marca = marcaService.getMarcaById(id);
        return ResponseEntity.ok(marca);
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> getAllMarcas() {
        List<MarcaResponseDTO> marcas = marcaService.getAllMarcas();
        return ResponseEntity.ok(marcas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> updateMarca(@PathVariable Integer id, @RequestBody MarcaRequestDTO marcaRequestDTO) {
        MarcaResponseDTO updatedMarca = marcaService.updateMarca(id, marcaRequestDTO);
        return ResponseEntity.ok(updatedMarca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
        marcaService.deleteMarca(id);
        return ResponseEntity.noContent().build();
    }
}