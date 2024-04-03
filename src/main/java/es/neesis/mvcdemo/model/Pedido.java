package es.neesis.mvcdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String identificador;
    @ManyToOne
    private Empleado empleado;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ProductoPedido> productos;
    private Double precioTotal;

    public Pedido (String identificador, Double precioTotal) {
        this.identificador = identificador;
        this.precioTotal = precioTotal;
    }
}
