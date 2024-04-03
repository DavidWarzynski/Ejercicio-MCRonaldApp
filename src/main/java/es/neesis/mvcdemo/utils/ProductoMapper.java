package es.neesis.mvcdemo.utils;

import es.neesis.mvcdemo.model.Pedido;
import es.neesis.mvcdemo.model.Producto;
import es.neesis.mvcdemo.model.ProductoCarta;
import es.neesis.mvcdemo.model.ProductoPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoMapper {

 public static List<ProductoPedido> crearProductoPedido(List<ProductoCarta> productos){
     List<ProductoPedido> listaProductosPedido = new ArrayList<>();
     Map<ProductoCarta, Integer> productoPrecio = new HashMap<>();
     productos.forEach(producto -> {
         if(productoPrecio.containsKey(producto)){
             productoPrecio.merge(producto, 1, Integer::sum);
         }else{
             productoPrecio.put(producto, 1);
         }
     });
     for (Map.Entry<ProductoCarta, Integer> entry : productoPrecio.entrySet()) {
         for(int i = 0; i < entry.getValue(); i++){
             ProductoPedido productoPedido = new ProductoPedido();
             productoPedido.setProductoCarta(entry.getKey());
             productoPedido.setProductAmount(entry.getValue());
             listaProductosPedido.add(productoPedido);
         }
     }
     return listaProductosPedido;
 }

}
