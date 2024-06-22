package global.vista;

import global.modelo.Curso;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class VistaCurso extends JFrame {
    private JLabel titulo;
    private JLabel labelCurso = new JLabel(" Nombre", SwingConstants.LEFT);
    private JLabel labelCodigo = new JLabel(" Codigo", SwingConstants.LEFT);
    private JLabel labelFechaInicio = new JLabel(" Fecha inicio", SwingConstants.LEFT);
    private JLabel labelFechaFin = new JLabel(" Fecha fin", SwingConstants.LEFT);
    private JLabel labelPuntos = new JLabel(" Puntos", SwingConstants.LEFT);
    private JLabel labelcargaHoraria = new JLabel(" Carga horaria", SwingConstants.LEFT);
    private JTextField textCurso = new JTextField(10);
    private JTextField textCodigo = new JTextField(10);
    private JTextField textFechaInicio = new JTextField(10);
    private JTextField textFechaFin = new JTextField(10);
    private JTextField textPuntos = new JTextField(10);
    private JTextField textcargaHoraria = new JTextField(10);
    private JButton btnGuardar;
    private JButton btnMostrar;
    private JButton btnEditar;
    private JButton btnAgregarCursoPrevio;
    private JList<String> cursos = new JList<>();
    private DefaultListModel<String> listaModelo = new DefaultListModel<>();

    public VistaCurso(){
        setTitle("Curso");
        setSize(950, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        titulo = new JLabel("Curso", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 26));
        add(titulo, BorderLayout.NORTH);

        JPanel atributos = new JPanel();
        atributos.setLayout(new GridLayout(6,1));
        atributos.add(labelCurso);
        atributos.add(labelCodigo);
        atributos.add(labelcargaHoraria);
        atributos.add(labelFechaInicio);
        atributos.add(labelFechaFin);
        atributos.add(labelPuntos);

        add(atributos, BorderLayout.WEST);

        JPanel ingreso = new JPanel();
        ingreso.setLayout(new GridLayout(6,1));
        ingreso.add(textCurso);
        ingreso.add(textCodigo);
        ingreso.add(textcargaHoraria);
        ingreso.add(textFechaInicio);
        ingreso.add(textFechaFin);
        ingreso.add(textPuntos);

        add(ingreso, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnMostrar = new JButton("Mostrar");
        btnEditar = new JButton("Editar");
        btnAgregarCursoPrevio = new JButton("Agregar Curso Previo");

        botones.add(btnGuardar);
        botones.add(btnMostrar);
        botones.add(btnEditar);
        botones.add(btnAgregarCursoPrevio);

        add(botones, BorderLayout.EAST);

        cursos.setModel(listaModelo);
        add(new JScrollPane(cursos), BorderLayout.SOUTH);
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnAgregarCursoPrevio() {
        return btnAgregarCursoPrevio;
    }

    public String getNombre() {
        String nombre = textCurso.getText();
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) { // Verifica que el nombre solo contenga letras y espacios
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido que contenga solo letras.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null; // Indicador de error
        }
        return nombre;
    }
    public int getCodigo() {
        try {
            return Integer.parseInt(textCodigo.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para el Codigo.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return -1; // Indicador de error
        }
    }
    public int getCarga() {
        try {
            return Integer.parseInt(textcargaHoraria.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para la carga horaria.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return -1; // Indicador de error
        }
    }
    public int getPuntos() {
        try {
            return Integer.parseInt(textPuntos.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para los puntos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return -1; // Indicador de error
        }
    }

    public LocalDate getFechaInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(textFechaInicio.getText(), formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una fecha válida en formato dd/MM/yyyy.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null; // Indicador de error
        }
    }
    public LocalDate getFechaFin() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(textFechaFin.getText(), formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una fecha válida en formato dd/MM/yyyy.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null; // Indicador de error
        }
    }

    public JButton getBtnMostrar() {
        return btnMostrar;
    }

    public void actualizarLista(List<Curso> cursos) {
        listaModelo.clear();
        for (Curso curso : cursos) {
            listaModelo.addElement(curso.toString());
        }
    }
    public void limpiarCampos() {
        textCurso.setText("");
        textCodigo.setText("");
        textcargaHoraria.setText("");
        textPuntos.setText("");
        textFechaInicio.setText("");
        textFechaFin.setText("");
    }
    public JList<String> getListaCursos() {
        return cursos;
    }

    public void cargarCurso(Curso curso) {
        textCurso.setText(curso.getTitulo());
        textCodigo.setText(String.valueOf(curso.getCodigo()));
        textcargaHoraria.setText(String.valueOf(curso.getCargaHoraria()));
        textPuntos.setText(String.valueOf(curso.getPuntos()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        textFechaInicio.setText(curso.getFechaInicio().format(formatter));
        textFechaFin.setText(curso.getFechaFin().format(formatter));
    }
}
