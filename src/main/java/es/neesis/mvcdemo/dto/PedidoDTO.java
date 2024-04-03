package es.neesis.mvcdemo.dto;

import es.neesis.mvcdemo.model.ProductoPedido;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private Long id;
    private String identificador;
    private List<ProductoPedido> productos;
    private Double precioTotal;
}
