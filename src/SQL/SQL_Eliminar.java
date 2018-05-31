/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import static SQL.SQL.Conexion;
import java.sql.PreparedStatement;

/**
 *
 * @author luis_
 */
public class SQL_Eliminar {
    //ELIMINAR
    public boolean eliminaArea(int cve_area){
        try
        {
            String query ="DELETE FROM AREA WHERE cve_area = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cve_area);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaCapacitacion(int no_capacitacion){
        try{
            String query ="DELETE FROM CAPACITACION WHERE no_capacitacion = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_capacitacion);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaPais(String cve_pais){
        try
        {
            String query ="DELETE FROM pais WHERE cve_pais = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, cve_pais);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean eliminaTalla(int numero){
        try
        {
            String query ="DELETE FROM TALLA WHERE numero = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, numero);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaSueldo(int nivel){
        try{
            String query ="DELETE FROM SUELDO WHERE nivel = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, nivel);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaEmpleado(int no_empleado){
        try{
            String query ="DELETE FROM EMPLEADO WHERE no_empleado = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_empleado);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaUsuario(int usuario){
        try{
            String query ="DELETE FROM USUARIO WHERE usuario = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, usuario);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaTipo_Telefono(String cve_telefono){
        try{
            String query ="DELETE FROM TIPO_TELEFONO WHERE cve_telefono = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, cve_telefono);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaTelefono(String numero){
        try{
            String query ="DELETE FROM TELEFONO WHERE numero = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, numero);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaEmpleado_Recibe_Capacitacion(int evaluacion){
        try{
            String query ="DELETE FROM EMPLEADO_RECIBE_CAPACITACION WHERE evaluacion = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, evaluacion);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaCliente(int no_cliente){
        try{
            String query ="DELETE FROM CLIENTE WHERE no_cliente = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_cliente);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaTicket(int no_ticket){
        try{
            String query ="DELETE FROM TICKET WHERE no_ticket = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_ticket);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaFactura(int no_factura){
        try{
            String query ="DELETE FROM FACTURA WHERE no_factura = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_factura);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaDepartamento(int cve_depto){
        try{
            String query ="DELETE FROM DEPARTAMENTO WHERE cve_depto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cve_depto);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaSistema_de_Apartado(int cve_apartado){
        try{
            String query ="DELETE FROM SISTEMA_DE_APARTADO WHERE cve_apartado = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cve_apartado);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaProveedor(int cve_proveedor){
        try{
            String query ="DELETE FROM PROVEEDOR WHERE cve_proveedor = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cve_proveedor);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaProductos(int cve_producto){
        try{
            String query ="DELETE FROM PRODUCTOS WHERE cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cve_producto);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaOrden(int no_orden){
        try{
            String query ="DELETE FROM ORDEN WHERE no_orden = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_orden);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaOrden_Pide_Productos(int no_orden, int cve_producto){
        try{
            String query ="DELETE FROM ORDEN_PIDE_PRODUCTOS WHERE no_orden = ? AND cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_orden);
            st.setInt(2, cve_producto);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaTicket_se_incluye_En_Producto(int no_ticket, int cve_producto){
        try{
            String query ="DELETE FROM TICKET_SEINCLUYEEN_PRODUCTO WHERE no_ticket = ? AND cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, no_ticket);
            st.setInt(2, cve_producto);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaApartado_Tiene_Productos(int cve_apartado, int cve_producto){
        try{
            String query ="DELETE FROM APARTADO_TIENE_PRODUCTOS WHERE cve_apartado = ? AND cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cve_apartado);
            st.setInt(2, cve_producto);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaTallas_Es_De_Producto(int numero, int cve_producto){
        try{
            String query ="DELETE FROM TALLAS_ESDE_PRODUCTO WHERE numero = ? AND cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, numero);
            st.setInt(2, cve_producto);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaDetalle(int ticket, int cve_producto){
        try{
            String query ="DELETE FROM detalle WHERE no_ticket = ? AND cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, ticket);
            st.setInt(2, cve_producto);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
