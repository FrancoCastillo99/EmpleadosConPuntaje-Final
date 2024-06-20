package global.controlador;

import global.modelo.*;
import global.vista.VistaCurso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorCurso {
    private VistaCurso vista = new VistaCurso();

    public ControladorCurso(VistaCurso vista){
        this.vista = vista;

        vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Curso> cursosList = Persistencia.cargarLista("Cursos.ser");
                Curso curso = new Curso();
                curso.setTitulo(vista.getNombre());
                curso.setCodigo(vista.getCodigo());
                curso.setCargaHoraria(vista.getCarga());
                curso.setFechaInicio(vista.getFechaInicio());
                curso.setFechaFin(vista.getFechaFin());
                curso.setPuntos(vista.getPuntos());
                cursosList.add(curso);
                Persistencia.guardarLista(cursosList,"Cursos.ser");
            }
        });

        vista.getBtnMostrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Curso> cursosList = Persistencia.cargarLista("Cursos.ser");
                vista.actualizarLista(cursosList);
            }
        });
    }
}
