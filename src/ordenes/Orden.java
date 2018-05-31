package ordenes;

import java.sql.Date;

/**
 *
 * @author luis_
 */
public class Orden {
    int no_orden;
    Date fecha_orden;
    Date fecha_entrega;
    double subtotal;
    double IVA;
    double pago_total;
    int cve_proveedor;

    public Orden(int no_orden, Date fecha_orden, Date fecha_entrega, double subtotal, double IVA, double pago_total, int cve_proveedor) {
        this.no_orden = no_orden;
        this.fecha_orden = fecha_orden;
        this.fecha_entrega = fecha_entrega;
        this.subtotal = subtotal;
        this.IVA = IVA;
        this.pago_total = pago_total;
        this.cve_proveedor = cve_proveedor;
    }

    public int getNo_orden() {
        return no_orden;
    }

    public void setNo_orden(int no_orden) {
        this.no_orden = no_orden;
    }

    public Date getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
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

    public double getPago_total() {
        return pago_total;
    }

    public void setPago_total(double pago_total) {
        this.pago_total = pago_total;
    }

    public int getCve_proveedor() {
        return cve_proveedor;
    }

    public void setCve_proveedor(int cve_proveedor) {
        this.cve_proveedor = cve_proveedor;
    }

    @Override
    public String toString() {
        return "Orden{" + "no_orden=" + no_orden + ", fecha_orden=" + fecha_orden + ", fecha_entrega=" + fecha_entrega + ", subtotal=" + subtotal + ", IVA=" + IVA + '}';
    }
    
    
}
