package com.saag.backend.service;

import com.saag.backend.dto.cotizacion.CotizacionRequestDTO;
import com.saag.backend.dto.cotizacion.CotizacionResponseDTO;

import java.util.List;

public interface CotizacionService {
    CotizacionResponseDTO createCotizacion(CotizacionRequestDTO cotizacionRequestDTO);
    CotizacionResponseDTO getCotizacionById(Integer id);
    List<CotizacionResponseDTO> getAllCotizaciones();
    CotizacionResponseDTO updateCotizacion(Integer id, CotizacionRequestDTO cotizacionRequestDTO);
    void deleteCotizacion(Integer id);
}