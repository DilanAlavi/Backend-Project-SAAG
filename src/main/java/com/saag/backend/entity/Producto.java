package com.saag.backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(nullable = false, length = 200)
    private String nombreProducto;

    @Column(columnDefinition = "TEXT")
    private String descripcionProducto;

    @Column(nullable = false)
    private Double precio;

    private String imagenUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    private Boolean activo = true;
}
