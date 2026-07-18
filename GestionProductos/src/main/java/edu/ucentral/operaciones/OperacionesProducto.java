package edu.ucentral.operaciones;

import edu.ucentral.dao.ImpOperacionesProducto;
import edu.ucentral.dto.Producto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Getter
public class OperacionesProducto {
    private List<Producto> productos;
    private ImpOperacionesProducto impOperacionesProducto;

    public OperacionesProducto(){
        this.productos = new ArrayList<>();
        this.impOperacionesProducto = new ImpOperacionesProducto();
        // buscar en base datos si existe algo y lo carga
        this.productos = this.impOperacionesProducto.obtenerTodos();
    }
    public void adicionar(Producto producto){
        // almaceno en base de datos
        this.impOperacionesProducto.crear(producto);
        // adiciona a lista local
        this.productos.add(producto);
    }
    public void imprimir(List<Producto> listaImprimir){
        listaImprimir
                .stream()
                .forEach(
                        elproducto -> System.out.println(
                                " Codigo "+ elproducto.getCodigo()+
                                " Nombre "+ elproducto.getNombre()+
                                " Tipo "+elproducto.getTipoProducto().name()+
                                " Existencias "+elproducto.getExistencias()+
                                " Precio compra $"+elproducto.getPrecioCompra()
                        )
                );
    }
    public void imprimirOrdenado(){
        List<Producto> lista = this.productos.stream()
                .sorted(Comparator
                        .comparing(Producto::getTipoProducto)
                        .thenComparing(Producto::getExistencias))
                .toList();
        this.imprimir(lista);

    }



}
