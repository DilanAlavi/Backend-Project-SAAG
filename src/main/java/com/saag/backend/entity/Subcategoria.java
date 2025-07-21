package com.saag.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subcategorias", uniqueConstraints = @UniqueConstraint(columnNames = {"nombreSubcategoria", "categoria_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubcategoria;

    @Column(nullable = false, length = 100)
    private String nombreSubcategoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(nullable = false)
    private Boolean activa = true;
}