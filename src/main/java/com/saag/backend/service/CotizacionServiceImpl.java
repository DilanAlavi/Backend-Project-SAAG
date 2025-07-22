package com.saag.backend.service;

import com.saag.backend.dto.cotizacion.CotizacionRequestDTO;
import com.saag.backend.dto.cotizacion.CotizacionResponseDTO;
import com.saag.backend.dto.cotizacion.DetalleCotizacionRequestDTO;
import com.saag.backend.entity.Cotizacion;
import com.saag.backend.entity.DetalleCotizacion;
import com.saag.backend.entity.Producto;
import com.saag.backend.entity.Usuario;
import com.saag.backend.mapper.CotizacionMapper;
import com.saag.backend.repository.CotizacionRepository;
import com.saag.backend.repository.DetalleCotizacionRepository;
import com.saag.backend.repository.ProductoRepository;
import com.saag.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CotizacionServiceImpl implements CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private DetalleCotizacionRepository detalleCotizacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CotizacionMapper cotizacionMapper;

    @Override
    @Transactional
    public CotizacionResponseDTO createCotizacion(CotizacionRequestDTO cotizacionRequestDTO) {
        Usuario usuario = usuarioRepository.findById(cotizacionRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cotizacion cotizacion = cotizacionMapper.toEntity(cotizacionRequestDTO);
        cotizacion.setUsuario(usuario);
        cotizacion.setFechaCotizacion(LocalDateTime.now());
        cotizacion.setEstado(Cotizacion.Estado.PENDIENTE);

        // Calcular el total antes de guardar
        double total = cotizacionRequestDTO.getDetalles().stream()
                .mapToDouble(DetalleCotizacionRequestDTO::getSubtotal)
                .sum();
        cotizacion.setTotalCotizacion(total);

        // Construyo la lista de detalles ANTES de guardar la cotización
        List<DetalleCotizacion> detalles = cotizacionRequestDTO.getDetalles().stream()
                .map(detalleDto -> {
                    Producto producto = productoRepository.findById(detalleDto.getIdProducto().longValue())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                    DetalleCotizacion detalle = new DetalleCotizacion();
                    detalle.setProducto(producto);
                    detalle.setCantidad(detalleDto.getCantidad());
                    detalle.setPrecioUnitario(detalleDto.getPrecioUnitario());
                    detalle.setSubtotal(detalleDto.getSubtotal());
                    // No asigno cotizacion aquí, se asigna automáticamente por la relación bidireccional
                    return detalle;
                }).collect(Collectors.toList());

        cotizacion.setDetalles(detalles);
        detalles.forEach(d -> d.setCotizacion(cotizacion)); // Relación bidireccional

        Cotizacion savedCotizacion = cotizacionRepository.save(cotizacion);

        // detalleCotizacionRepository.saveAll(detalles); // Eliminado, lo maneja el cascade

        return cotizacionMapper.toDto(savedCotizacion);
    }

    @Override
    public CotizacionResponseDTO getCotizacionById(Integer id) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotizacion no encontrada"));
        return cotizacionMapper.toDto(cotizacion);
    }

    @Override
    public List<CotizacionResponseDTO> getAllCotizaciones() {
        List<Cotizacion> cotizaciones = cotizacionRepository.findAll();
        return cotizaciones.stream()
                .map(cotizacionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CotizacionResponseDTO updateCotizacion(Integer id, CotizacionRequestDTO cotizacionRequestDTO) {
        Cotizacion existingCotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotizacion no encontrada"));

        Usuario usuario = usuarioRepository.findById(cotizacionRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existingCotizacion.setUsuario(usuario);
        existingCotizacion.setNombreCompleto(cotizacionRequestDTO.getNombreCompleto());
        
        // Calcular el total de la cotización basado en los detalles
        double total = cotizacionRequestDTO.getDetalles().stream()
                .mapToDouble(DetalleCotizacionRequestDTO::getSubtotal)
                .sum();
        existingCotizacion.setTotalCotizacion(total);

        // Eliminar detalles existentes y guardar los nuevos
        detalleCotizacionRepository.deleteAll(existingCotizacion.getDetalles());
        List<DetalleCotizacion> nuevosDetalles = cotizacionRequestDTO.getDetalles().stream()
                .map(detalleDto -> {
                    Producto producto = productoRepository.findById(detalleDto.getIdProducto().longValue())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                    DetalleCotizacion detalle = new DetalleCotizacion();
                    detalle.setCotizacion(existingCotizacion);
                    detalle.setProducto(producto);
                    detalle.setCantidad(detalleDto.getCantidad());
                    detalle.setPrecioUnitario(detalleDto.getPrecioUnitario());
                    detalle.setSubtotal(detalleDto.getSubtotal());
                    return detalle;
                }).collect(Collectors.toList());
        detalleCotizacionRepository.saveAll(nuevosDetalles);
        existingCotizacion.setDetalles(nuevosDetalles);

        Cotizacion updatedCotizacion = cotizacionRepository.save(existingCotizacion);
        return cotizacionMapper.toDto(updatedCotizacion);
    }

    @Override
    public void deleteCotizacion(Integer id) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotizacion no encontrada"));
        cotizacionRepository.delete(cotizacion);
    }
}