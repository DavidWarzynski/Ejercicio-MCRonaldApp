package es.neesis.mvcdemo.dto;

import es.neesis.mvcdemo.model.Pedido;
import lombok.Data;

import java.util.List;

@Data
public class EmpleadoDTO {

    private Long id;
    private String nombre;
    private List<Pedido> pedidosAsignados;

}
