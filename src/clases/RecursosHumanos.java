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
public class RecursosHumanos {

    String documento;
    String razonSocial;
    String cargo;
    
    public RecursosHumanos() {

    }

    public RecursosHumanos(String documento, String razonSocial, String cargo) {
        this.documento = documento;
        this.razonSocial = razonSocial;
        this.cargo = cargo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
    
    public void crearArchivoRRHH() {
        try {
            File objetoArchivo = new File("RRHH.txt");
            if (objetoArchivo.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Se ha creado correctamente el archivo: " + objetoArchivo.getName());
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe");
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al crear el archivo");

        }
    }

    public void agregarRegistrosFuncionario() {
        try {
            FileWriter fw = new FileWriter("RRHH.txt", true);

            fw.write(getDocumento());
            fw.write(",");
            fw.write(getRazonSocial());
            fw.write(",");
            fw.write(getCargo());
            fw.write("\n");
            fw.close();

            JOptionPane.showMessageDialog(null, "Se registró correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al registrar" + e.toString());
        }
    }

    public void MostrarTotalRRHH(JTable tablaTotalRRHH) {

        String nombreArchivo = "RRHH.txt";

        File file = new File(nombreArchivo);

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String primeraLinea = br.readLine().trim();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Documento");
            model.addColumn("RazonSocial");
            model.addColumn("Cargo");

            tablaTotalRRHH.setModel(model);

            Object[] tableLines = br.lines().toArray();

            for (int i = 0; i < tableLines.length; i++) {

                String line = tableLines[i].toString().trim();
                String[] datarow = line.split(",");
                model.addRow(datarow);
                tablaTotalRRHH.setModel(model);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error" + e.toString());

        }
    }

    public void seleccionarFuncionario(JTable tablaFuncionario) {

        try {

            int fila = tablaFuncionario.getSelectedRow();

            if (fila >= 0) {

                setDocumento(tablaFuncionario.getValueAt(fila, 0).toString());
                setRazonSocial(tablaFuncionario.getValueAt(fila, 1).toString());
                setCargo(tablaFuncionario.getValueAt(fila, 2).toString());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error" + e.toString());
        }

    }

    public void EliminarFuncionario(JTable tablaFuncionario, JTextField documento) {

        //Eliminaci�n visual de la tabla
        DefaultTableModel model = (DefaultTableModel) tablaFuncionario.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {

            if (((String) model.getValueAt(i, 0)).equals(documento.getText())) {
                model.removeRow(i);
                break;

            }
        }
        //Limpieza del archivo .txt

        try {
            PrintWriter writer = new PrintWriter("RRHH.txt");
            writer.print("");
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un problema" + e.toString());
        }

        //Creaci�n de los nuevos registros luego de la eliminaci�n
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("RRHH.txt")))) {
            StringJoiner joiner = new StringJoiner(",");

            for (int col = 0; col < tablaFuncionario.getColumnCount(); col++) {
                joiner.add(tablaFuncionario.getColumnName(col));
            }

            System.out.println(joiner.toString());
            bw.write(joiner.toString());
            bw.newLine();

            for (int row = 0; row < tablaFuncionario.getRowCount(); row++) {
                joiner = new StringJoiner(",");
                for (int col = 0; col < tablaFuncionario.getColumnCount(); col++) {

                    Object obj = tablaFuncionario.getValueAt(row, col);
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

    public void EditarFuncionario(JTable tablaRRHH) {

        //Limpieza del archivo .txt
        try {
            PrintWriter writer = new PrintWriter("RRHH.txt");
            writer.print("");
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un problema" + e.toString());
        }

        //Creaci�n de los nuevos registros luego de la eliminaci�n
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("RRHH.txt")))) {
            StringJoiner joiner = new StringJoiner(",");
            for (int col = 0; col < tablaRRHH.getColumnCount(); col++) {
                joiner.add(tablaRRHH.getColumnName(col));
            }

            System.out.println(joiner.toString());
            bw.write(joiner.toString());
            bw.newLine();

            for (int row = 0; row < tablaRRHH.getRowCount(); row++) {
                joiner = new StringJoiner(",");
                for (int col = 0; col < tablaRRHH.getColumnCount(); col++) {

                    Object obj = tablaRRHH.getValueAt(row, col);
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
