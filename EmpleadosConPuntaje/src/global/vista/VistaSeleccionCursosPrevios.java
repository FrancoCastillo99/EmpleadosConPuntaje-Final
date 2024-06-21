package global.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import global.modelo.*;

public class VistaSeleccionCursosPrevios extends JDialog {
    private JList<String> listaCursos;
    private DefaultListModel<String> modeloLista;
    private JButton btnSeleccionar;
    private JButton btnCancelar;

    public VistaSeleccionCursosPrevios(JFrame parent, List<Curso> cursos) {
        super(parent, "Seleccionar Cursos Previos", true);
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

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public JButton getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public JList<String> getListaCursos() {
        return listaCursos;
    }
}
