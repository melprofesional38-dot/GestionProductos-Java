package edu.ucentral.dao;

import edu.ucentral.dto.Producto;
import edu.ucentral.dto.TipoProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ImpOperacionesProducto implements OperacionesGN<Producto>{

    public void crear(Producto entidad){
        String sql = "insert into productos (tipoproducto, nombre, existencias, preciocompra, precioventa, " +
                " proveedor, fechavencimiento) " +
                " values (?, ?,?, ?, ?, ?, ?);";
        try(Connection conexion = GestorConexion.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
        ){
            ps.setString(1, entidad.getTipoProducto().toString());
            ps.setString(2, entidad.getNombre());
            ps.setInt(3, entidad.getExistencias());
            ps.setLong(4, entidad.getPrecioCompra());
            ps.setLong(5, entidad.getPrecioVenta());
            ps.setString(6, entidad.getProveedor());
            ps.setDate(7, java.sql.Date.valueOf(entidad.getFechaVencimiento()));

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }

    }
    public List<Producto> obtenerTodos() {
        List<Producto> listado = new ArrayList<>();
        String sql = "select * from productos";
        try(Connection conexion = GestorConexion.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
        ){
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()){
                Long codigo = resultado.getLong("codigo");
                String tipoproducto = resultado.getString("tipoproducto");
                String nombre = resultado.getString("nombre");
                Long existencias = resultado.getLong("existencias");
                Long preciocompra = resultado.getLong("preciocompra");
                Long precioventa = resultado.getLong("precioventa");
                String proveedor = resultado.getString("proveedor");
                LocalDate fecha = resultado.getDate("fechavencimiento").toLocalDate();


                Producto producto = Producto
                        .builder()
                        .codigo(codigo)
                        .nombre(nombre)
                        .existencias(existencias.intValue())
                        .precioCompra(preciocompra)
                        .precioVenta(precioventa)
                        .tipoProducto(TipoProducto.valueOf(tipoproducto.toUpperCase()))
                        .proveedor(proveedor)
                        .fechaVencimiento(fecha)
                        .build();
                listado.add(producto);

            }

        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }

        return listado;
    }

}
