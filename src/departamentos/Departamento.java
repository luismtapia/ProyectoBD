package departamentos;

public class Departamento {
    int cve_depto;
    String nombre_depto;

    public Departamento(int cve_depto, String nombre_depto) {
        this.cve_depto = cve_depto;
        this.nombre_depto = nombre_depto;
    }

    public int getCve_depto() {
        return cve_depto;
    }

    public void setCve_depto(int cve_depto) {
        this.cve_depto = cve_depto;
    }

    public String getNombre_depto() {
        return nombre_depto;
    }

    public void setNombre_depto(String nombre_depto) {
        this.nombre_depto = nombre_depto;
    }

    @Override
    public String toString() {
        return "Departamento{" + "cve_depto=" + cve_depto + ", nombre_depto=" + nombre_depto + '}';
    }
    
    
}
