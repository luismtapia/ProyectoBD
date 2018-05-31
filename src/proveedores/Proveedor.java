package proveedores;

/**
 *
 * @author luis_
 */
public class Proveedor {
    int cve_proveedor;
    String nombre_proveedor;
    String direccion;
    String RFC;

    public Proveedor(int cve_proveedor, String nombre_proveedor, String direccion, String RFC) {
        this.cve_proveedor = cve_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.direccion = direccion;
        this.RFC = RFC;
    }

    public int getCve_proveedor() {
        return cve_proveedor;
    }

    public void setCve_proveedor(int cve_proveedor) {
        this.cve_proveedor = cve_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "cve_proveedor=" + cve_proveedor + ", nombre_proveedor=" + nombre_proveedor + ", direccion=" + direccion + ", RFC=" + RFC + '}';
    }
    
    
}
