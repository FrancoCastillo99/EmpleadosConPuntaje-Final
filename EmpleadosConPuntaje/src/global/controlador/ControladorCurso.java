package global.controlador;

import global.modelo.*;
import global.vista.VistaCurso;
import global.vista.VistaSeleccionCursosPrevios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

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
                // Validación del nombre
                String nombre = vista.getNombre();
                if (nombre == null) return; // Termina la ejecución si el nombre no es válido

                // Validación del código
                int codigo = vista.getCodigo();
                if (codigo == -1) return; // Termina la ejecución si el código no es válido

                // Verificar duplicados usando el método estático
                if (Curso.cursoDuplicado(cursosList, nombre, codigo)) {
                    JOptionPane.showMessageDialog(vista, "El curso con el nombre o código ya existe.", "Error de Duplicado", JOptionPane.ERROR_MESSAGE);
                    return; // Termina la ejecución si se encuentra un duplicado
                }

                // Validación de la carga horaria
                int carga = vista.getCarga();
                if (carga == -1) return; // Termina la ejecución si la carga horaria no es válida

                // Validación de los puntos
                int puntos = vista.getPuntos();
                if (puntos == -1) return; // Termina la ejecución si los puntos no son válidos

                // Validación de la fecha de inicio
                LocalDate fechaInicio = vista.getFechaInicio();
                if (fechaInicio == null) return; // Termina la ejecución si la fecha de inicio no es válida

                // Validación de la fecha de fin
                LocalDate fechaFin = vista.getFechaFin();
                if (fechaFin == null) return; // Termina la ejecución si la fecha de fin no es válida

                Curso curso = new Curso();
                curso.setTitulo(nombre);
                curso.setCodigo(codigo);
                curso.setCargaHoraria(carga);
                curso.setPuntos(puntos);
                curso.setFechaInicio(fechaInicio);
                curso.setFechaFin(fechaFin);

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
