package global.vista;


import global.modelo.EmpleadoContratado;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class VistaEmpleadoContratado extends JFrame {
    private JLabel titulo;
    private JLabel labelNombre = new JLabel(" Nombre", SwingConstants.LEFT);
    private JLabel labelDni = new JLabel(" DNI", SwingConstants.LEFT);
    private JLabel labelDireccion = new JLabel(" Direccion", SwingConstants.LEFT);
    private JLabel labelTelefono = new JLabel(" Telefono", SwingConstants.LEFT);
    private JLabel labelFechaContrato = new JLabel(" Vencimiento Contrato", SwingConstants.LEFT);
    private JLabel labelFuncion = new JLabel(" Función", SwingConstants.LEFT);
    private JLabel labelSueldo = new JLabel(" Sueldo", SwingConstants.LEFT);
    private JTextField textNombre = new JTextField(10);
    private JTextField textDni = new JTextField(10);
    private JTextField textDireccion = new JTextField(10);
    private JTextField textTelefono = new JTextField(10);
    private JTextField textFechaContrato = new JTextField(10);
    private JTextField textSueldo = new JTextField(10);
    private JTextField textFuncion = new JTextField(10);
    private JButton btnGuardar;
    private JButton btnBorrar;
    private JButton btnMostrar;
    private JButton btnEditar;
    private JList<String> empleados = new JList<>();
    private DefaultListModel<String> listaModelo = new DefaultListModel<>();

    public VistaEmpleadoContratado(){
        setTitle("Empleados Contratados");
        setSize(950, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        titulo = new JLabel("Empleados Contratados", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 26));
        add(titulo, BorderLayout.NORTH);

        JPanel atributos = new JPanel();
        atributos.setLayout(new GridLayout(7,1));
        atributos.add(labelNombre);
        atributos.add(labelDni);
        atributos.add(labelDireccion);
        atributos.add(labelTelefono);
        atributos.add(labelFechaContrato);
        atributos.add(labelFuncion);
        atributos.add(labelSueldo);

        add(atributos, BorderLayout.WEST);

        JPanel ingreso = new JPanel();
        ingreso.setLayout(new GridLayout(7,1));
        ingreso.add(textNombre);
        ingreso.add(textDni);
        ingreso.add(textDireccion);
        ingreso.add(textTelefono);
        ingreso.add(textFechaContrato);
        ingreso.add(textFuncion);
        ingreso.add(textSueldo);

        add(ingreso, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnMostrar = new JButton("Mostrar");
        btnEditar = new JButton("Editar");
        btnBorrar = new JButton("Borrar");
        botones.add(btnGuardar);
        botones.add(btnMostrar);
        botones.add(btnEditar);
        botones.add(btnBorrar);

        add(botones, BorderLayout.EAST);

        empleados.setModel(listaModelo);
        add(new JScrollPane(empleados), BorderLayout.SOUTH);
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }
    public JButton getBtnMostrar() {
        return btnMostrar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public String getNombre() {
        String nombre = textNombre.getText();
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) { // Verifica que el nombre solo contenga letras y espacios
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido que contenga solo letras.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null; // Indicador de error
        }
        return nombre;
    }
    public int getDni() {
        try {
            return Integer.parseInt(textDni.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para el DNI.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return -1; // Indicador de error
        }
    }
    public String getDireccion(){
        return textDireccion.getText();
    }

    public int getTelefono() {
        try {
            return Integer.parseInt(textTelefono.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para el Telefono.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return -1; // Indicador de error
        }
    }
    public LocalDate getFechaContrato() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(textFechaContrato.getText(), formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una fecha válida en formato dd/MM/yyyy.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null; // Indicador de error
        }
    }

    public String getFuncion() {
        String funcion = textFuncion.getText();
        if (!funcion.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) { // Verifica que el nombre solo contenga letras y espacios
            JOptionPane.showMessageDialog(this, "Por favor ingrese una funcion válida que contenga solo letras.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null; // Indicador de error
        }
        return funcion;
    }
    public float getSueldo() {
        try {
            return Float.parseFloat(textSueldo.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para el sueldo.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return -1; // Indicador de error
        }
    }

    public void actualizarLista(List<EmpleadoContratado> empleados) {
        listaModelo.clear();
        for (EmpleadoContratado empleado : empleados) {
            listaModelo.addElement(empleado.toString());
        }
    }
    public void limpiarCampos() {
        textNombre.setText("");
        textDni.setText("");
        textDireccion.setText("");
        textTelefono.setText("");
        textFechaContrato.setText("");
        textFuncion.setText("");
        textSueldo.setText("");
    }
    public void cargarEmpleado(EmpleadoContratado empleado) {
        textNombre.setText(empleado.getNombre());
        textDni.setText(String.valueOf(empleado.getEmpleadoDni()));
        textDireccion.setText(empleado.getDireccion());
        textTelefono.setText(String.valueOf(empleado.getTelefono()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        textFechaContrato.setText(empleado.getFechaVencimiento().format(formatter));
        textFuncion.setText(empleado.getFuncion());
        textSueldo.setText(String.valueOf(empleado.getSueldo().getSueldoBase()));
    }
    public JList<String> getListaEmpleados() {
        return empleados;
    }
}


