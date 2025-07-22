package com.saag.backend.service;

import com.saag.backend.dto.producto.ProductoRequestDTO;
import com.saag.backend.dto.producto.ProductoResponseDTO;
import com.saag.backend.entity.Categoria;
import com.saag.backend.entity.Marca;
import com.saag.backend.entity.Producto;
import com.saag.backend.entity.Subcategoria;
import com.saag.backend.mapper.ProductoMapper;
import com.saag.backend.repository.CategoriaRepository;
import com.saag.backend.repository.MarcaRepository;
import com.saag.backend.repository.ProductoRepository;
import com.saag.backend.repository.SubcategoriaRepository;
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

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Override
    public ProductoResponseDTO createProducto(ProductoRequestDTO productoRequestDTO) {
        Marca marca = marcaRepository.findById(productoRequestDTO.getIdMarca().intValue())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getIdCategoria().intValue())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        Subcategoria subcategoria = null;
        if (productoRequestDTO.getIdSubcategoria() != null) {
            subcategoria = subcategoriaRepository.findById(productoRequestDTO.getIdSubcategoria().intValue())
                .orElse(null);
        }
        Producto producto = new Producto();
        producto.setNombreProducto(productoRequestDTO.getNombreProducto());
        producto.setDescripcionProducto(productoRequestDTO.getDescripcionProducto());
        producto.setPrecio(productoRequestDTO.getPrecio());
        producto.setImagenUrl(productoRequestDTO.getImagenUrl());
        producto.setCategoria(categoria);
        producto.setSubcategoria(subcategoria);
        producto.setMarca(marca);
        producto.setActivo(productoRequestDTO.getActivo() != null ? productoRequestDTO.getActivo() : true);
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

        Marca marca = marcaRepository.findById(productoRequestDTO.getIdMarca().intValue())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getIdCategoria().intValue())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        Subcategoria subcategoria = null;
        if (productoRequestDTO.getIdSubcategoria() != null) {
            subcategoria = subcategoriaRepository.findById(productoRequestDTO.getIdSubcategoria().intValue())
                .orElse(null);
        }
        existingProducto.setNombreProducto(productoRequestDTO.getNombreProducto());
        existingProducto.setDescripcionProducto(productoRequestDTO.getDescripcionProducto());
        existingProducto.setPrecio(productoRequestDTO.getPrecio());
        existingProducto.setImagenUrl(productoRequestDTO.getImagenUrl());
        existingProducto.setCategoria(categoria);
        existingProducto.setSubcategoria(subcategoria);
        existingProducto.setMarca(marca);
        existingProducto.setActivo(productoRequestDTO.getActivo() != null ? productoRequestDTO.getActivo() : true);
        Producto updatedProducto = productoRepository.save(existingProducto);
        return productoMapper.toDto(updatedProducto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}