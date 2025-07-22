package com.saag.backend.mapper;

import com.saag.backend.dto.subcategoria.SubcategoriaRequestDTO;
import com.saag.backend.dto.subcategoria.SubcategoriaResponseDTO;
import com.saag.backend.entity.Subcategoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubcategoriaMapper {

    @Mapping(target = "categoria.idCategoria", source = "idCategoria")
    @Mapping(target = "activa", source = "activa")
    @Mapping(target = "nombreSubcategoria", source = "nombreSubcategoria")
    Subcategoria toEntity(SubcategoriaRequestDTO subcategoriaRequestDTO);

    @Mapping(target = "idCategoria", source = "categoria.idCategoria")
    @Mapping(target = "nombreCategoria", source = "categoria.nombreCategoria")
    @Mapping(target = "activa", source = "activa")
    @Mapping(target = "nombreSubcategoria", source = "nombreSubcategoria")
    SubcategoriaResponseDTO toDto(Subcategoria subcategoria);
}
