package es.neesis.mvcdemo.repository;


import es.neesis.mvcdemo.model.ProductoCarta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartaRepository extends JpaRepository<ProductoCarta,Long> {

}
