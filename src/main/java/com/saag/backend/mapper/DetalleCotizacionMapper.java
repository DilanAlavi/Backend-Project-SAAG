package com.saag.backend.mapper;

import com.saag.backend.dto.cotizacion.DetalleCotizacionRequestDTO;
import com.saag.backend.dto.cotizacion.DetalleCotizacionResponseDTO;
import com.saag.backend.entity.DetalleCotizacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetalleCotizacionMapper {
    DetalleCotizacionMapper INSTANCE = Mappers.getMapper(DetalleCotizacionMapper.class);

    @Mapping(source = "idProducto", target = "producto.idProducto")
    DetalleCotizacion toEntity(DetalleCotizacionRequestDTO detalleCotizacionRequestDTO);

    @Mapping(source = "producto.idProducto", target = "idProducto")
    @Mapping(source = "producto.nombre", target = "nombreProducto")
    DetalleCotizacionResponseDTO toDto(DetalleCotizacion detalleCotizacion);
}