package ventas;

import empleados.*;
import java.sql.Date;

/**
 *
 * @author luis_
 */
public class Venta {
    int no_empleado;
    String nombre_empleado;
    String direccion_empleado;
    Date fecha_contratacion;
    int nivel;

    public Venta(int no_empleado, String nombre_empleado, String direccion_empleado, Date fecha_contratacion, int nivel) {
        this.no_empleado = no_empleado;
        this.nombre_empleado = nombre_empleado;
        this.direccion_empleado = direccion_empleado;
        this.fecha_contratacion = fecha_contratacion;
        this.nivel = nivel;
    }

    public int getNo_empleado() {
        return no_empleado;
    }

    public void setNo_empleado(int no_empleado) {
        this.no_empleado = no_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getDireccion_empleado() {
        return direccion_empleado;
    }

    public void setDireccion_empleado(String direccion_empleado) {
        this.direccion_empleado = direccion_empleado;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return String.format("%10s", no_empleado) + "\t" + String.format("%-60s", nombre_empleado) + String.format("%-60s", direccion_empleado) + String.format("%20s",fecha_contratacion) + String.format("%20s", nivel);
    }
    
    
}
