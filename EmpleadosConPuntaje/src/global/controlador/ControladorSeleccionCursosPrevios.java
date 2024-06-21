package global.controlador;
import global.modelo.*;
import global.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorSeleccionCursosPrevios {
    private VistaSeleccionCursosPrevios vista;
    private Curso cursoSeleccionado;
    private List<Curso> cursosList;

    public ControladorSeleccionCursosPrevios(VistaSeleccionCursosPrevios vista, Curso cursoSeleccionado, ArrayList<Curso> cursosList) {
        this.vista = vista;
        this.cursoSeleccionado = cursoSeleccionado;
        this.cursosList = cursosList;

        vista.getBtnSeleccionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedValues = vista.getListaCursos().getSelectedValuesList();
                for (String value : selectedValues) {
                    for (Curso curso : cursosList) {
                        if (curso.toString().equals(value)) {
                            cursoSeleccionado.addCursosPrevios(curso);
                            break;
                        }
                    }
                }
                Persistencia.guardarLista(cursosList, "Cursos.ser");
                vista.dispose();
            }
        });
    }
}
