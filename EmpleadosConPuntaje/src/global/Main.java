package global;

import global.controlador.ControladorPrincipal;
import global.vista.*;
import global.modelo.Curso;
import global.modelo.EmpleadoPermanente;

import java.time.LocalDate;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        VistaPrincipal vista = new VistaPrincipal();
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(vista);
        vista.setVisible(true);
    }
}
