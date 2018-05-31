/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipos_telefonos;


/**
 *
 * @author luis_
 */
public class Tipo_Telefono {
    String cve_telefono, tipo;

    public Tipo_Telefono(String cve_telefono, String tipo) {
        this.cve_telefono = cve_telefono;
        this.tipo = tipo;
      
    }


    public String getCve_telefono() {
        return cve_telefono;
    }

    public void setCve_telefono(String cve_telefono) {
        this.cve_telefono = cve_telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return cve_telefono + "\t-\t" + tipo;
    }
    
    
}
