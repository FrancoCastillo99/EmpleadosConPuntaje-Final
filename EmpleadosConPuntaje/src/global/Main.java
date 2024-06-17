package global;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Curso c1 = new Curso(100, "Finanzas", 10, 50, new Date(),new Date());
        Curso c2 = new Curso(101, "Social", 5,15, new Date(),new Date());
        Curso c3 = new Curso(102, "Finanzas 2", 20, 65, new Date(),new Date());

        c3.addCursosPrevios(c1);

        EmpleadoPermanente ep1 = new EmpleadoPermanente("Franco","Soler 591", 2612099, 41699948);
        EmpleadoPermanente ep2 = new EmpleadoPermanente("Liones", "SiempreViva 145", 2613147, 38457632);

        ArrayList<EmpleadoPermanente> permanentes = new ArrayList<>();
        permanentes.add(ep1);
        permanentes.add(ep2);

        ep1.addCurso(c1, 200000);
        ep1.addCurso(c3);
        ep2.addCurso(c2,220000);
        ep2.addCurso(c3);

        System.out.println("Empleado 1: " + ep1.importeBruto());
        System.out.println("Empleado 1: " + ep1.getPuntosAcumulados());
        System.out.println();
        System.out.println("Empleado 2: " + ep2.importeBruto());
        System.out.println("Empleado 2: " + ep2.getPuntosAcumulados());

        System.out.println();
        ArrayList<EmpleadoPermanente> ordenados = EmpleadoPermanente.maximoPuntaje(permanentes);
        for (EmpleadoPermanente empleado : ordenados){
            System.out.println("- " + empleado.getNombre() + "\t- " + empleado.getPuntosAcumulados());
        }
    }
}
