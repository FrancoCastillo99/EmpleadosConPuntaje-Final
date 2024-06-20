package global.controlador;
import global.vista.VistaCurso;
import global.vista.VistaMejoresPuntajes;
import global.vista.VistaPrincipal;
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
                Curso c1 = new Curso(100, "Finanzas", 10, 50, LocalDate.of(2024,3,1), LocalDate.now());
                Curso c2 = new Curso(101, "Social", 5,200, LocalDate.now(), LocalDate.now());
                Curso c3 = new Curso(102, "Finanzas 2", 20, 65, LocalDate.now(), LocalDate.now());

                c3.addCursosPrevios(c1);

                EmpleadoPermanente ep1 = new EmpleadoPermanente("Franco","Soler 591", 2612099, 41699948);
                EmpleadoPermanente ep2 = new EmpleadoPermanente("Lionel", "SiempreViva 145", 2613147, 38457632);

                ep1.addCurso(c1, 200000);
                ep1.addCurso(c3);
                ep2.addCurso(c2,220000);
                ep2.addCurso(c3);


                ArrayList<EmpleadoPermanente> permanentes = new ArrayList<>();
                permanentes.add(ep1);
                permanentes.add(ep2);

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
    }
}
