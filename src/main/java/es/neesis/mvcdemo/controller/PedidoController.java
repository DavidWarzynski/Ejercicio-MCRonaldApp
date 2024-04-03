package es.neesis.mvcdemo.controller;

import es.neesis.mvcdemo.dto.EmpleadoPedido;
import es.neesis.mvcdemo.model.Pedido;
import es.neesis.mvcdemo.model.ProductoCarta;
import es.neesis.mvcdemo.exceptions.BusinessException;
import es.neesis.mvcdemo.service.IPedidoService;
import es.neesis.mvcdemo.utils.DTOMapper;
import es.neesis.mvcdemo.utils.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping("/lista")
    @ResponseBody
    public void getPedidos(Model model) {
        model.addAttribute("pedido", pedidoService.getPedidos());
    }

    @GetMapping("/obtener/{pedidoId}")
    @ResponseBody
    public void getPedido(@PathVariable Long pedidoId, Model model) throws BusinessException {
        model.addAttribute("pedido", pedidoService.getPedido(pedidoId));
    }

    @PostMapping("/crear")
    @ResponseBody
    public void crearPedido(@RequestBody List<ProductoCarta> productosCarta, Model model) {
        Pedido pedido = new Pedido();
        pedido.setProductos(ProductoMapper.crearProductoPedido(productosCarta));
        pedidoService.crearPedido(DTOMapper.pedidoToDTO(pedido));
        model.addAttribute("pedido", pedidoService.getPedidos());
    }

    @DeleteMapping("/cancelar/{pedidoId}")
    @ResponseBody
    public void cancelarPedido(@PathVariable Long pedidoId, Model model) throws BusinessException {
        pedidoService.cancelarPedido(pedidoId);
        model.addAttribute("pedido", pedidoService.getPedidos());
    }

    @PutMapping("/modificar")
    @ResponseBody
    public void modificarPedido(@RequestBody Pedido pedido, Model model) throws BusinessException {
        pedidoService.modificarPedido(DTOMapper.pedidoToDTO(pedido));
        model.addAttribute("pedido", pedidoService.getPedidos());
    }

    @PostMapping("/asignarEmpleado")
    @ResponseBody
    public void asignarEmpleadoAPedido(@RequestBody EmpleadoPedido empleadoPedido, Model model)
            throws BusinessException {
        Long empleadoId = empleadoPedido.getEmpleadoId();
        Long pedidoId = empleadoPedido.getPedidoId();
        pedidoService.asignarEmpleadoAPedido(empleadoId, pedidoId);
        model.addAttribute("pedido", pedidoService.getPedidos());
    }

}
