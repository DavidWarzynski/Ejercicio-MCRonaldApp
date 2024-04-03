package es.neesis.mvcdemo.service;

import es.neesis.mvcdemo.dto.PedidoDTO;
import es.neesis.mvcdemo.exceptions.BusinessException;

import java.util.List;

public interface IPedidoService {
    List<PedidoDTO> getPedidos();
    PedidoDTO getPedido(Long pedidoId) throws BusinessException;
    void crearPedido(PedidoDTO pedido);
    void cancelarPedido(Long pedidoId) throws BusinessException;
    void modificarPedido(PedidoDTO pedido) throws BusinessException;
    void asignarEmpleadoAPedido(Long empleadoId, Long pedidoId) throws BusinessException;

}
