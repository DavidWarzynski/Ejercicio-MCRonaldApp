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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void guardarFicheroContenidoAlmacen(String directorio) {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);
        String nombreArchivo= "contenidoAlmacen_" + fechaFormateada + ".txt";
        String rutaCompleta = directorio+"/"+nombreArchivo;
        try {
            FileWriter fileWriter = new FileWriter(rutaCompleta, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String contenidoAlmacen = getProductos().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
            bufferedWriter.write(contenidoAlmacen);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
