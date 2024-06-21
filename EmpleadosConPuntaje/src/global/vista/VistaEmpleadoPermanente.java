package global.vista;

import global.modelo.EmpleadoContratado;
import global.modelo.EmpleadoPermanente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaEmpleadoPermanente extends JFrame {
    private JLabel titulo;
    private JLabel labelNombre = new JLabel(" Nombre", SwingConstants.LEFT);
    private JLabel labelDni = new JLabel(" DNI", SwingConstants.LEFT);
    private JLabel labelDireccion = new JLabel(" Direccion", SwingConstants.LEFT);
    private JLabel labelTelefono = new JLabel(" Telefono", SwingConstants.LEFT);
    private JLabel labelSueldo = new JLabel(" Sueldo", SwingConstants.LEFT);
    private JTextField textNombre = new JTextField(10);
    private JTextField textDni = new JTextField(10);
    private JTextField textDireccion = new JTextField(10);
    private JTextField textTelefono = new JTextField(10);
    private JTextField textSueldo = new JTextField(10);
    private JButton btnGuardar;
    private JButton btnMostrar;
    private JButton btnEditar;
    private JButton btnRealizarCurso;
    private JList<String> empleados = new JList<>();
    private DefaultListModel<String> listaModelo = new DefaultListModel<>();

    public VistaEmpleadoPermanente(){
        setTitle("Empleados Contratados");
        setSize(950, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        titulo = new JLabel("Empleados Permanentes", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 26));
        add(titulo, BorderLayout.NORTH);

        JPanel atributos = new JPanel();
        atributos.setLayout(new GridLayout(5,1));
        atributos.add(labelNombre);
        atributos.add(labelDni);
        atributos.add(labelDireccion);
        atributos.add(labelTelefono);
        atributos.add(labelSueldo);

        add(atributos, BorderLayout.WEST);

        JPanel ingreso = new JPanel();
        ingreso.setLayout(new GridLayout(5,1));
        ingreso.add(textNombre);
        ingreso.add(textDni);
        ingreso.add(textDireccion);
        ingreso.add(textTelefono);
        ingreso.add(textSueldo);

        add(ingreso, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnMostrar = new JButton("Mostrar");
        btnEditar = new JButton("Editar");
        btnRealizarCurso = new JButton("Realizar curso");
        botones.add(btnGuardar);
        botones.add(btnMostrar);
        botones.add(btnEditar);
        botones.add(btnRealizarCurso);

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

    public JButton getBtnRealizarCurso() {
        return btnRealizarCurso;
    }

    public String getNombre(){
        return textNombre.getText();
    }
    public int getDni(){
        return Integer.parseInt(textDni.getText());
    }
    public String getDireccion(){
        return textDireccion.getText();
    }
    public int getTelefono(){
        return Integer.parseInt(textTelefono.getText());
    }

    public float getSueldo(){
        return Float.parseFloat(textSueldo.getText());
    }

    public JButton getBtnMostrar() {
        return btnMostrar;
    }

    public void actualizarLista(List<EmpleadoPermanente> empleados) {
        listaModelo.clear();
        for (EmpleadoPermanente empleado : empleados) {
            listaModelo.addElement(empleado.toString());
        }
    }
    public void limpiarCampos() {
        textNombre.setText("");
        textDni.setText("");
        textDireccion.setText("");
        textTelefono.setText("");
        textSueldo.setText("");
    }
    public void cargarEmpleado(EmpleadoPermanente empleado) {
        textNombre.setText(empleado.getNombre());
        textDni.setText(String.valueOf(empleado.getEmpleadoDni()));
        textDireccion.setText(empleado.getDireccion());
        textTelefono.setText(String.valueOf(empleado.getTelefono()));
        textSueldo.setText(String.valueOf(empleado.getSueldo().getSueldoBase()));
    }
    public JList<String> getListaEmpleados() {
        return empleados;
    }
}


