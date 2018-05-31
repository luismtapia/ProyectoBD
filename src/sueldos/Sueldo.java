package sueldos;



/**
 *
 * @author luis_
 */
public class Sueldo {
    int nivel, monto;
    

    public Sueldo(int nivel, int monto) {
        this.nivel = nivel;
        this.monto = monto;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return nivel + "        " + monto;
    }
}
