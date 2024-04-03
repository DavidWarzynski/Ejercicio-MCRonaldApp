package es.neesis.mvcdemo.repository;

import es.neesis.mvcdemo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Long> {

}
