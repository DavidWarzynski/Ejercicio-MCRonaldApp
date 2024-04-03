package es.neesis.mvcdemo.service.impl;

import es.neesis.mvcdemo.dto.ProductoDTO;
import es.neesis.mvcdemo.exceptions.BusinessException;
import es.neesis.mvcdemo.model.Producto;
import es.neesis.mvcdemo.repository.IProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlmacenServiceTest {

    private AlmacenService sut;
    private IProductoRepository productoRepository;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        this.productoRepository = Mockito.mock(IProductoRepository.class);
        this.sut = new AlmacenService(productoRepository);

        List<Producto> products = new ArrayList<Producto>();
        products.add(new Producto(1L,"Hamburguesa",40));
        products.add(new Producto(2L,"Batido",20));
        Producto producto = new Producto(1L,"Hamburguesa",40);

        when(this.productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(this.productoRepository.findAll()).thenReturn(products);

    }

    @Test
    void testGetProductos_ShouldReturnProductos_WhenCalled(){
        //Given
        List<ProductoDTO> expected = new ArrayList<ProductoDTO>();
        expected.add(new ProductoDTO(1L,"Hamburguesa",40));
        expected.add(new ProductoDTO(2L,"Batido",20));
        //When
        List<ProductoDTO> actual = this.sut.getProductos();
        //Then
        assertEquals(expected, actual);
        verify(this.productoRepository, times(1)).findAll();
    }

    @Test
    void testGetProducto_ShouldReturnProducto_WhenProductoExiste() throws BusinessException {
        //Given
        ProductoDTO expected = new ProductoDTO(1L,"Hamburguesa",40);
        Long productoId = 1L;
        //When
        ProductoDTO actual = this.sut.getProducto(productoId);
        //Then
        assertEquals(expected,actual);
        verify(this.productoRepository, times(1)).findById(productoId);
        //Then
    }

    @Test
    void testGetProducto_ShouldThrowBusinessException_WhenProductoNoExiste() throws BusinessException {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            this.sut.getProducto(5L);
        });
        //Then
        assertEquals("El producto no existe", exception.getMessage());
    }

    @Test
    void testAddProducto_ShouldAddProducto_WhenCalled() throws BusinessException {
        //Given
        ProductoDTO productoDTO = new ProductoDTO(3L,"Helado",40);
        Producto producto = new Producto(3L,"Helado",40);
        //When
        this.sut.addProducto(productoDTO);
        //Then
        verify(this.productoRepository, times(1)).save(producto);
    }

    @Test
    void testDeleteProducto_ShouldDeleteProducto_WhenProductoExiste() throws BusinessException {
        //Given
        Long productoId = 1L;
        //When
        this.sut.deleteProducto(productoId);
        //Then
        verify(this.productoRepository, times(1)).deleteById(productoId);
    }

    @Test
    void testDeleteProducto_ShouldThrowBusinessException_WhenProductoNoExiste() throws BusinessException {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            this.sut.deleteProducto(5L);
        });
        //Then
        assertEquals("El producto no existe", exception.getMessage());
    }

    @Test
    void testGuardarFicheroContenidoAlmacen_ShouldCrearFichero_WhenCalled(){
        //Given
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);
        String nombreArchivo= "contenidoAlmacen_" + fechaFormateada + ".txt";

        Path file = tempDir.resolve(nombreArchivo);
        //When
        this.sut.guardarFicheroContenidoAlmacen(tempDir.toString());
        //Then
        assertAll(
                () -> assertTrue(Files.exists(file),"Exists"),
                () -> assertTrue(file.toFile().length() > 0),
                () -> assertEquals(nombreArchivo, file.toFile().getName())
        );
    }

}