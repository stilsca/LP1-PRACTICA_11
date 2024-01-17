package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringJoiner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Stilver Scavone
 */
public class Compras {

    String idCompra;
    String proveedor;
    String fecha;

    public Compras() {

    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public void crearArchivoCompra() {
        try {
            File objetoArchivo = new File("Compra.txt");
            if (objetoArchivo.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Se ha creado correctamente el archivo: " + objetoArchivo.getName());
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe");
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al crear el archivo");

        }
    }

    public void agregarRegistrosCompras() {
        try {
            FileWriter fw = new FileWriter("Compra.txt", true);

            fw.write(getIdCompra());
            fw.write(",");
            fw.write(getProveedor());
            fw.write(",");
            fw.write(getFecha());
            fw.write("\n");
            fw.close();

            JOptionPane.showMessageDialog(null, "Se registró correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al registrar" + e.toString());
        }
    }

    public void MostrarTotalCompra(JTable tablaTotalCompra) {

        String nombreArchivo = "Compra.txt";

        File file = new File(nombreArchivo);

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String primeraLinea = br.readLine().trim();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Id");
            model.addColumn("Proveedor");
            model.addColumn("Fecha");

            tablaTotalCompra.setModel(model);

            Object[] tableLines = br.lines().toArray();

            for (int i = 0; i < tableLines.length; i++) {

                String line = tableLines[i].toString().trim();
                String[] datarow = line.split(",");
                model.addRow(datarow);
                
            }
            tablaTotalCompra.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error" + e.toString());

        }
    }

    public void seleccionarCompra(JTable tablaCompra) {

        try {

            int fila = tablaCompra.getSelectedRow();

            if (fila >= 0) {

                setIdCompra(tablaCompra.getValueAt(fila, 0).toString());
                setProveedor(tablaCompra.getValueAt(fila, 1).toString());
                setFecha(tablaCompra.getValueAt(fila, 2).toString());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error" + e.toString());
        }

    }

    public void EliminarCompra(JTable tablaCompra, JTextField id) {

        //Eliminaci�n visual de la tabla
        DefaultTableModel model = (DefaultTableModel) tablaCompra.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {

            if (((String) model.getValueAt(i, 0)).equals(id.getText())) {
                model.removeRow(i);
                break;

            }
        }
        //Limpieza del archivo .txt

        try {
            PrintWriter writer = new PrintWriter("Compra.txt");
            writer.print("");
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un problema" + e.toString());
        }

        //Creaci�n de los nuevos registros luego de la eliminaci�n
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Compra.txt")))) {
            StringJoiner joiner = new StringJoiner(",");

            for (int col = 0; col < tablaCompra.getColumnCount(); col++) {
                joiner.add(tablaCompra.getColumnName(col));
            }

            System.out.println(joiner.toString());
            bw.write(joiner.toString());
            bw.newLine();

            for (int row = 0; row < tablaCompra.getRowCount(); row++) {
                joiner = new StringJoiner(",");
                for (int col = 0; col < tablaCompra.getColumnCount(); col++) {

                    Object obj = tablaCompra.getValueAt(row, col);
                    String value = obj == null ? "null" : obj.toString();
                    joiner.add(value);

                }

                bw.write(joiner.toString());
                bw.newLine();
                JOptionPane.showMessageDialog(null, "Se elimino correctamente");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error");
        }

    }

    public void EditarFuncionario(JTable tablaCompra) {

        //Limpieza del archivo .txt
        try {
            PrintWriter writer = new PrintWriter("Compra.txt");
            writer.print("");
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un problema" + e.toString());
        }

        //Creaci�n de los nuevos registros luego de la eliminaci�n
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Compra.txt")))) {
            StringJoiner joiner = new StringJoiner(",");
            for (int col = 0; col < tablaCompra.getColumnCount(); col++) {
                joiner.add(tablaCompra.getColumnName(col));
            }

            System.out.println(joiner.toString());
            bw.write(joiner.toString());
            bw.newLine();

            for (int row = 0; row < tablaCompra.getRowCount(); row++) {
                joiner = new StringJoiner(",");
                for (int col = 0; col < tablaCompra.getColumnCount(); col++) {

                    Object obj = tablaCompra.getValueAt(row, col);
                    String value = obj == null ? "null" : obj.toString();
                    joiner.add(value);

                }

                System.out.println(joiner.toString());
                bw.write(joiner.toString());
                bw.newLine();
                //JOptionPane.showMessageDialog(null, "Se modific� correctamente");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error");
        }

    }

}
