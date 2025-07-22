package com.saag.backend.mapper;

import com.saag.backend.dto.cotizacion.CotizacionRequestDTO;
import com.saag.backend.dto.cotizacion.CotizacionResponseDTO;
import com.saag.backend.entity.Cotizacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DetalleCotizacionMapper.class})
public interface CotizacionMapper {


    @Mapping(source = "idUsuario", target = "usuario.idUsuario")
    Cotizacion toEntity(CotizacionRequestDTO cotizacionRequestDTO);

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    CotizacionResponseDTO toDto(Cotizacion cotizacion);
}