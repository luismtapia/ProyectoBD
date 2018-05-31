package productos;

public class Producto {
    int cve_producto;
    String descripcion;
    String marca;
    String modelo;
    double precio_venta;
    double precio_compra;
    int cve_depto;
    String cve_pais;
    int cve_proveedor;

    public Producto(int cve_producto, String descripcion, String marca, String modelo, double precio_venta, double precio_compra, int cve_depto, String cve_pais, int cve_proveedor) {
        this.cve_producto = cve_producto;
        this.descripcion = descripcion;
        this.marca = marca;
        this.modelo = modelo;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.cve_depto = cve_depto;
        this.cve_pais = cve_pais;
        this.cve_proveedor = cve_proveedor;
    }

    public int getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(int cve_producto) {
        this.cve_producto = cve_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getCve_depto() {
        return cve_depto;
    }

    public void setCve_depto(int cve_depto) {
        this.cve_depto = cve_depto;
    }

    public String getCve_pais() {
        return cve_pais;
    }

    public void setCve_pais(String cve_pais) {
        this.cve_pais = cve_pais;
    }

    public int getCve_proveedor() {
        return cve_proveedor;
    }

    public void setCve_proveedor(int cve_proveedor) {
        this.cve_proveedor = cve_proveedor;
    }

    @Override
    public String toString() {
        return cve_producto + "\t\t" + String.format("%-40s", descripcion) + "\t\t" + String.format("%30s", precio_venta);
    }
    
    
}
