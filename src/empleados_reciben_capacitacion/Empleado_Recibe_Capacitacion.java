/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleados_reciben_capacitacion;

/**
 *
 * @author luis_
 */
public class Empleado_Recibe_Capacitacion {
    int no_empleado, no_capacitacion, evaluacion;

    public Empleado_Recibe_Capacitacion(int evaluacion, int no_capacitacion, int no_empleado) {
        this.evaluacion = evaluacion;
        this.no_capacitacion = no_capacitacion;
        this.no_empleado = no_empleado;
    }
    
    public int getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(int evaluacion) {
        this.evaluacion = evaluacion;
    }

    public int getNo_capacitacion() {
        return no_capacitacion;
    }

    public void setNo_capacitacion(int no_capacitacion) {
        this.no_capacitacion = no_capacitacion;
    }
    public int getNo_empleado() {
        return no_empleado;
    }

    public void setNo_empleado(int no_empleado) {
        this.no_empleado = no_empleado;
    }
   
    @Override
    public String toString() {
        return   "Empleado "+no_empleado+ "\t\t-\t\t"+ no_capacitacion+"\t\t-Calificacion = "+evaluacion ;
    }
    
    
}
