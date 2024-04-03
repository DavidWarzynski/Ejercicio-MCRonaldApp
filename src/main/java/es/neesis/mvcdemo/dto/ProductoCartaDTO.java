package es.neesis.mvcdemo.dto;

import es.neesis.mvcdemo.model.Producto;
import lombok.Data;

@Data
public class ProductoCartaDTO {
    private Long id;
    private Producto producto;
    private Double precio;
}
