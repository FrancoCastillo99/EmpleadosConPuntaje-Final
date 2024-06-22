package global.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    private int codigo;
    private String titulo;
    private int cargaHoraria;
    private int puntos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private ArrayList<Curso> cursosPrevios;
    private ArrayList<Empleado> estudiantes;

    public Curso() {
        cursosPrevios = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

    public Curso(int codigo, String titulo, int cargaHoraria, int puntos, LocalDate fechaInicio, LocalDate fechaFin) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.puntos = puntos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        cursosPrevios = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public ArrayList<Curso> getCursosPrevios() {
        return cursosPrevios;
    }

    public void setCursosPrevios(ArrayList<Curso> cursosPrevios) {
        this.cursosPrevios = cursosPrevios;
    }

    public ArrayList<Empleado> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Empleado> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void addCursosPrevios(Curso curso){
        if(this.cursosPrevios == null)
            this.cursosPrevios = new ArrayList<>();
        this.cursosPrevios.add(curso);
    }

    public void addEstudiante(Empleado estudiante){
        if (this.estudiantes == null)
            this.estudiantes = new ArrayList<>();
        this.estudiantes.add(estudiante);
    }

    @Override
    public String toString() {
        return "Curso: " + titulo + "  |  Codigo: " + codigo + "  |  Carga Horaria: " + cargaHoraria + "  |  Puntos: " + puntos +
                "  |  Fecha Inicio: " + fechaInicio + "  |  Fecha Fin: " + fechaFin + "  |  Cursos necesarios: " + mostrarCursosPrevios();
    }

    public String mostrarCursosPrevios() {
        if (cursosPrevios == null || cursosPrevios.isEmpty()) {
            return "No requiere cursos previos.";
        }
        StringBuilder total = new StringBuilder();
        for (Curso curso : cursosPrevios) {
            total.append(curso.getTitulo()).append(" ");
        }
        return total.toString().trim();
    }

    public static boolean cursoDuplicado(ArrayList<Curso> cursosList, String nombre, int codigo) {
        for (Curso curso : cursosList) {
            if (curso.getTitulo().equalsIgnoreCase(nombre) || curso.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
}
