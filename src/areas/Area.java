package areas;

/**
 *
 * @author luis_
 */
public class Area {
    int cve_area;
    String nombre;

    public Area(int cve_area, String nombre) {
        this.cve_area = cve_area;
        this.nombre = nombre;
    }

    public int getCve_area() {
        return cve_area;
    }

    public void setCve_area(int cve_area) {
        this.cve_area = cve_area;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
