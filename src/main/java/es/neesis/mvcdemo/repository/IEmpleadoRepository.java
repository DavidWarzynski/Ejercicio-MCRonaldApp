package es.neesis.mvcdemo.repository;

import es.neesis.mvcdemo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoRepository extends JpaRepository<Empleado,Long> {
}
