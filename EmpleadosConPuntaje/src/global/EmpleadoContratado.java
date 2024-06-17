package global;


import java.util.Date;

public class EmpleadoContratado extends Empleado{
    private String funcion;
    private Date fechaVencimiento;

    public EmpleadoContratado(String nombre, String direccion, int telefono, int empleadoDni, String funcion, Date fechaVencimiento) {
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void definirSueldo(float sueldoBase){
        int dni = getEmpleadoDni();
        Sueldo sueldo = new Sueldo(dni, sueldoBase);
        sueldo.setIncentivo(0);
        setSueldo(sueldo);
    }

    @Override
    public float importeBruto() {
        Date currentDate = new Date();
        if (fechaVencimiento != null && fechaVencimiento.before(currentDate)) {
            return 0; // No se liquida sueldo si el contrato ha vencido
        }
        return getSueldo().getSueldoBase();
    }
}
