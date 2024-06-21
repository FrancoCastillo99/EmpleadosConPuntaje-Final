package global.modelo;

import javax.swing.*;
import java.util.*;
import java.time.LocalDate;

public class EmpleadoPermanente extends Empleado {
    private static final long serialVersionUID = 1L;
    private ArrayList<Curso> realiza;
    private int puntosAcumulados;

    public EmpleadoPermanente() {
        realiza = new ArrayList<>();
    }

    public EmpleadoPermanente(String nombre, String direccion, int telefono, int empleadoDni) {
        super(nombre, direccion, telefono, empleadoDni);
        this.realiza = new ArrayList<>();
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public boolean puedeRealizarCurso(Curso curso){
        return realiza.containsAll(curso.getCursosPrevios());
    }

    public void addCurso(Curso curso, float sueldoBase) { //Debe recibir parametros para crear la clase sueldo
        if (puedeRealizarCurso(curso)){
            if(this.realiza == null)
                this.realiza = new ArrayList<Curso>();
            this.realiza.add(curso);

            int dni = getEmpleadoDni();
            Sueldo sueldo = new Sueldo(dni,sueldoBase); //Crear objeto sueldo
            sumarPuntos();
            sueldo.calcularIncentivo(this);
            setSueldo(sueldo);

            curso.addEstudiante(this);
        }else {
            JOptionPane.showMessageDialog(null,"El curso [" + curso.getTitulo() + "] tiene requisitos previos que el empleado [" + this.getNombre() + "] no cumple");
        }
    }

    public void addCurso(Curso curso) {
        if (puedeRealizarCurso(curso)){
            if(this.realiza == null)
                this.realiza = new ArrayList<Curso>();
            this.realiza.add(curso);

            sumarPuntos();
            getSueldo().calcularIncentivo(this);

            curso.addEstudiante(this);
        }else {
            JOptionPane.showMessageDialog(null,"El curso [" + curso.getTitulo() + "] tiene requisitos previos que el empleado [" + this.getNombre() + "] no cumple");
        }
    }

    public void sumarPuntos() {
        LocalDate now = LocalDate.now();
        puntosAcumulados = 0;
        for (Curso c : realiza) {
            LocalDate inicio = c.getFechaInicio();
            LocalDate fin = c.getFechaFin();
            if ((inicio.getMonth() == now.getMonth() && inicio.getYear() == now.getYear()) ||
                    (fin.getMonth() == now.getMonth() && fin.getYear() == now.getYear())) {
                puntosAcumulados += c.getPuntos();
            }
        }
    }

    public static ArrayList<EmpleadoPermanente> maximoPuntaje(ArrayList<EmpleadoPermanente> empleados) {
        ArrayList<EmpleadoPermanente> mejores = new ArrayList<>();

        for (EmpleadoPermanente empleado : empleados) {
            empleado.sumarPuntos();
            if (mejores.size() < 10) {
                mejores.add(empleado);
                mejores.sort(Comparator.comparingInt(EmpleadoPermanente::getPuntosAcumulados).reversed());
            } else {
                if (empleado.getPuntosAcumulados() > mejores.get(9).getPuntosAcumulados()) {
                    mejores.set(9, empleado);
                    mejores.sort(Comparator.comparingInt(EmpleadoPermanente::getPuntosAcumulados).reversed());
                }
            }
        }

        return mejores;
    }

    @Override
    public float importeBruto() {
        Sueldo sueldo = getSueldo();
        sumarPuntos(); // Actualiza los puntos acumulados

        sueldo.calcularIncentivo(this);

        return sueldo.getSueldoBase() + sueldo.getIncentivo();
    }
    public void definirSueldo(float sueldoBase){
        int dni = getEmpleadoDni();
        Sueldo sueldo = new Sueldo(dni, sueldoBase);
        sueldo.setIncentivo(0);
        setSueldo(sueldo);
    }
    public String mostrarCursosPrevios() {
        if (realiza == null || realiza.isEmpty()) {
            return "No ha realizado ningun curso.";
        }
        StringBuilder total = new StringBuilder();
        for (Curso curso : realiza) {
            total.append(curso.getTitulo()).append(" ");
        }
        return total.toString().trim();
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "  |  DNI: " + getEmpleadoDni() + "  |  Dirección: " + getDireccion() + "  |  Telefono: " + getTelefono() +
                "  |  Puntos: " + puntosAcumulados +"  |  Sueldo: $" + importeBruto() + "  |  Cursos realizados: " + mostrarCursosPrevios();
    }
}
