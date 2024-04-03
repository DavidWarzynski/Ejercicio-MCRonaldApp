package es.neesis.mvcdemo.service;

import es.neesis.mvcdemo.dto.EmpleadoDTO;
import es.neesis.mvcdemo.exceptions.BusinessException;

import java.util.List;

public interface IPlantillaService {
    List<EmpleadoDTO> getEmpleados();
    void altaEmpleado(EmpleadoDTO empleado);
    void bajaEmpleado(Long empleadoId) throws BusinessException;
}
