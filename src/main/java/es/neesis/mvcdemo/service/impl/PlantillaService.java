package es.neesis.mvcdemo.service.impl;

import es.neesis.mvcdemo.dto.EmpleadoDTO;
import es.neesis.mvcdemo.repository.IEmpleadoRepository;
import es.neesis.mvcdemo.repository.IPedidoRepository;
import es.neesis.mvcdemo.service.IPlantillaService;
import es.neesis.mvcdemo.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantillaService implements IPlantillaService {
    @Autowired
    private IEmpleadoRepository empleadoRepository;
    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    public List<EmpleadoDTO> getEmpleados() {
        return DTOMapper.empleadoListToDTO(empleadoRepository.findAll());
    }

    @Override
    public void altaEmpleado(EmpleadoDTO empleado) {
        empleadoRepository.save(DTOMapper.dtoToEmpleado(empleado));
    }

    @Override
    public void bajaEmpleado(Long empleadoId) {
        empleadoRepository.deleteById(empleadoId);
    }


}
