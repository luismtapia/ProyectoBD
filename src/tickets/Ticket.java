package tickets;

import java.sql.Date;

/**
 *
 * @author luis_
 */
public class Ticket {
    int no_ticket;
    Date fecha_compra;
    double total_compra;
    int no_empleado;

    public Ticket(int no_ticket, Date fecha_compra, double total_compra, int no_empleado) {
        this.no_ticket = no_ticket;
        this.fecha_compra = fecha_compra;
        this.total_compra = total_compra;
        this.no_empleado = no_empleado;
    }

    public int getNo_ticket() {
        return no_ticket;
    }

    public void setNo_ticket(int no_ticket) {
        this.no_ticket = no_ticket;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(double total_compra) {
        this.total_compra = total_compra;
    }

    public int getNo_empleado() {
        return no_empleado;
    }

    public void setNo_empleado(int no_empleado) {
        this.no_empleado = no_empleado;
    }

    @Override
    public String toString() {
        return  String.format("\t%30s", no_ticket) + String.format("\t%40s", fecha_compra) + String.format("\t$%30s", total_compra) + String.format("\t%30s", no_empleado);
    }
    
    
}
