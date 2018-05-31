package detalle;

public class Detalle {
    int cantidad;
    int no_ticket;
    int cve_producto;

    public Detalle(int cantidad, int no_ticket, int cve_producto) {
        this.cantidad = cantidad;
        this.no_ticket = no_ticket;
        this.cve_producto = cve_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNo_ticket() {
        return no_ticket;
    }

    public void setNo_ticket(int no_ticket) {
        this.no_ticket = no_ticket;
    }

    public int getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(int cve_producto) {
        this.cve_producto = cve_producto;
    }

    @Override
    public String toString() {
        return "detalle{" + "cantidad=" + cantidad + ", no_ticket=" + no_ticket + ", cve_producto=" + cve_producto + '}';
    }
    
    
}
