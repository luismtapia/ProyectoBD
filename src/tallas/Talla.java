package tallas;

/**
 *
 * @author luis_
 */
public class Talla {
    int talla;

    public Talla(int talla) {
        this.talla = talla;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    @Override
    public String toString() {
        return ""+talla;
    }
    
    
}
