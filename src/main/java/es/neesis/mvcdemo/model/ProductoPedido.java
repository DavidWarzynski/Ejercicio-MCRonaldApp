package es.neesis.mvcdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private ProductoCarta productoCarta;
    @ManyToOne
    private Pedido pedido;
    private Integer productAmount;

    public ProductoPedido(ProductoCarta productoCarta, Integer productAmount) {
        this.productoCarta = productoCarta;
        this.productAmount = productAmount;
    }
}
