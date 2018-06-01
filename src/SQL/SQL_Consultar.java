package SQL;

import static SQL.SQL.Conexion;
import apartado_tiene_productos.Apartado_Tiene_Producto;
import areas.Area;
import capacitaciones.Capacitacion;
import clientes.Cliente;
import departamentos.Departamento;
import empleados.Empleado;
import empleados_reciben_capacitacion.Empleado_Recibe_Capacitacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 *
 * @author luis_
 */
public class SQL_Consultar {
    
    public ObservableList<Talla_Es_De_Producto> getTallas_Por_Clave_Producto_List(int cve_producto) {
        ObservableList<Talla_Es_De_Producto> lista= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TALLAS_ESDE_PRODUCTO WHERE cve_producto = '"+cve_producto+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista.add(new Talla_Es_De_Producto(
                    rs.getInt("cantidad_existencia"),
                    rs.getInt("numero"),
                    rs.getInt("cve_producto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista;
    }
    
    public ObservableList<Ticket> getTicket() {
        ObservableList<Ticket> lista_= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM TICKET";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_.add(new Ticket(
                    rs.getInt("no_ticket"),
                    rs.getDate("fecha_compra"),
                    rs.getDouble("total_compra"),
                    rs.getInt("no_empleado")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_;
    }
    
    public ObservableList<Ticket_Se_Incluye_En_Producto> getTicket_Se_Incluye() {
        ObservableList<Ticket_Se_Incluye_En_Producto> lista_= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM TICKET_SEINCLUYEEN_PRODUCTO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_.add(new Ticket_Se_Incluye_En_Producto(
                    rs.getInt("cantidad"),
                    rs.getInt("no_ticket"),
                    rs.getInt("cve_producto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_;
    }
    
    public ObservableList<Area> getAreas() {
        ObservableList<Area> lista_areas= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM AREA";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_areas.add(new Area(
                    rs.getInt("cve_area"),
                    rs.getString("nombre_area")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_areas;
    }
    
    public ObservableList<Capacitacion> getCapacitacion() {
        ObservableList<Capacitacion> lista_capacitaciones= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM CAPACITACION";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_capacitaciones.add(new Capacitacion(
                    rs.getInt("no_capacitacion"),
                    rs.getString("nombre_capacitacion"),
                    rs.getString("nombre_capacitador"),
                    rs.getInt("cve_area")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_capacitaciones;
    }
    
    public ObservableList<Pais> getPaises() {
        ObservableList<Pais> lista_paises= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM pais";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_paises.add(new Pais(
                    rs.getString("cve_pais"),
                    rs.getString("nombre_pais")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_paises;
    }
    
    public ObservableList<Talla> getTallas() {
        ObservableList<Talla> lista_tallas= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM TALLA";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_tallas.add(new Talla(
                    rs.getInt("numero")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_tallas;
    }
    
    public ObservableList<Empleado> getEmpleados() {
        ObservableList<Empleado> lista = FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM EMPLEADO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista.add(new Empleado(
                    rs.getInt("no_empleado"),
                    rs.getString("nombre_empleado"),
                    rs.getString("direccion_empleado"),
                    rs.getDate("fecha_contratacion"),
                    rs.getInt("nivel")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista;
    }
    
    public ObservableList<Usuario> getUsuarios() {
        ObservableList<Usuario> lista_usuarios= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM usuario";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_usuarios.add(new Usuario(
                    rs.getInt("usuario"),
                    rs.getString("contraseña")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_usuarios;
    }
    
    public ObservableList<Sueldo> getSueldo() {
        ObservableList<Sueldo> lista_sueldos= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM SUELDO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_sueldos.add(new Sueldo(
                    rs.getInt("nivel"),
                    rs.getInt("monto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_sueldos;
    }
    
    public ObservableList<Tipo_Telefono> getTipo_Telefono() {
        ObservableList<Tipo_Telefono> lista_tipos_telefonos= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM TIPO_TELEFONO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_tipos_telefonos.add(new Tipo_Telefono(
                    rs.getString("cve_telefono"),
                    rs.getString("tipo")
                    
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_tipos_telefonos;
    }
     
    public ObservableList<Cliente> getClientes() {
        ObservableList<Cliente> lista_clientes= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM CLIENTE";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_clientes.add(new Cliente(
                    rs.getInt("no_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("domicilio"),
                    rs.getString("RFC"),
                    rs.getString("correo")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_clientes;
    }
     
    public ObservableList<Departamento> getDepartamentos() {
        ObservableList<Departamento> lista= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM DEPARTAMENTO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista.add(new Departamento(
                    rs.getInt("cve_depto"),
                    rs.getString("nombre_depto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista;
    }
     
    public ObservableList<Proveedor> getProveedores() {
        ObservableList<Proveedor> lista= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM PROVEEDOR";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista.add(new Proveedor(
                    rs.getInt("cve_proveedor"),
                    rs.getString("nombre_proveedor"),
                    rs.getString("direccion"),
                    rs.getString("RFC")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista;
    }
     
    public ObservableList<Producto> getProductos() {
        ObservableList<Producto> lista= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM PRODUCTOS";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista.add(new Producto(
                    rs.getInt("cve_producto"),
                    rs.getString("descripcion"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_compra"),
                    rs.getInt("cve_depto"),
                    rs.getString("cve_pais"),
                    rs.getInt("cve_proveedor")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista;
    }
     
    public ObservableList<Telefono> getTelefono() {
        ObservableList<Telefono> lista_telefonos= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM TELEFONO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_telefonos.add(new Telefono(
                    rs.getString("numero") ,
                    rs.getInt("no_empleado"),
                    rs.getString("cve_telefono")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_telefonos;
    }
     
    public ObservableList<Empleado_Recibe_Capacitacion> getEmpleado_Recibe_Capacitacion() {
        ObservableList<Empleado_Recibe_Capacitacion> lista_capacitacionesDempleados= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM EMPLEADO_RECIBE_CAPACITACION";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_capacitacionesDempleados.add(new Empleado_Recibe_Capacitacion(
                    rs.getInt("evaluacion") ,
                    rs.getInt("no_capacitacion"),
                    rs.getInt("no_empleado")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_capacitacionesDempleados;
    }
    
    public ObservableList<Orden> getOrdenes() {
        ObservableList<Orden> lista_= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM ORDEN";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_.add(new Orden(
                    rs.getInt("no_orden"),
                    rs.getDate("fecha_orden"),
                    rs.getDate("fecha_entrega"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("IVA"),
                    rs.getDouble("pago_total"),
                    rs.getInt("cve_proveedor")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_;
    }
    
    public ObservableList<Orden_Pide_Producto> getOrden_Pide_Producto() {
        ObservableList<Orden_Pide_Producto> lista_= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM ORDEN_PIDE_PRODUCTOS";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_.add(new Orden_Pide_Producto(
                    rs.getInt("cantidad"),
                    rs.getInt("no_orden"),
                    rs.getInt("cve_producto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_;
    }
    
    public ObservableList<Talla_Es_De_Producto> getTallas_Es_De_Productos() {
        ObservableList<Talla_Es_De_Producto> lista_= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TALLAS_ESDE_PRODUCTO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_.add(new Talla_Es_De_Producto(
                    rs.getInt("cantidad_existencia"),
                    rs.getInt("numero"),
                    rs.getInt("cve_producto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_;
    }
    
    public ObservableList<Sistema_de_Apartado> getSistema_de_Apartado() {
        ObservableList<Sistema_de_Apartado> lista_= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM SISTEMA_DE_APARTADO";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_.add(new Sistema_de_Apartado(
                    rs.getInt("cve_apartado"),
                    rs.getDate("fecha_apartado"),
                    rs.getDate("fecha_liquidacion"),
                    rs.getDouble("enganche"),
                    rs.getDouble("restantes"),
                    rs.getInt("no_empleado"),
                    rs.getInt("no_cliente"),
                    rs.getInt("cve_depto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_;
    }
    
    
    public ObservableList<Apartado_Tiene_Producto> getApartado_Tiene_Producto() {
        ObservableList<Apartado_Tiene_Producto> lista_= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM APARTADO_TIENE_PRODUCTOS";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista_.add(new Apartado_Tiene_Producto(
                    rs.getInt("cantidad"), 
                    rs.getInt("cve_apartado"),
                    rs.getInt("cve_producto")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista_;
    }
    
            
    ////LEER TODOS
    public ResultSet consultarDESDEtabla(String tabla) {
            String query = "SELECT * FROM "+tabla;
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    public ResultSet consultarArea() {
            String query = "SELECT * FROM AREA";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarSueldo() {
            String query = "SELECT * FROM SUELDO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarNivel() {
            String query = "SELECT * FROM SUELDO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarTipo_Telefono() {
            String query = "SELECT * FROM TIPO_TELEFONO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarEmpleado() {
            String query = "SELECT * FROM EMPLEADO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarCapacitacion() {
            String query = "SELECT * FROM CAPACITACION";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarDepartamentos() {
            String query = "SELECT * FROM DEPARTAMENTO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarPaises() {
            String query = "SELECT * FROM PAIS";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarTallas() {
            String query = "SELECT * FROM TALLA";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarProductos() {
            String query = "SELECT * FROM PRODUCTOS";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarProveedores() {
            String query = "SELECT * FROM PROVEEDOR";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarOrdenes() {
            String query = "SELECT * FROM ORDEN";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet consultarUsuarios() {
            String query = "SELECT * FROM USUARIO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    public ResultSet consultarSistema_De_Apartado() {
            String query = "SELECT * FROM SISTEMA_DE_APARTADO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    public ResultSet consultarClientes() {
            String query = "SELECT * FROM CLIENTE";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    public ResultSet consultarTickets() {
            String query = "SELECT * FROM TICKET";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    public ResultSet consultarTalla_Es_De_Producto() {
            String query = "SELECT * FROM TALLAS_ESDE_PRODUCTO";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    
    public ResultSet consultarDetalle() {
            String query = "SELECT * FROM detalle";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    
    
    
    //BUSQUEDA POR NOMBRE
    public ResultSet getAreaPorNombre(String nombre){
        String query="SELECT * FROM AREA WHERE nombre_area = '"+nombre+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getAreaPorClave(int cve_area){
        String query="SELECT * FROM AREA WHERE cve_area = '"+cve_area+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getDepartamentoPorNombre(String nombre){
        String query="SELECT * FROM DEPARTAMENTO WHERE nombre_depto = '"+nombre+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getEmpleadoPorNumero(int numero_emp){
        String query="SELECT * FROM EMPLEADO WHERE no_empleado = '"+numero_emp+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getEmpleadoPorNombre(String nombre_emp){
        String query="SELECT * FROM EMPLEADO WHERE nombre_empleado = '"+nombre_emp+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getTipo_TelefonoPorClave(String cve_telefono){
        String query="SELECT * FROM TIPO_TELEFONO WHERE cve_telefono = '"+cve_telefono+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getPaisPorNombre(String nombre){
        String query="SELECT * FROM PAIS WHERE nombre_pais = '"+nombre+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getProveedorPorNombre(String nombre){
        String query="SELECT * FROM PROVEEDOR WHERE nombre_proveedor = '"+nombre+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getProductoPorCodigo(int codigo){
        String query="SELECT * FROM PRODUCTOS WHERE cve_producto = '"+codigo+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getTipo_TelefonoPorTipo(String tipo_telefono){
        String query="SELECT * FROM TIPO_TELEFONO WHERE tipo = '"+tipo_telefono+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getCapacitacionPorNombre(String nombre_cap){
        String query="SELECT * FROM CAPACITACION WHERE nombre_capacitacion = '"+nombre_cap+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getCapacitacionPorNo_Capacitacion(int no_cap){
        String query="SELECT * FROM CAPACITACION WHERE no_capacitacion = '"+no_cap+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getProveedorPorClave(int cve_proveedor){
        String query="SELECT * FROM PROVEEDOR WHERE cve_proveedor = '"+cve_proveedor+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getPaisPorClave(String cve_pais){
        String query="SELECT * FROM PAIS WHERE cve_pais = '"+cve_pais+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getDepartamentoPorClave(int cve_depto){
        String query="SELECT * FROM DEPARTAMENTO WHERE cve_depto = '"+cve_depto+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getTallas_Es_De_Producto_Por_Cve_Producto(int cve_producto){
        String query="SELECT * FROM TALLAS_ESDE_PRODUCTO WHERE cve_producto = '"+cve_producto+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getClientePorNombre(String nombre_cliente){
        String query="SELECT * FROM CLIENTE WHERE nombre_cliente = '"+nombre_cliente+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getTicketPorNumero(int no_ticket){
        String query="SELECT * FROM TICKET WHERE no_ticket = '"+no_ticket+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getTalla_Es_De_ProductoPorClaves(int _numero, int _cve_producto){
        String query="SELECT * FROM TALLAS_ESDE_PRODUCTO WHERE numero = '"+_numero+"' AND cve_producto = '"+_cve_producto+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    //--------------------------------------------------
    
    public ResultSet consultarDepartamento_Especifico(String departamento) {
            //String query = "select * from VistaProductosDepto"+departamento;
            String query = "SELECT cve_depto FROM PRODUCTOS WHERE cve_depto in(SELECT cve_depto FROM DEPARTAMENTO WHERE nombre_depto = '"+departamento+"')";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    public ObservableList<Producto> getDepartamento_Especifico(int depto) {
        ObservableList<Producto> lista= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM PRODUCTOS WHERE cve_depto = '"+depto+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                lista.add(new Producto(
                    rs.getInt("cve_producto"),
                    rs.getString("descripcion"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_compra"),
                    rs.getInt("cve_depto"),
                    rs.getString("cve_pais"),
                    rs.getInt("cve_proveedor")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return lista;
    }
    
    public ResultSet getUltimo_Ticket(){
        String query="SELECT TOP 1 * FROM TICKET ORDER BY no_ticket DESC";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getUltimo_Apartado(){
        String query="SELECT TOP 1 * FROM SISTEMA_DE_APARTADO ORDER BY cve_apartado DESC";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getCount(int ticket, int producto){
        String query="select COUNT(*) total FROM DETALLE WHERE no_ticket = '"+ticket+"' AND cve_producto ='"+producto+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
}