package global.modelo;


import java.io.Serializable;

public abstract class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String direccion;
    private int telefono;
    private int empleadoDni;
    private Sueldo sueldo;

    public Empleado() {
    }

    public Empleado(String nombre, String direccion, int telefono, int empleadoDni) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.empleadoDni = empleadoDni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEmpleadoDni() {
        return empleadoDni;
    }

    public void setEmpleadoDni(int empleadoDni) {
        this.empleadoDni = empleadoDni;
    }

    public Sueldo getSueldo() {
        return sueldo;
    }

    public void setSueldo(Sueldo sueldo) {
        this.sueldo = sueldo;
    }
    public abstract float importeBruto(); // Método abstracto para ser implementado por subclases

}
