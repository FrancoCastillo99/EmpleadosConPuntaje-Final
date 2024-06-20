package global.vista;

import global.modelo.Curso;
import global.modelo.EmpleadoPermanente;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        botones.add(btnGuardar);
        botones.add(btnMostrar);

        add(botones, BorderLayout.EAST);

        cursos.setModel(listaModelo);
        add(new JScrollPane(cursos), BorderLayout.SOUTH);
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public String getNombre(){
        return textCurso.getText();
    }
    public int getCodigo(){
        return Integer.parseInt(textCodigo.getText());
    }
    public int getCarga(){
        return Integer.parseInt(textcargaHoraria.getText());
    }
    public int getPuntos(){
        return Integer.parseInt(textPuntos.getText());
    }
    public LocalDate getFechaInicio(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(textFechaInicio.getText(),formatter);
    }
    public LocalDate getFechaFin(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(textFechaFin.getText(),formatter);
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
}
