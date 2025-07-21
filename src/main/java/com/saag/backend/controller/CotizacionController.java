package com.saag.backend.controller;

import com.saag.backend.dto.cotizacion.CotizacionRequestDTO;
import com.saag.backend.dto.cotizacion.CotizacionResponseDTO;
import com.saag.backend.service.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping
    public ResponseEntity<CotizacionResponseDTO> createCotizacion(@RequestBody CotizacionRequestDTO cotizacionRequestDTO) {
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