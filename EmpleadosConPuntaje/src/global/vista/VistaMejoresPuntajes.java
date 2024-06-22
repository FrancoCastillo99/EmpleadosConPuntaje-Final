package global.vista;

import global.modelo.EmpleadoPermanente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaMejoresPuntajes extends JFrame{
    private JList<String> mejores = new JList<>();
    private DefaultListModel<String> listaModelo = new DefaultListModel<>();
    private JLabel titulo;

    public VistaMejoresPuntajes(){
        mejores.setModel(listaModelo);
        setTitle("Mejores");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        titulo = new JLabel("Empleados con mayor puntaje", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 26));
        add(titulo, BorderLayout.NORTH);

        this.add(new JScrollPane(mejores), BorderLayout.CENTER);

    }

    public void actualizarLista(List<EmpleadoPermanente> empleados) {
        listaModelo.clear();
        for (EmpleadoPermanente empleadoPermanente : empleados) {
            listaModelo.addElement(empleadoPermanente.mejores());
        }
    }
}
