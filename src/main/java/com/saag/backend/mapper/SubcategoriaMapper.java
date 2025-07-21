package com.saag.backend.mapper;

import com.saag.backend.dto.subcategoria.SubcategoriaRequestDTO;
import com.saag.backend.dto.subcategoria.SubcategoriaResponseDTO;
import com.saag.backend.entity.Subcategoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubcategoriaMapper {
    SubcategoriaMapper INSTANCE = Mappers.getMapper(SubcategoriaMapper.class);

    @Mapping(target = "categoria.idCategoria", source = "idCategoria")
    Subcategoria toEntity(SubcategoriaRequestDTO subcategoriaRequestDTO);

    @Mapping(target = "idCategoria", source = "categoria.idCategoria")
    @Mapping(target = "nombreCategoria", source = "categoria.nombre")
    SubcategoriaResponseDTO toDto(Subcategoria subcategoria);
}