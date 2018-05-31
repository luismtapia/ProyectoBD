/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orden_pide_productos;

/**
 *
 * @author luis_
 */
public class Orden_Pide_Producto {
    int cantidad;
    int no_orden;
    int cve_producto;

    public Orden_Pide_Producto(int cantidad, int no_orden, int cve_producto) {
        this.cantidad = cantidad;
        this.no_orden = no_orden;
        this.cve_producto = cve_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNo_orden() {
        return no_orden;
    }

    public void setNo_orden(int no_orden) {
        this.no_orden = no_orden;
    }

    public int getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(int cve_producto) {
        this.cve_producto = cve_producto;
    }

    @Override
    public String toString() {
        return "Orden_Pide_Producto{" + "cantidad=" + cantidad + ", no_orden=" + no_orden + ", cve_producto=" + cve_producto + '}';
    }
    
    
}
