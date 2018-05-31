
package facturas;

import java.sql.Date;

/**
 *
 * @author luis_
 */
public class Factura {
    int no_factura;
    Date fecha_compra;
    double subtotal;
    double IVA;
    double total;
    int no_ticket;
    int no_cliente;

    public Factura(int no_factura, Date fecha_compra, double subtotal, double IVA, double total, int no_ticket, int no_cliente) {
        this.no_factura = no_factura;
        this.fecha_compra = fecha_compra;
        this.subtotal = subtotal;
        this.IVA = IVA;
        this.total = total;
        this.no_ticket = no_ticket;
        this.no_cliente = no_cliente;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getNo_ticket() {
        return no_ticket;
    }

    public void setNo_ticket(int no_ticket) {
        this.no_ticket = no_ticket;
    }

    public int getNo_cliente() {
        return no_cliente;
    }

    public void setNo_cliente(int no_cliente) {
        this.no_cliente = no_cliente;
    }

    @Override
    public String toString() {
        return no_factura + "    fecha_compra=" + fecha_compra + ", subtotal=" + subtotal + ", IVA=" + IVA + ", total=" + total + ", no_ticket=" + no_ticket + ", no_cliente=" + no_cliente;
    }
    
    
}
