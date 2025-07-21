package com.saag.backend.mapper;

import com.saag.backend.dto.producto.ProductoRequestDTO;
import com.saag.backend.dto.producto.ProductoResponseDTO;
import com.saag.backend.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {MarcaMapper.class, CategoriaMapper.class})
public interface ProductoMapper {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "idMarca", target = "marca.idMarca")
    @Mapping(source = "idCategoria", target = "categoria.idCategoria")
    Producto toEntity(ProductoRequestDTO productoRequestDTO);

    ProductoResponseDTO toDto(Producto producto);
}