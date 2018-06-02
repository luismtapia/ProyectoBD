
package ventas;

/**
 *
 * @author luis_
 */
public class VistaTicket {
    int cve_producto;
    String descripcion;
    double precio_venta;
    int numero;

    public VistaTicket(int cve_producto, String descripcion, double precio_venta, int numero) {
        this.cve_producto = cve_producto;
        this.descripcion = descripcion;
        this.precio_venta = precio_venta;
        this.numero = numero;
    }

    public int getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(int cve_producto) {
        this.cve_producto = cve_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
    
    @Override
    public String toString() {
        return "clave: " + cve_producto + " Descripción: " +descripcion+ " precio: "+precio_venta+ " numero: "+numero;
    }
    
}
