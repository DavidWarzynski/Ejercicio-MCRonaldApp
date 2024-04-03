package es.neesis.mvcdemo.dto;

import es.neesis.mvcdemo.model.Pedido;
import es.neesis.mvcdemo.model.ProductoCarta;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductoPedidoDTO {
    private Long id;
    private ProductoCarta productoCarta;
    private Pedido pedido;
    private Integer productAmount;
}
