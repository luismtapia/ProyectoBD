package clientes;

public class Cliente {
    int no_cliente;
    String nombre_cliente,domicilio,RFC,correo;

    public Cliente(int no_cliente, String nombre_cliente, String domicilio, String RFC, String correo) {
        this.no_cliente = no_cliente;
        this.nombre_cliente = nombre_cliente;
        this.domicilio = domicilio;
        this.RFC = RFC;
        this.correo = correo;
    }

    public int getNo_cliente() {
        return no_cliente;
    }

    public void setNo_cliente(int no_empleado) {
        this.no_cliente = no_empleado;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    

    @Override
    public String toString() {
        return "nombre_cliente=" + nombre_cliente + ", domicilio=" + domicilio + ", RFC=" + RFC + ", correo=" + correo;
    }
    
    
}
