package capacitaciones;
/**
 *
 * @author luis_
 */

public class Capacitacion {
    int no_capacitacion,cve_area;
    String nombre_capacitacion,nombre_capacitador;

    public Capacitacion(int no_capacitacion, String nombre_capacitacion, String nombre_capacitador, int cve_area) {
        this.no_capacitacion = no_capacitacion;
        this.nombre_capacitacion = nombre_capacitacion;
        this.nombre_capacitador = nombre_capacitador;
        this.cve_area = cve_area;
    }

    public int getNo_capacitacion() {
        return no_capacitacion;
    }

    public void setNo_capacitacion(int no_capacitacion) {
        this.no_capacitacion = no_capacitacion;
    }

    public int getCve_area() {
        return cve_area;
    }

    public void setCve_area(int cve_area) {
        this.cve_area = cve_area;
    }

    public String getNombre_capacitacion() {
        return nombre_capacitacion;
    }

    public void setNombre_capacitacion(String nombre_capacitacion) {
        this.nombre_capacitacion = nombre_capacitacion;
    }

    public String getNombre_capacitador() {
        return nombre_capacitador;
    }

    public void setNombre_capacitador(String nombre_capacitador) {
        this.nombre_capacitador = nombre_capacitador;
    }

    @Override
    public String toString() {
        return nombre_capacitacion + "            nombre_capacitador:" + nombre_capacitador;
    }
}