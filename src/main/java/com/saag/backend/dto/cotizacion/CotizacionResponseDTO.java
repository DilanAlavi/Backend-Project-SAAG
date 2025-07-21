package com.saag.backend.dto.cotizacion;

import com.saag.backend.entity.Cotizacion.Estado;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CotizacionResponseDTO {
    private Integer idCotizacion;
    private Integer idUsuario;
    private String nombreCompleto;
    private LocalDateTime fechaCotizacion;
    private Double totalCotizacion;
    private Estado estado;
    private List<DetalleCotizacionResponseDTO> detalles;
}