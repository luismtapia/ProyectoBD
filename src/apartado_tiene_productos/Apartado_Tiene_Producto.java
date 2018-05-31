/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apartado_tiene_productos;

/**
 *
 * @author luis_
 */
public class Apartado_Tiene_Producto {
    int cantidad;
    int cve_apartado;
    int cve_producto;

    public Apartado_Tiene_Producto(int cantidad, int cve_apartado, int cve_producto) {
        this.cantidad = cantidad;
        this.cve_apartado = cve_apartado;
        this.cve_producto = cve_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCve_apartado() {
        return cve_apartado;
    }

    public void setCve_apartado(int cve_apartado) {
        this.cve_apartado = cve_apartado;
    }

    public int getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(int cve_producto) {
        this.cve_producto = cve_producto;
    }

    @Override
    public String toString() {
        return "Apartado_Tiene_Producto{" + "cantidad=" + cantidad + ", cve_apartado=" + cve_apartado + ", cve_producto=" + cve_producto + '}';
    }
    
    
}
