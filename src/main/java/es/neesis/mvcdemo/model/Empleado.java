package es.neesis.mvcdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Pedido> pedidosAsignados = new ArrayList<Pedido>();

    public Empleado (String nombre){
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
}
