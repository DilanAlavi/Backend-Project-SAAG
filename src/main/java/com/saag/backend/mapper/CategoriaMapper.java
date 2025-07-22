package com.saag.backend.mapper;

import com.saag.backend.dto.categoria.CategoriaRequestDTO;
import com.saag.backend.dto.categoria.CategoriaResponseDTO;
import com.saag.backend.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    @Mapping(source = "nombre", target = "nombreCategoria")
    Categoria toEntity(CategoriaRequestDTO categoriaRequestDTO);
    @Mapping(source = "nombreCategoria", target = "nombre")
    CategoriaResponseDTO toDto(Categoria categoria);
}