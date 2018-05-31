package SQL;

import detalle.Detalle;
import static SQL.SQL.Conexion;
import apartado_tiene_productos.Apartado_Tiene_Producto;
import areas.Area;
import capacitaciones.Capacitacion;
import clientes.Cliente;
import departamentos.Departamento;
import empleados.Empleado;
import empleados_reciben_capacitacion.Empleado_Recibe_Capacitacion;
import facturas.Factura;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import orden_pide_productos.Orden_Pide_Producto;
import ordenes.Orden;
import paises.Pais;
import productos.Producto;
import proveedores.Proveedor;
import sistema_de_apartados.Sistema_de_Apartado;
import sueldos.Sueldo;
import tallas.Talla;
import tallas_es_de_productos.Talla_Es_De_Producto;
import telefonos.Telefono;
import ticket_se_incluye_en_producto.Ticket_Se_Incluye_En_Producto;
import tickets.Ticket;
import tipos_telefonos.Tipo_Telefono;
import usuarios.Usuario;

public class SQL_Crear {
    
    //INSERTAR
    public Boolean insertaArea(Area nueva_area){
        try{
            String query="INSERT INTO AREA (cve_area, nombre_area) values(?, ?)";
            PreparedStatement st=Conexion.prepareStatement(query);
            st.setInt(1, nueva_area.getCve_area());
            st.setString(2, nueva_area.getNombre());
            st.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaCapacitacion(Capacitacion nueva_capacitacion){
        try{
        String query="INSERT INTO CAPACITACION (nombre_capacitacion,nombre_capacitador,cve_area) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        //autoincrement
        st.setString(1, nueva_capacitacion.getNombre_capacitacion());
        st.setString(2, nueva_capacitacion.getNombre_capacitador());
        st.setInt(3, nueva_capacitacion.getCve_area());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaPais(Pais nuevo_pais){
        try{
        String query="INSERT INTO pais (cve_pais, nombre_pais) values(?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevo_pais.getCve_pais());
        st.setString(2, nuevo_pais.getNombre_pais());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaTalla(Talla nuevo_pais){
        try{
        String query="INSERT INTO TALLA (numero) values(?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_pais.getTalla());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaUsuario(Usuario nuevo_usuario){
        try{
            String query="INSERT INTO usuario (usuario, contraseña, no_empleado) values(?, ?, ?)";
            PreparedStatement st=Conexion.prepareStatement(query);
            st.setInt(1, nuevo_usuario.getUsuario());
            st.setString(2, nuevo_usuario.getContraseña());
            st.setInt(3, nuevo_usuario.getUsuario());
            st.execute();
            return true;
        }catch(SQLException e){System.out.println("SQL: "+e);}
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaSueldo(Sueldo nuevo_sueldo){
        try{
        String query="INSERT INTO SUELDO (nivel, monto) values(?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_sueldo.getNivel());
        st.setInt(2, nuevo_sueldo.getMonto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaEmpleado(Empleado nuevo_empleado){
        try{
        String query="INSERT INTO EMPLEADO (nombre_empleado,direccion_empleado,fecha_contratacion,nivel) values(?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevo_empleado.getNombre_empleado());
        st.setString(2, nuevo_empleado.getDireccion_empleado());
        st.setDate(3, nuevo_empleado.getFecha_contratacion());
        st.setInt(4, nuevo_empleado.getNivel());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaTipo_Telefono(Tipo_Telefono nuevo_Tipo_Telefono){
        try{
        String query="INSERT INTO TIPO_TELEFONO (cve_telefono, tipo) values(?,?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        //autoincrement
        st.setString(1, nuevo_Tipo_Telefono.getCve_telefono());
        st.setString(2, nuevo_Tipo_Telefono.getTipo());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaTelefono(Telefono nuevo_telefono){
        try{
        String query="INSERT INTO TELEFONO (numero,no_empleado,cve_telefono) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        //autoincrement
        st.setString(1, nuevo_telefono.getNumero());
        st.setInt(2, nuevo_telefono.getNo_empleado());
        st.setString(3, nuevo_telefono.getCve_telefono());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaCliente(Cliente nuevo_cliente){
        try{
        String query="INSERT INTO CLIENTE (nombre_cliente,domicilio,RFC,correo) values(?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevo_cliente.getNombre_cliente());
        st.setString(2, nuevo_cliente.getDomicilio());
        st.setString(3, nuevo_cliente.getRFC());
        st.setString(4, nuevo_cliente.getCorreo());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaDepartamento(Departamento nuevo_){
        try{
        String query="INSERT INTO DEPARTAMENTO (cve_depto, nombre_depto) values(?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCve_depto());
        st.setString(2, nuevo_.getNombre_depto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaProveedor(Proveedor nuevo_){
        try{
        String query="INSERT INTO PROVEEDOR (cve_proveedor, nombre_proveedor,direccion , RFC) values(?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCve_proveedor());
        st.setString(2, nuevo_.getNombre_proveedor());
        st.setString(3, nuevo_.getDireccion());
        st.setString(4, nuevo_.getRFC());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaProducto(Producto nuevo_){
        try{
        String query="INSERT INTO PRODUCTOS (cve_producto, descripcion, marca, modelo, precio_venta, precio_compra, cve_depto, cve_pais, cve_proveedor) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCve_producto());
        st.setString(2, nuevo_.getDescripcion());
        st.setString(3, nuevo_.getMarca());
        st.setString(4, nuevo_.getModelo());
        st.setDouble(5, nuevo_.getPrecio_venta());
        st.setDouble(6, nuevo_.getPrecio_compra());
        st.setInt(7, nuevo_.getCve_depto());
        st.setString(8, nuevo_.getCve_pais());
        st.setInt(9, nuevo_.getCve_proveedor());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
     
    public Boolean insertaTicket(Ticket nuevo_){
        try{
        String query="INSERT INTO TICKET (fecha_compra, total_compra, no_empleado) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setDate(1, nuevo_.getFecha_compra());
        st.setDouble(2, nuevo_.getTotal_compra());
        st.setInt(3, nuevo_.getNo_empleado());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaEmpleado_Recibe_Capacitacion(Empleado_Recibe_Capacitacion nuevo_empleado_recibe_capacitacion){
        try{
        String query="INSERT INTO EMPLEADO_RECIBE_CAPACITACION (evaluacion,no_capacitacion,no_empleado) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        //autoincrement
        st.setInt(1, nuevo_empleado_recibe_capacitacion.getEvaluacion());
        st.setInt(2, nuevo_empleado_recibe_capacitacion.getNo_capacitacion());
        st.setInt(3, nuevo_empleado_recibe_capacitacion.getNo_empleado());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaOrden(Orden nuevo_cliente){
        try{
        String query="INSERT INTO ORDEN (fecha_orden,fecha_entrega,subtotal,IVA,pago_total,cve_proveedor) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setDate(1, nuevo_cliente.getFecha_orden());
        st.setDate(2, nuevo_cliente.getFecha_entrega());
        st.setDouble(3, nuevo_cliente.getSubtotal());
        st.setDouble(4, nuevo_cliente.getIVA());
        st.setDouble(5, nuevo_cliente.getPago_total());
        st.setInt(6, nuevo_cliente.getCve_proveedor());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaOrden_Pide_Producto(Orden_Pide_Producto nuevo_){
        try{
        String query="INSERT INTO ORDEN_PIDE_PRODUCTOS (cantidad,no_orden,cve_producto) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCantidad());
        st.setInt(2, nuevo_.getNo_orden());
        st.setInt(3, nuevo_.getCve_producto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaTallas_Es_De_Productos(Talla_Es_De_Producto nuevo_){
        try{
        String query="INSERT INTO TALLAS_ESDE_PRODUCTO (cantidad_existencia,numero,cve_producto) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCantidad_existencia());
        st.setInt(2, nuevo_.getNumero());
        st.setInt(3, nuevo_.getCve_producto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaSistema_De_Apartado(Sistema_de_Apartado nuevo_){
        try{
        String query="INSERT INTO SISTEMA_DE_APARTADO (fecha_apartado, fecha_liquidacion, engache, restantes, no_empleado, no_cliente, cve_depto ) values(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setDate(1, nuevo_.getFecha_apartado());
        st.setDate(2, nuevo_.getFecha_liquidacion());
        st.setDouble(3, nuevo_.getEngache());
        st.setDouble(4, nuevo_.getRestantes());
        st.setInt(5, nuevo_.getNo_empleado());
        st.setInt(6, nuevo_.getNo_cliente());
        st.setInt(7, nuevo_.getCve_depto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaTicket_se_incluye_en_producto(Ticket_Se_Incluye_En_Producto nuevo_){
        try{
        String query="INSERT INTO TICKET_SEINCLUYEEN_PRODUCTO (cantidad, no_ticket, cve_producto) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCantidad());
        st.setInt(2, nuevo_.getNo_ticket());
        st.setInt(3, nuevo_.getCve_producto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaDetalle(Detalle nuevo_){
        try{
        String query="INSERT INTO detalle (cantidad, no_ticket, cve_producto) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCantidad());
        st.setInt(2, nuevo_.getNo_ticket());
        st.setInt(3, nuevo_.getCve_producto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaFactura(Factura nuevo_){
        try{
        String query="INSERT INTO FACTURA (fecha_compra,subtotal,IVA,total,no_ticket,no_cliente) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setDate(1, nuevo_.getFecha_compra());
        st.setDouble(2, nuevo_.getSubtotal());
        st.setDouble(3, nuevo_.getIVA());
        st.setDouble(4, nuevo_.getTotal());
        st.setInt(5, nuevo_.getNo_ticket());
        st.setInt(6, nuevo_.getNo_cliente());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    
    public Boolean insertaApartado_Tiene_Productos(Apartado_Tiene_Producto nuevo_){
        try{
        String query="INSERT INTO APARTADO_TIENE_PRODUCTOS (cantidad, cve_apartado, cve_producto) values(?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevo_.getCantidad());
        st.setInt(2, nuevo_.getCve_apartado());
        st.setInt(3, nuevo_.getCve_producto());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
}