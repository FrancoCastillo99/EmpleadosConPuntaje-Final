package global.controlador;

import global.modelo.*;
import global.vista.VistaCurso;
import global.vista.VistaSeleccionCursosPrevios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorCurso {
    private VistaCurso vista;
    private ArrayList<Curso> cursosList;
    private int selectedIndex = -1; // Índice del curso seleccionado

    public ControladorCurso(VistaCurso vista){
        this.vista = vista;
        cursosList = Persistencia.cargarLista("Cursos.ser");

        // Verificar si la lista cargada es nula y si es así inicializarla
        if (cursosList == null) {
            cursosList = new ArrayList<>();
        }
        vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso curso = new Curso();
                curso.setTitulo(vista.getNombre());
                curso.setCodigo(vista.getCodigo());
                curso.setCargaHoraria(vista.getCarga());
                curso.setFechaInicio(vista.getFechaInicio());
                curso.setFechaFin(vista.getFechaFin());
                curso.setPuntos(vista.getPuntos());

                if (selectedIndex == -1) {
                    // Añadir nuevo curso
                    cursosList.add(curso);
                } else {
                    // Actualizar curso existente
                    cursosList.set(selectedIndex, curso);
                    selectedIndex = -1; // Reiniciar índice
                }

                Persistencia.guardarLista(cursosList, "Cursos.ser");
                vista.limpiarCampos();
                vista.actualizarLista(cursosList);
            }
        });

        vista.getBtnMostrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cursosList = Persistencia.cargarLista("Cursos.ser");
                if (cursosList == null) {
                    cursosList = new ArrayList<>();
                }
                vista.actualizarLista(cursosList);
            }
        });
        vista.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = vista.getListaCursos().getSelectedIndex();
                if (selectedIndex != -1) {
                    Curso cursoSeleccionado = cursosList.get(selectedIndex);
                    vista.cargarCurso(cursoSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(vista, "Selecciona un curso para editar.");
                }
            }
        });
        vista.getBtnAgregarCursoPrevio().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = vista.getListaCursos().getSelectedIndex();
                if (selectedIndex != -1) {
                    Curso cursoSeleccionado = cursosList.get(selectedIndex);
                    VistaSeleccionCursosPrevios dialog = new VistaSeleccionCursosPrevios(vista, cursosList);
                    ControladorSeleccionCursosPrevios controladorSeleccion = new ControladorSeleccionCursosPrevios(dialog, cursoSeleccionado, cursosList);
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vista, "Selecciona un curso para agregar cursos previos.");
                }
            }
        });

    }
}
