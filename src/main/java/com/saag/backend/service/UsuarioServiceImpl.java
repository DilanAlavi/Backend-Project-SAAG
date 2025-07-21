
package com.saag.backend.service;

import com.saag.backend.dto.usuario.UsuarioRequestDTO;
import com.saag.backend.dto.usuario.UsuarioResponseDTO;
import com.saag.backend.entity.Usuario;
import com.saag.backend.mapper.UsuarioMapper;
import com.saag.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder; // AGREGADO para encriptar passwords

    @Override
    public UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO dto) {
        // Validar si el usuario ya existe
        if (usuarioRepository.existsByNombreUsuario(dto.getNombreUsuario())) {
            throw new RuntimeException("El usuario ya existe");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);

        // Encriptar el password antes de guardar
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(guardado);
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Método adicional para buscar por nombre de usuario (útil para login)
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + nombreUsuario));
    }
}