/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telefonos;



/**
 *
 * @author luis_
 */
public class Telefono {
    int no_empleado;
    String numero, cve_telefono;

    public Telefono(String numero, int no_empleado, String cve_telefono) {
        this.numero = numero;
        this.no_empleado = no_empleado;
        this.cve_telefono = cve_telefono;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getNo_empleado() {
        return no_empleado;
    }

    public void setNo_empleado(int no_empleado) {
        this.no_empleado = no_empleado;
    }

    public String getCve_telefono() {
        return cve_telefono;
    }

    public void setCve_telefono(String cve_telefono) {
        this.cve_telefono = cve_telefono;
    }

   
    @Override
    public String toString() {
        return   "Empleado "+no_empleado+ "\t\t-\t\t"+ cve_telefono+"\t\t-\t\t"+numero ;
    }
    
    
}
