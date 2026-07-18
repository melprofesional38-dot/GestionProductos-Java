package edu.ucentral.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Producto {
    private Long codigo;
    private TipoProducto tipoProducto;
    private String nombre;
    private int existencias;
    private Long precioCompra;
    private Long precioVenta;
    private String proveedor;
    private LocalDate fechaVencimiento;


}
