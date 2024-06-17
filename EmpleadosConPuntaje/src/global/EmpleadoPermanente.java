package global;

import java.util.*;

public class EmpleadoPermanente extends Empleado{
    private ArrayList<Curso> realiza;
    private int puntosAcumulados;
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
            System.out.println("El curso [" + curso.getTitulo() + "] tiene requisitos previos que el empleado [" + this.getNombre() + "] no cumple\n");
        }
    }

    public void addCurso(Curso curso) { //Debe recibir parametros para crear la clase sueldo
        if (puedeRealizarCurso(curso)){
            if(this.realiza == null)
                this.realiza = new ArrayList<Curso>();
            this.realiza.add(curso);

            sumarPuntos();
            getSueldo().calcularIncentivo(this);

            curso.addEstudiante(this);
        }else {
            System.out.println("El curso [" + curso.getTitulo() + "] tiene requisitos previos que el empleado [" + this.getNombre() + "] no cumple\n");
        }
    }
    public void sumarPuntos() {
        puntosAcumulados = 0;
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        for (Curso c : realiza) {
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(c.getFechaInicio());
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(c.getFechaFin());

            if ((startCal.get(Calendar.MONTH) == currentMonth && startCal.get(Calendar.YEAR) == currentYear) ||
                    (endCal.get(Calendar.MONTH) == currentMonth && endCal.get(Calendar.YEAR) == currentYear)) {
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
}