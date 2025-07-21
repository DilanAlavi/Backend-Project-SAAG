package com.saag.backend.service;

import com.saag.backend.dto.producto.ProductoRequestDTO;
import com.saag.backend.dto.producto.ProductoResponseDTO;
import com.saag.backend.entity.Categoria;
import com.saag.backend.entity.Marca;
import com.saag.backend.entity.Producto;
import com.saag.backend.mapper.ProductoMapper;
import com.saag.backend.repository.CategoriaRepository;
import com.saag.backend.repository.MarcaRepository;
import com.saag.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ProductoResponseDTO createProducto(ProductoRequestDTO productoRequestDTO) {
        Marca marca = marcaRepository.findById(productoRequestDTO.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = productoMapper.toEntity(productoRequestDTO);
        producto.setMarca(marca);
        producto.setCategoria(categoria);
        Producto savedProducto = productoRepository.save(producto);
        return productoMapper.toDto(savedProducto);
    }

    @Override
    public ProductoResponseDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDto(producto);
    }

    @Override
    public List<ProductoResponseDTO> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponseDTO updateProducto(Long id, ProductoRequestDTO productoRequestDTO) {
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Marca marca = marcaRepository.findById(productoRequestDTO.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existingProducto.setNombreProducto(productoRequestDTO.getNombreProducto());
        existingProducto.setDescripcion(productoRequestDTO.getDescripcion());
        existingProducto.setPrecio(productoRequestDTO.getPrecio());
        existingProducto.setMarca(marca);
        existingProducto.setCategoria(categoria);
        existingProducto.setStock(productoRequestDTO.getStock());

        Producto updatedProducto = productoRepository.save(existingProducto);
        return productoMapper.toDto(updatedProducto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}