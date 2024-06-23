package global.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import global.modelo.*;

public class VistaSeleccionCursos extends JDialog {
    private JList<String> listaCursos;
    private DefaultListModel<String> modeloLista;
    private JButton btnSeleccionar;
    private JButton btnCancelar;

    public VistaSeleccionCursos(JFrame parent, List<Curso> cursos) {
        super(parent, "Seleccionar Cursos", true);
        setSize(300, 400);
        setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        for (Curso curso : cursos) {
            modeloLista.addElement(curso.toString());
        }

        listaCursos = new JList<>(modeloLista);
        add(new JScrollPane(listaCursos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnSeleccionar = new JButton("Seleccionar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

    }

    public JButton getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JList<String> getListaCursos() {
        return listaCursos;
    }
}

