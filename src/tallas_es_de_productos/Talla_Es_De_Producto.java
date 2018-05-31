/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallas_es_de_productos;

/**
 *
 * @author luis_
 */
public class Talla_Es_De_Producto {
    int cantidad_existencia;
    int numero;
    int cve_producto;

    public Talla_Es_De_Producto(int cantidad_existencia, int numero, int cve_producto) {
        this.cantidad_existencia = cantidad_existencia;
        this.numero = numero;
        this.cve_producto = cve_producto;
    }

    public int getCantidad_existencia() {
        return cantidad_existencia;
    }

    public void setCantidad_existencia(int cantidad_existencia) {
        this.cantidad_existencia = cantidad_existencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(int cve_producto) {
        this.cve_producto = cve_producto;
    }

    @Override
    public String toString() {
        return cve_producto + "       numero: " + numero + "       existencia: " + cantidad_existencia ;
    }
    
    
}
