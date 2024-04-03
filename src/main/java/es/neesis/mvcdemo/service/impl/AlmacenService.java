package es.neesis.mvcdemo.service.impl;

import es.neesis.mvcdemo.dto.ProductoDTO;
import es.neesis.mvcdemo.model.Producto;
import es.neesis.mvcdemo.repository.IProductoRepository;
import es.neesis.mvcdemo.exceptions.BusinessException;
import es.neesis.mvcdemo.service.IAlmacenService;
import es.neesis.mvcdemo.utils.BusinessChecks;
import es.neesis.mvcdemo.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService implements IAlmacenService {

    private IProductoRepository productoRepository;

    public AlmacenService(IProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> getProductos() {
        return DTOMapper.productoListToDTO(productoRepository.findAll());
    }

    @Override
    public ProductoDTO getProducto(Long productoId) throws BusinessException {
        Optional<Producto> producto = productoRepository.findById(productoId);
        BusinessChecks.exists(producto, "El producto no existe");

        return DTOMapper.productoToDTO(producto.get());
    }

    @Override
    public void addProducto(ProductoDTO producto) {
        productoRepository.save(DTOMapper.dtoToProducto(producto));
    }

    @Override
    public void deleteProducto(Long productoId) throws BusinessException {
        Optional<Producto> producto = productoRepository.findById(productoId);
        BusinessChecks.exists(producto, "El producto no existe");

        productoRepository.deleteById(productoId);
    }
}
