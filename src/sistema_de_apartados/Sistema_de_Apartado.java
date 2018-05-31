package sistema_de_apartados;

import java.sql.Date;

/**
 *
 * @author luis_
 */
public class Sistema_de_Apartado {
    int cve_apartado;
    Date fecha_apartado;
    Date fecha_liquidacion;
    double engache;
    double restantes;
    int no_empleado;
    int no_cliente;
    int cve_depto;

    public Sistema_de_Apartado(int cve_apartado, Date fecha_apartado, Date fecha_liquidacion, double engache, double restantes, int no_empleado, int no_cliente, int cve_depto) {
        this.cve_apartado = cve_apartado;
        this.fecha_apartado = fecha_apartado;
        this.fecha_liquidacion = fecha_liquidacion;
        this.engache = engache;
        this.restantes = restantes;
        this.no_empleado = no_empleado;
        this.no_cliente = no_cliente;
        this.cve_depto = cve_depto;
    }

    public int getCve_apartado() {
        return cve_apartado;
    }

    public void setCve_apartado(int cve_apartado) {
        this.cve_apartado = cve_apartado;
    }

    public Date getFecha_apartado() {
        return fecha_apartado;
    }

    public void setFecha_apartado(Date fecha_apartado) {
        this.fecha_apartado = fecha_apartado;
    }

    public Date getFecha_liquidacion() {
        return fecha_liquidacion;
    }

    public void setFecha_liquidacion(Date fecha_liquidacion) {
        this.fecha_liquidacion = fecha_liquidacion;
    }

    public double getEngache() {
        return engache;
    }

    public void setEngache(double engache) {
        this.engache = engache;
    }

    public double getRestantes() {
        return restantes;
    }

    public void setRestantes(double restantes) {
        this.restantes = restantes;
    }

    public int getNo_empleado() {
        return no_empleado;
    }

    public void setNo_empleado(int no_empleado) {
        this.no_empleado = no_empleado;
    }

    public int getNo_cliente() {
        return no_cliente;
    }

    public void setNo_cliente(int no_cliente) {
        this.no_cliente = no_cliente;
    }

    public int getCve_depto() {
        return cve_depto;
    }

    public void setCve_depto(int cve_depto) {
        this.cve_depto = cve_depto;
    }

    @Override
    public String toString() {
        return "cve_apartado=" + cve_apartado + ", fecha_apartado=" + fecha_apartado + ", fecha_liquidacion=" + fecha_liquidacion + ", engache=" + engache + ", restantes=" + restantes + ", no_empleado=" + no_empleado + ", no_cliente=" + no_cliente + ", cve_depto=" + cve_depto;
    }
}
