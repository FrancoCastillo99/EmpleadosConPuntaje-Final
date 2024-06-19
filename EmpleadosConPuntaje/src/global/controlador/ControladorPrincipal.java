package global.controlador;
import global.vista.VistaMejoresPuntajes;
import global.vista.VistaPrincipal;
import global.modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPrincipal {
    private VistaPrincipal vista;

    public ControladorPrincipal(VistaPrincipal vista){

        this.vista = vista;

        vista.getBtnMejoresPuntajes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaMejoresPuntajes vistaMejoresPuntajes = new VistaMejoresPuntajes();
                EmpleadoPermanente ep1 = new EmpleadoPermanente("Franco","Soler 591", 2612099, 41699948);
                EmpleadoPermanente ep2 = new EmpleadoPermanente("Lionel", "SiempreViva 145", 2613147, 38457632);

                ArrayList<EmpleadoPermanente> permanentes = new ArrayList<>();
                permanentes.add(ep1);
                permanentes.add(ep2);

                ArrayList<EmpleadoPermanente> mejores = EmpleadoPermanente.maximoPuntaje(permanentes);
                vistaMejoresPuntajes.actualizarLista(mejores);
                vistaMejoresPuntajes.setVisible(true);
            }
        });
    }
}
