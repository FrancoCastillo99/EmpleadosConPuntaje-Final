package global;


public class Sueldo {
    private int empleadoDni;
    private float sueldoBase;
    private float incentivo;

    public Sueldo(int empleadoDni, float sueldoBase) {
        this.empleadoDni = empleadoDni;
        this.sueldoBase = sueldoBase;
    }

    public int getEmpleadoDni() {
        return empleadoDni;
    }

    public void setEmpleadoDni(int empleadoDni) {
        this.empleadoDni = empleadoDni;
    }

    public float getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(float sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public float getIncentivo() {
        return incentivo;
    }

    public void setIncentivo(float incentivo) {
        this.incentivo = incentivo;
    }

    public void calcularIncentivo(EmpleadoPermanente empleadoPermanente){
        this.incentivo = empleadoPermanente.getPuntosAcumulados() * 1000;
    }

}
