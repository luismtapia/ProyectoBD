package paises;

public class Pais {
    String cve_pais;
    String nombre_pais;

    public Pais(String cve_pais, String nombre_pais) {
        this.cve_pais = cve_pais;
        this.nombre_pais = nombre_pais;
    }

    public String getCve_pais() {
        return cve_pais;
    }

    public void setCve_pais(String cve_pais) {
        this.cve_pais = cve_pais;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    @Override
    public String toString() {
        return nombre_pais;
    }
    
    
}
