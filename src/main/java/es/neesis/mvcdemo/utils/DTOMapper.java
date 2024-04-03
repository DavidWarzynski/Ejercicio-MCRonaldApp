package es.neesis.mvcdemo.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import es.neesis.mvcdemo.dto.*;
import es.neesis.mvcdemo.model.*;

import java.util.List;

public class DTOMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static EmpleadoDTO empladoToDTO(Empleado empleado) {
        return mapper.map(empleado, EmpleadoDTO.class);
    }

    public static Empleado dtoToEmpleado(EmpleadoDTO empleado) {
        return mapper.map(empleado, Empleado.class);
    }

    public static List<EmpleadoDTO> empleadoListToDTO(List<Empleado> empleados) {
        return empleados.stream().map(empleado -> mapper.map(empleado, EmpleadoDTO.class)).toList();
    }

    public static List<Empleado> empleadoDtoToList(List<EmpleadoDTO> empleados) {
        return empleados.stream().map(empleado -> mapper.map(empleado, Empleado.class)).toList();
    }

    public static ProductoDTO productoToDTO(Producto producto) {
        return mapper.map(producto, ProductoDTO.class);
    }

    public static Producto dtoToProducto(ProductoDTO producto) {
        return mapper.map(producto, Producto.class);
    }

    public static List<ProductoDTO> productoListToDTO(List<Producto> productos) {
        return productos.stream().map(producto -> mapper.map(producto, ProductoDTO.class)).toList();
    }

    public static List<Producto> productoDtoToList(List<ProductoDTO> productos) {
        return productos.stream().map(producto -> mapper.map(producto, Producto.class)).toList();
    }

    public static PedidoDTO pedidoToDTO(Pedido pedido) {
        return mapper.map(pedido, PedidoDTO.class);
    }

    public static Pedido dtoToPedido(PedidoDTO pedido) {
        return mapper.map(pedido, Pedido.class);
    }

    public static List<PedidoDTO> pedidoListToDTO(List<Pedido> pedidos) {
        return pedidos.stream().map(pedido -> mapper.map(pedido, PedidoDTO.class)).toList();
    }

    public static List<Pedido> pedidoDtoListToPedido(List<PedidoDTO> pedidoDTOS) {
        return pedidoDTOS.stream().map(pedido -> mapper.map(pedido, Pedido.class)).toList();
    }

    public static ProductoCartaDTO productoCartaToDTO(ProductoCarta productoCarta) {
        return mapper.map(productoCarta, ProductoCartaDTO.class);
    }
    public static ProductoCarta dtoToProductoCarta(ProductoCartaDTO productoCarta) {
        return mapper.map(productoCarta, ProductoCarta.class);
    }
    public static List<ProductoCartaDTO> productoCartaListToDTO(List<ProductoCarta> productoCartas) {
        return productoCartas.stream().map(productoCarta -> mapper.map(productoCarta, ProductoCartaDTO.class)).toList();
    }
    public static List<ProductoCarta> productoCartaDtoToList(List<ProductoCartaDTO> productoCartas) {
        return productoCartas.stream().map(productoCarta -> mapper.map(productoCarta, ProductoCarta.class)).toList();
    }
    public static ProductoPedidoDTO productoPedidoToDTO(ProductoPedido productoPedido) {
        return mapper.map(productoPedido, ProductoPedidoDTO.class);
    }
    public static ProductoPedido dtoToProductoPedido(ProductoPedidoDTO productoPedido) {
        return mapper.map(productoPedido, ProductoPedido.class);
    }
    public static List<ProductoPedidoDTO> productoPedidoListToDTO(List<ProductoPedido> productoPedidos) {
        return productoPedidos.stream().map(productoPedido -> mapper.map(productoPedido, ProductoPedidoDTO.class)).toList();
    }
    public static List<ProductoPedido> productoPedidoDtoToList(List<ProductoPedidoDTO> productoPedidos) {
        return productoPedidos.stream().map(productoPedido -> mapper.map(productoPedido, ProductoPedido.class)).toList();
    }


}
