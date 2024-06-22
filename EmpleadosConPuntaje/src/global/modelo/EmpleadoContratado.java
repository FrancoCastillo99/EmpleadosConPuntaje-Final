package global.modelo;


import java.time.LocalDate;
import java.util.ArrayList;


public class EmpleadoContratado extends Empleado {
    private static final long serialVersionUID = 1L;
    private String funcion;
    private LocalDate fechaVencimiento;

    public EmpleadoContratado() {
    }

    public EmpleadoContratado(String nombre, String direccion, int telefono, int empleadoDni, String funcion, LocalDate fechaVencimiento) {
        super(nombre, direccion, telefono, empleadoDni);
        this.funcion = funcion;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void definirSueldo(float sueldoBase){
        int dni = getEmpleadoDni();
        Sueldo sueldo = new Sueldo(dni, sueldoBase);
        sueldo.setIncentivo(0);
        setSueldo(sueldo);
    }

    public float importeBruto() {
        LocalDate currentDate = LocalDate.now();
        if (fechaVencimiento != null && fechaVencimiento.isBefore(currentDate)) {
            return 0; // No se liquida sueldo si el contrato ha vencido
        }
        return getSueldo().getSueldoBase();
    }
    public static boolean empleadoContratadoDuplicado(ArrayList<EmpleadoContratado> empleadoContratados, int dni) {
        for (EmpleadoContratado empleado : empleadoContratados) {
            if (empleado.getEmpleadoDni() == dni) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "  |  DNI: " + getEmpleadoDni() + "  |  Dirección: " + getDireccion() + "  |  Telefono: " + getTelefono() +
                "  |  Vencimiento Contrato: " + fechaVencimiento + "  |  Función: " + funcion + "  |  Sueldo: $" + getSueldo().getSueldoBase();
    }
}
