package es.neesis.mvcdemo.service.impl;

import es.neesis.mvcdemo.dto.PedidoDTO;
import es.neesis.mvcdemo.model.*;
import es.neesis.mvcdemo.repository.IEmpleadoRepository;
import es.neesis.mvcdemo.repository.IPedidoRepository;
import es.neesis.mvcdemo.repository.IProductoRepository;
import es.neesis.mvcdemo.exceptions.BusinessException;
import es.neesis.mvcdemo.service.IPedidoService;
import es.neesis.mvcdemo.utils.BusinessChecks;
import es.neesis.mvcdemo.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements IPedidoService {

    private IPedidoRepository pedidoRepository;
    private IEmpleadoRepository empleadoRepository;
    private IProductoRepository productoRepository;

    public PedidoService (IProductoRepository productoRepository,
                          IEmpleadoRepository empleado,
                          IPedidoRepository pedido) {
        this.productoRepository = productoRepository;
        this.empleadoRepository = empleado;
        this.pedidoRepository = pedido;
    }


    @Override
    public List<PedidoDTO> getPedidos() {
        return DTOMapper.pedidoListToDTO(pedidoRepository.findAll());
    }

    @Override
    public PedidoDTO getPedido(Long pedidoId) throws BusinessException {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        BusinessChecks.exists(pedido, "El pedido no existe");
        return DTOMapper.pedidoToDTO(pedido.get());
    }

    @Override
    public void crearPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = DTOMapper.dtoToPedido(pedidoDTO);
        actualizarUnidadesProductos(pedido.getProductos(), false);
        pedido.setPrecioTotal(caclularPrecioPedido(pedido.getProductos()));
        pedidoRepository.save(pedido);
    }

    @Override
    public void cancelarPedido(Long pedidoId) throws BusinessException {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        BusinessChecks.exists(pedido, "El pedido no existe");
        actualizarUnidadesProductos(pedido.get().getProductos(), true);
        pedidoRepository.delete(pedido.get());
    }

    @Override
    public void modificarPedido(PedidoDTO pedidoDTO) throws BusinessException {
        Pedido pedido = DTOMapper.dtoToPedido(pedidoDTO);

        Optional<Pedido> pedidoExistente = pedidoRepository.findById(pedido.getId());
        BusinessChecks.exists(pedidoExistente, "El pedido no existe");

        pedidoExistente.get().setIdentificador(pedido.getIdentificador());
        pedidoExistente.get().setEmpleado(pedido.getEmpleado());
        pedidoExistente.get().setProductos(pedido.getProductos());
        Double nuevoPrecio = caclularPrecioPedido(pedido.getProductos());
        pedidoExistente.get().setPrecioTotal(nuevoPrecio);
        this.pedidoRepository.save(pedidoExistente.get());
    }

    @Override
    public void asignarEmpleadoAPedido(Long empleadoId, Long pedidoId) throws BusinessException {
        Optional<Empleado> empleado = empleadoRepository.findById(empleadoId);
        BusinessChecks.exists(empleado, "El empleado no existe");

        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        BusinessChecks.exists(pedido, "El pedido no existe");

        pedido.get().setEmpleado(empleado.get());
        empleado.get().getPedidosAsignados().add(pedido.get());
    }

    private Double caclularPrecioPedido(List<ProductoPedido> productos) {
        double precioTotal = 0;
        for (ProductoPedido productoPedido : productos) {
            precioTotal += productoPedido.getProductoCarta().getPrecio() * productoPedido.getProductAmount();
        }
        return precioTotal;
    }

    private void actualizarUnidadesProductos(List<ProductoPedido> productosPedido, Boolean esAditivo) {
        for (ProductoPedido productoPedido : productosPedido) {
            ProductoCarta productoCarta = productoPedido.getProductoCarta();
            int cantidad = esAditivo ? productoPedido.getProductAmount() : -productoPedido.getProductAmount();
            actualizarUnidadesProducto(productoCarta.getProducto(), cantidad);
        }
    }

    private void actualizarUnidadesProducto(Producto producto, Integer unidades) {
        producto.setStockDisponible(producto.getStockDisponible() + unidades);
        this.productoRepository.save(producto);
    }
}
