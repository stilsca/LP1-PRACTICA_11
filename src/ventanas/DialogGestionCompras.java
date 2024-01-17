package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
/**
 *
 * @author Stilver Scavone
 */
public class DialogGestionCompras extends JDialog {

    private JTextField txtIdCompra;
    private JTextField txtProveedorCompra;
    private JTextField txtFechaCompra;
    private JTable tbListaCompras;

    public static void main(String[] args) {
        try {
            DialogGestionCompras dialog = new DialogGestionCompras();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DialogGestionCompras() {
        setTitle("GESTION DE COMPRAS");
        setBounds(100, 100, 633, 313);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Datos de Compras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 55, 239, 214);
        getContentPane().add(panel);
        panel.setLayout(null);

        txtIdCompra = new JTextField();
        txtIdCompra.setBounds(132, 24, 86, 20);
        panel.add(txtIdCompra);
        txtIdCompra.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Codigo");
        lblNewLabel_3.setBounds(10, 27, 46, 14);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Proveedor");
        lblNewLabel_4.setBounds(10, 68, 112, 14);
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Fecha");
        lblNewLabel_5.setBounds(10, 106, 112, 14);
        panel.add(lblNewLabel_5);

        txtProveedorCompra = new JTextField();
        txtProveedorCompra.setBounds(132, 65, 86, 20);
        panel.add(txtProveedorCompra);
        txtProveedorCompra.setColumns(10);

        txtFechaCompra = new JTextField();
        txtFechaCompra.setBounds(132, 103, 86, 20);
        panel.add(txtFechaCompra);
        txtFechaCompra.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompra = new clases.Compras();
                objetoCompra.setIdCompra(txtIdCompra.getText());
                objetoCompra.setProveedor(txtProveedorCompra.getText());
                objetoCompra.setFecha(txtFechaCompra.getText());
                objetoCompra.agregarRegistrosCompras();
            }
        });
        btnGuardar.setBounds(10, 140, 99, 23);
        panel.add(btnGuardar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompra = new clases.Compras();
                objetoCompra.EditarFuncionario(tbListaCompras);
            }
        });
        btnEditar.setBounds(109, 140, 112, 23);
        panel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompra = new clases.Compras();
                objetoCompra.EliminarCompra(tbListaCompras, txtIdCompra);
            }
        });
        btnEliminar.setBounds(10, 168, 208, 29);
        panel.add(btnEliminar);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Lista de Compras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(275, 54, 338, 177);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 30, 318, 136);
        panel_1.add(scrollPane);

        tbListaCompras = new JTable();
        scrollPane.setViewportView(tbListaCompras);

        JButton btnCrearArchivoCompras = new JButton("Crear Archivo de Compras");
        btnCrearArchivoCompras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompra = new clases.Compras();
                objetoCompra.crearArchivoCompra();
            }
        });
        btnCrearArchivoCompras.setBounds(10, 21, 239, 23);
        getContentPane().add(btnCrearArchivoCompras);

        JButton btnMostrarCompras = new JButton("Mostrar Compras");
        btnMostrarCompras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompra = new clases.Compras();
                objetoCompra.MostrarTotalCompra(tbListaCompras);
            }
        });
        btnMostrarCompras.setBounds(275, 21, 158, 23);
        getContentPane().add(btnMostrarCompras);

        JButton btnSeleccionar = new JButton("Seleccionar Compra");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompra = new clases.Compras();

                objetoCompra.seleccionarCompra(tbListaCompras);
                txtIdCompra.setText(objetoCompra.getIdCompra());
                txtProveedorCompra.setText(objetoCompra.getProveedor());
                txtFechaCompra.setText(objetoCompra.getFecha());
            }
        });
        btnSeleccionar.setBounds(443, 20, 164, 23);
        getContentPane().add(btnSeleccionar);
    }

}
