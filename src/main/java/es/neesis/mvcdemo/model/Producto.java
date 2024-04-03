package es.neesis.mvcdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Integer stockDisponible;

    public Producto(String nombre, Integer stockDisponible) {
        this.nombre = nombre;
        this.stockDisponible = stockDisponible;
    }
}
