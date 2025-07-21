
package com.saag.backend.mapper;

import com.saag.backend.dto.usuario.UsuarioRequestDTO;
import com.saag.backend.dto.usuario.UsuarioResponseDTO;
import com.saag.backend.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    // Para convertir Entity a ResponseDTO
    @Mapping(target = "rol", expression = "java(usuario.getRol().name())")
    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    // Para convertir RequestDTO a Entity - CORREGIDO
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "rol", expression = "java(mapRolFromString(usuarioRequestDTO.getRol()))")
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);

    // Método auxiliar para convertir String a Enum Usuario.Rol
    default Usuario.Rol mapRolFromString(String rol) {
        if (rol == null) {
            return Usuario.Rol.USUARIO; // valor por defecto
        }
        try {
            return Usuario.Rol.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Usuario.Rol.USUARIO; // valor por defecto si el rol no es válido
        }
    }
}
