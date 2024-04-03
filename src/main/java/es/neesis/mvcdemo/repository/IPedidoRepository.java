package es.neesis.mvcdemo.repository;

import es.neesis.mvcdemo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido,Long> {

}
