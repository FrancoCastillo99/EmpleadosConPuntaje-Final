package global.vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {
    private JButton btnEmpleadoPermantente;
    private JButton btnEmpleadoContratado;
    private JButton btnMejoresPuntajes;
    private JButton btnCurso;
    private JLabel titulo;

    public VistaPrincipal(){
        setTitle("Empresa");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2,2));
        btnEmpleadoContratado = new JButton("Empleado Contratado");
        btnEmpleadoPermantente = new JButton("Empleado Permanente");
        btnMejoresPuntajes = new JButton("Mejores puntajes");
        btnCurso = new JButton("Curso");
        panelBotones.add(btnEmpleadoContratado);
        panelBotones.add(btnEmpleadoPermantente);
        panelBotones.add(btnMejoresPuntajes);
        panelBotones.add(btnCurso);

        add(panelBotones, BorderLayout.CENTER);

        titulo = new JLabel("EMPRESA", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 32));
        add(titulo, BorderLayout.NORTH);
    }

    public JButton getBtnEmpleadoPermantente() {
        return btnEmpleadoPermantente;
    }

    public JButton getBtnEmpleadoContratado() {
        return btnEmpleadoContratado;
    }

    public JButton getBtnMejoresPuntajes() {
        return btnMejoresPuntajes;
    }

    public JButton getBtnCurso() {
        return btnCurso;
    }
}
