package com.saag.backend.mapper;

import com.saag.backend.dto.marca.MarcaRequestDTO;
import com.saag.backend.dto.marca.MarcaResponseDTO;
import com.saag.backend.entity.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    Marca toEntity(MarcaRequestDTO marcaRequestDTO);

    MarcaResponseDTO toDto(Marca marca);
}