package com.saag.backend.dto.cotizacion;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCotizacionRequestDTO {
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}

