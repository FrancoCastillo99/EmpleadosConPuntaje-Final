package global.controlador;
import global.vista.*;
import global.modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorPrincipal {
    private VistaPrincipal vista;

    public ControladorPrincipal(VistaPrincipal vista){

        this.vista = vista;

        vista.getBtnMejoresPuntajes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaMejoresPuntajes vistaMejoresPuntajes = new VistaMejoresPuntajes();


                ArrayList<EmpleadoPermanente> permanentes = Persistencia.cargarLista("EmpleadoPermanentes.ser");

                ArrayList<EmpleadoPermanente> mejores = EmpleadoPermanente.maximoPuntaje(permanentes);
                vistaMejoresPuntajes.actualizarLista(mejores);
                vistaMejoresPuntajes.setVisible(true);
            }
        });

        vista.getBtnCurso().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaCurso vistaCurso = new VistaCurso();
                ControladorCurso controladorCurso = new ControladorCurso(vistaCurso);
                vistaCurso.setVisible(true);
            }
        });

        vista.getBtnEmpleadoContratado().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaEmpleadoContratado vistaEmpleadoContratado = new VistaEmpleadoContratado();
                ControladorEmpleadoContratado controladorEmpleadoContratado = new ControladorEmpleadoContratado(vistaEmpleadoContratado);
                vistaEmpleadoContratado.setVisible(true);
            }
        });

        vista.getBtnEmpleadoPermantente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaEmpleadoPermanente vistaEmpleadoPermanente = new VistaEmpleadoPermanente();
                ControladorEmpleadoPermanente controladorEmpleadoPermanente = new ControladorEmpleadoPermanente(vistaEmpleadoPermanente);
                vistaEmpleadoPermanente.setVisible(true);
            }
        });
    }
}
