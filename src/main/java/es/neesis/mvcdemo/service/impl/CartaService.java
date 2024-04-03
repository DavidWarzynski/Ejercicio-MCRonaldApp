package es.neesis.mvcdemo.service.impl;

import es.neesis.mvcdemo.dto.ProductoCartaDTO;
import es.neesis.mvcdemo.model.ProductoCarta;
import es.neesis.mvcdemo.model.Producto;
import es.neesis.mvcdemo.repository.ICartaRepository;
import es.neesis.mvcdemo.repository.IProductoRepository;
import es.neesis.mvcdemo.exceptions.BusinessException;
import es.neesis.mvcdemo.service.ICartaService;
import es.neesis.mvcdemo.utils.BusinessChecks;
import es.neesis.mvcdemo.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaService implements ICartaService {
    @Autowired
    private ICartaRepository cartaRepository;
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<ProductoCartaDTO> getProductos() {
        return DTOMapper.productoCartaListToDTO(cartaRepository.findAll());
    }

    @Override
    public void addProducto(ProductoCartaDTO productoCartaDTO) throws BusinessException {
        ProductoCarta productoCarta = DTOMapper.dtoToProductoCarta(productoCartaDTO);
        Optional<Producto> productoAlmacen = productoRepository.findById(productoCarta.getProducto().getId());

        BusinessChecks.exists(productoAlmacen, "No existe el producto en el almacen");
        BusinessChecks.isTrue(productoAlmacen.get().getStockDisponible() > 0,
                "No hay existencias del producto en almacen");

        cartaRepository.save(productoCarta);
    }

    @Override
    public void updateProducto(ProductoCartaDTO productoCartaDTO) throws BusinessException {
        ProductoCarta productoCarta = DTOMapper.dtoToProductoCarta(productoCartaDTO);
        Optional<ProductoCarta> productoCartaExistente = cartaRepository.findById(productoCarta.getId());
        BusinessChecks.exists(productoCartaExistente, "No existe el producto en el almacen");

        productoCartaExistente.get().setPrecio(productoCarta.getPrecio());
    }

    @Override
    public void removeProducto(Long productoId) throws BusinessException {
        Optional<ProductoCarta> productoCarta = cartaRepository.findById(productoId);
        BusinessChecks.exists(productoCarta, "El prudcto de la carta no existe");

        cartaRepository.delete(productoCarta.get());
    }
}
