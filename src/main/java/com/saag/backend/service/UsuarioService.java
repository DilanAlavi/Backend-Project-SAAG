package com.saag.backend.service;

import com.saag.backend.dto.usuario.UsuarioRequestDTO;
import com.saag.backend.dto.usuario.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO dto);
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO obtenerPorId(Integer id);
    void eliminarUsuario(Integer id);
}
