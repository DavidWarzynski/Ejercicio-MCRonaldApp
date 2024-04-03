package es.neesis.mvcdemo.service.impl;

import es.neesis.mvcdemo.dto.PedidoDTO;
import es.neesis.mvcdemo.exceptions.BusinessException;
import es.neesis.mvcdemo.model.*;
import es.neesis.mvcdemo.repository.IEmpleadoRepository;
import es.neesis.mvcdemo.repository.IPedidoRepository;
import es.neesis.mvcdemo.repository.IProductoRepository;
import es.neesis.mvcdemo.utils.DTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    private PedidoService sut;
    private IProductoRepository productoRepository;
    private IEmpleadoRepository empleadoRepository;
    private IPedidoRepository pedidoRepository;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        this.productoRepository = Mockito.mock(IProductoRepository.class);
        this.empleadoRepository = Mockito.mock(IEmpleadoRepository.class);
        this.pedidoRepository = Mockito.mock(IPedidoRepository.class);
        this.sut = new PedidoService(productoRepository,empleadoRepository,pedidoRepository);

        Empleado empleado = new Empleado("Ramon");
        Producto producto = new Producto("Batido",20);
        ProductoCarta productoCarta = new ProductoCarta(producto,5.0);
        ProductoPedido productoPedido = new ProductoPedido(productoCarta,2);
        Pedido pedido = new Pedido("P-01",10.0);
        pedido.setEmpleado(empleado);
        List<ProductoPedido> products = new ArrayList<>();
        products.add(productoPedido);
        pedido.setProductos(products);
        this.pedido = pedido;

        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos.add(pedido);

        when(pedidoRepository.findAll()).thenReturn(pedidos);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
    }

    @Test
    void testGetPedidos_ShouldReturnPedidos_WhenCalled(){
        //Given
        PedidoDTO pedidoDTO = DTOMapper.pedidoToDTO(this.pedido);

        List<PedidoDTO> expected = new ArrayList<PedidoDTO>();
        expected.add(pedidoDTO);
        //When
        List<PedidoDTO> actual = this.sut.getPedidos();
        //Then
        assertEquals(expected, actual);
    }

    @Test
    void testGetPedido_ShouldReturnPedido_WhenPedidoExiste() throws BusinessException {
        //Given
        PedidoDTO pedidoDTO = DTOMapper.pedidoToDTO(this.pedido);
        Long pedidoId = 1L;
        //When
        PedidoDTO actual = this.sut.getPedido(pedidoId);
        //Then
        assertEquals(pedidoDTO, actual);
    }

    @Test
    void testGetPedido_ShouldThrowBusinessException_WhenPedidoNotExist() throws BusinessException {
        BusinessException businessException = assertThrows(BusinessException.class, ()->{
            this.sut.getPedido(5L);
        });
        assertEquals("El pedido no existe",businessException.getMessage());
    }

    @Test
    void testCrearPedido_ShouldCreatePedido_WhenCalled(){
        //Given
        PedidoDTO pedidoDTO = DTOMapper.pedidoToDTO(this.pedido);
        //When
        this.sut.crearPedido(pedidoDTO);
        //Then
        verify(pedidoRepository, times(1)).save(any());
        verify(productoRepository, times(pedidoDTO.getProductos().size())).save(any());
    }

    @Test
    void testCancelarPedido_ShouldCancelPedido_WhenCalled() throws BusinessException {
        //Given
        Long pedidoId = 1L;
        //When
        this.sut.cancelarPedido(pedidoId);
        //Then
        verify(pedidoRepository, times(1)).delete(any());
        verify(productoRepository, times(this.pedido.getProductos().size())).save(any());
    }

    @Test
    void testCancelarPedido_ShouldTrhowBusinessException_WhenPedidoNotExists() throws BusinessException {
        BusinessException businessException = assertThrows(BusinessException.class, ()->{
            this.sut.cancelarPedido(5L);
        });
        assertEquals("El pedido no existe",businessException.getMessage());
    }

    @Test
    void testAsignarEmpleadoAPedido_ShouldAsignarEmpleado_WhenProductoAndEmpleadoExists() throws BusinessException {
        //Given
        Long empleadoId = 1L;
        Long pedidoId = 1L;
        //When
        this.sut.asignarEmpleadoAPedido(empleadoId, pedidoId);
        //Then
        verify(pedidoRepository, times(1)).findById(pedidoId);
        verify(empleadoRepository, times(1)).findById(empleadoId);
    }

    @Test
    void testAsignarEmpleadoAPedido_ShouldThrowBusinessException_WhenEmpleadoNotExists() throws BusinessException {
        BusinessException businessException = assertThrows(BusinessException.class, ()->{
            this.sut.asignarEmpleadoAPedido(5L, 1L);
        });
        assertEquals("El empleado no existe",businessException.getMessage());
    }

    @Test
    void testAsignarEmpleadoAPedido_ShouldThrowBusinessException_WhenPedidoNotExists() throws BusinessException {
        BusinessException businessException = assertThrows(BusinessException.class, ()->{
            this.sut.asignarEmpleadoAPedido(1L, 5L);
        });
        assertEquals("El pedido no existe",businessException.getMessage());
    }

}