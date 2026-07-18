package edu.ucentral;

import edu.ucentral.dto.Producto;
import edu.ucentral.dto.TipoProducto;
import edu.ucentral.operaciones.OperacionesProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class VentanaProducto extends JFrame {

    private OperacionesProducto operacionesProducto;

    // Campos del formulario
    private JTextField txtNombre;
    private JTextField txtExistencias;
    private JTextField txtPrecioCompra;
    private JTextField txtPrecioVenta;
    private JTextField txtProveedor;
    private JTextField txtFechaVencimiento;
    private JComboBox<TipoProducto> cmbTipo;

    // Tabla
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public VentanaProducto() {
        operacionesProducto = new OperacionesProducto();
        iniciarVentana();
    }

    private void iniciarVentana() {
        setTitle("Gestión de Productos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel del formulario arriba
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Nuevo Producto"));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Tipo de Producto:"));
        cmbTipo = new JComboBox<>(TipoProducto.values());
        panelFormulario.add(cmbTipo);

        panelFormulario.add(new JLabel("Existencias:"));
        txtExistencias = new JTextField();
        panelFormulario.add(txtExistencias);

        panelFormulario.add(new JLabel("Precio Compra:"));
        txtPrecioCompra = new JTextField();
        panelFormulario.add(txtPrecioCompra);

        panelFormulario.add(new JLabel("Precio Venta:"));
        txtPrecioVenta = new JTextField();
        panelFormulario.add(txtPrecioVenta);

        panelFormulario.add(new JLabel("Proveedor:"));
        txtProveedor = new JTextField();
        panelFormulario.add(txtProveedor);

        panelFormulario.add(new JLabel("Fecha Vencimiento (YYYY-MM-DD):"));
        txtFechaVencimiento = new JTextField();
        panelFormulario.add(txtFechaVencimiento);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarProducto());
        panelFormulario.add(new JLabel());
        panelFormulario.add(btnGuardar);

        // Tabla abajo
        String[] columnas = {"Código", "Nombre", "Tipo", "Existencias", "Precio Compra", "Precio Venta", "Proveedor"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Productos registrados"));

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        // Cargar productos existentes al abrir
        cargarTabla();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void guardarProducto() {
        try {
            String nombre = txtNombre.getText();
            int existencias = Integer.parseInt(txtExistencias.getText());
            long precioCompra = Long.parseLong(txtPrecioCompra.getText());
            long precioVenta = Long.parseLong(txtPrecioVenta.getText());
            String proveedor = txtProveedor.getText();
            LocalDate fecha = LocalDate.parse(txtFechaVencimiento.getText());
            TipoProducto tipo = (TipoProducto) cmbTipo.getSelectedItem();

            Producto producto = Producto.builder()
                    .nombre(nombre)
                    .existencias(existencias)
                    .precioCompra(precioCompra)
                    .precioVenta(precioVenta)
                    .proveedor(proveedor)
                    .fechaVencimiento(fecha)
                    .tipoProducto(tipo)
                    .build();

            operacionesProducto.adicionar(producto);
            cargarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Producto guardado correctamente.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0); // limpiar tabla
        for (Producto p : operacionesProducto.getProductos()) {
            modeloTabla.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getTipoProducto().name(),
                    p.getExistencias(),
                    p.getPrecioCompra(),
                    p.getPrecioVenta(),
                    p.getProveedor()
            });
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtExistencias.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtProveedor.setText("");
        txtFechaVencimiento.setText("");
        cmbTipo.setSelectedIndex(0);
    }
}