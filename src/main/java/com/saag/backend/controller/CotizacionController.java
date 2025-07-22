package com.saag.backend.controller;

import com.saag.backend.dto.cotizacion.CotizacionRequestDTO;
import com.saag.backend.dto.cotizacion.CotizacionResponseDTO;
import com.saag.backend.service.CotizacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Agregar este import
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cotizaciones")
@Tag(name = "Cotizaciones", description = "API para gestionar cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @Operation(summary = "Crear una nueva cotización",
            description = "Crea una nueva cotización con sus detalles de productos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cotización creada exitosamente",
            content = @Content(schema = @Schema(implementation = CotizacionResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de cotización inválidos"),
        @ApiResponse(responseCode = "404", description = "Usuario o producto no encontrado")
    })
    @PostMapping
    public ResponseEntity<CotizacionResponseDTO> createCotizacion(@Valid @RequestBody CotizacionRequestDTO cotizacionRequestDTO) {
        CotizacionResponseDTO createdCotizacion = cotizacionService.createCotizacion(cotizacionRequestDTO);
        return new ResponseEntity<>(createdCotizacion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotizacionResponseDTO> getCotizacionById(@PathVariable Integer id) {
        CotizacionResponseDTO cotizacion = cotizacionService.getCotizacionById(id);
        return ResponseEntity.ok(cotizacion);
    }

    @GetMapping
    public ResponseEntity<List<CotizacionResponseDTO>> getAllCotizaciones() {
        List<CotizacionResponseDTO> cotizaciones = cotizacionService.getAllCotizaciones();
        return ResponseEntity.ok(cotizaciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CotizacionResponseDTO> updateCotizacion(@PathVariable Integer id, @RequestBody CotizacionRequestDTO cotizacionRequestDTO) {
        CotizacionResponseDTO updatedCotizacion = cotizacionService.updateCotizacion(id, cotizacionRequestDTO);
        return ResponseEntity.ok(updatedCotizacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCotizacion(@PathVariable Integer id) {
        cotizacionService.deleteCotizacion(id);
        return ResponseEntity.noContent().build();
    }
}