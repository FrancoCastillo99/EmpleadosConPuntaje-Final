package global.controlador;

import global.modelo.Curso;
import global.modelo.Empleado;
import global.modelo.EmpleadoPermanente;
import global.modelo.Persistencia;
import global.vista.VistaSeleccionCursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorSeleccionCurso {
    private VistaSeleccionCursos vista;
    private EmpleadoPermanente empleadoSeleccionado;
    private ArrayList<Curso> cursosList;
    private ArrayList<EmpleadoPermanente> empleadoPermanentes;

    public ControladorSeleccionCurso(VistaSeleccionCursos vista, EmpleadoPermanente empleadoSeleccionado, ArrayList<Curso> cursosList, ArrayList<EmpleadoPermanente> empleadoPermanentes) {
        this.vista = vista;
        this.empleadoSeleccionado = empleadoSeleccionado;
        this.cursosList = cursosList;
        this.empleadoPermanentes = empleadoPermanentes;

        vista.getBtnSeleccionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedValues = vista.getListaCursos().getSelectedValuesList();
                for (String value : selectedValues) {
                    for (Curso curso : cursosList) {
                        if (curso.toString().equals(value)) {
                            empleadoSeleccionado.addCurso(curso);
                            break;
                        }
                    }
                }
                Persistencia.guardarLista(cursosList, "Cursos.ser");
                Persistencia.guardarLista(empleadoPermanentes, "EmpleadoPermanentes.ser");
                vista.dispose();
            }
        });

        vista.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
            }
        });
    }
}
