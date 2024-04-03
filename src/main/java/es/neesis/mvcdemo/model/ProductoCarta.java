package es.neesis.mvcdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCarta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Producto producto;
    private Double precio;

    public ProductoCarta(Producto producto, Double precio) {
        this.producto = producto;
        this.precio = precio;
    }
}
