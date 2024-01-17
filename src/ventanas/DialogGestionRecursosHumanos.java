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
public class DialogGestionRecursosHumanos extends JDialog {

    private JTextField txtDocumentoFuncionario;
    private JTextField txtRazonSocialFuncionario;
    private JTextField txtCargoFuncionario;
    private JTable tbListaFuncionario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DialogGestionRecursosHumanos dialog = new DialogGestionRecursosHumanos();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DialogGestionRecursosHumanos() {
        setTitle("GESTION DE FUNCIONARIOS");
        setBounds(100, 100, 633, 313);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Datos de Funcionario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 55, 239, 214);
        getContentPane().add(panel);
        panel.setLayout(null);

        txtDocumentoFuncionario = new JTextField();
        txtDocumentoFuncionario.setBounds(132, 24, 86, 20);
        panel.add(txtDocumentoFuncionario);
        txtDocumentoFuncionario.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Documento");
        lblNewLabel_3.setBounds(10, 27, 70, 14);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Razon social");
        lblNewLabel_4.setBounds(10, 68, 112, 14);
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Cargo");
        lblNewLabel_5.setBounds(10, 106, 112, 14);
        panel.add(lblNewLabel_5);

        txtRazonSocialFuncionario = new JTextField();
        txtRazonSocialFuncionario.setBounds(132, 65, 86, 20);
        panel.add(txtRazonSocialFuncionario);
        txtRazonSocialFuncionario.setColumns(10);

        txtCargoFuncionario = new JTextField();
        txtCargoFuncionario.setBounds(132, 103, 86, 20);
        panel.add(txtCargoFuncionario);
        txtCargoFuncionario.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.RecursosHumanos objetoFuncionario = new clases.RecursosHumanos();
                objetoFuncionario.setDocumento(txtDocumentoFuncionario.getText());
                objetoFuncionario.setRazonSocial(txtRazonSocialFuncionario.getText());
                objetoFuncionario.setCargo(txtCargoFuncionario.getText());
                objetoFuncionario.agregarRegistrosFuncionario();
            }
        });
        btnGuardar.setBounds(10, 140, 99, 23);
        panel.add(btnGuardar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.RecursosHumanos objetoFuncionario = new clases.RecursosHumanos();
                objetoFuncionario.EditarFuncionario(tbListaFuncionario);
            }
        });
        btnEditar.setBounds(109, 140, 112, 23);
        panel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.RecursosHumanos objetoFuncionario = new clases.RecursosHumanos();
                objetoFuncionario.EliminarFuncionario(tbListaFuncionario, txtDocumentoFuncionario);
            }
        });
        btnEliminar.setBounds(10, 168, 208, 29);
        panel.add(btnEliminar);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Lista de Funcionarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(275, 54, 338, 177);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 30, 318, 136);
        panel_1.add(scrollPane);

        tbListaFuncionario = new JTable();
        scrollPane.setViewportView(tbListaFuncionario);

        JButton btnCrearArchivoFuncionario = new JButton("Crear Archivo de Funcionarios");
        btnCrearArchivoFuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.RecursosHumanos objetoFuncionario = new clases.RecursosHumanos();
                objetoFuncionario.crearArchivoRRHH();
            }
        });
        btnCrearArchivoFuncionario.setBounds(10, 21, 239, 23);
        getContentPane().add(btnCrearArchivoFuncionario);

        JButton btnMostrarFuncionarios = new JButton("Mostrar Funcionarios");
        btnMostrarFuncionarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.RecursosHumanos objetoFuncionario = new clases.RecursosHumanos();
                objetoFuncionario.MostrarTotalRRHH(tbListaFuncionario);
            }
        });
        btnMostrarFuncionarios.setBounds(275, 21, 158, 23);
        getContentPane().add(btnMostrarFuncionarios);

        JButton btnSeleccionar = new JButton("Seleccionar Funcionario");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.RecursosHumanos objetoFuncionario = new clases.RecursosHumanos();

                objetoFuncionario.seleccionarFuncionario(tbListaFuncionario);
                txtDocumentoFuncionario.setText(objetoFuncionario.getDocumento());
                txtRazonSocialFuncionario.setText(objetoFuncionario.getRazonSocial());
                txtCargoFuncionario.setText(objetoFuncionario.getCargo());
            }
        });
        btnSeleccionar.setBounds(443, 20, 164, 23);
        getContentPane().add(btnSeleccionar);
    }

}
