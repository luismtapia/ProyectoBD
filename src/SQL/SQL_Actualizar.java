package SQL;

import static SQL.SQL.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author luis_
 */
public class SQL_Actualizar {
    //ACTUALIZAR
    public boolean ActualizaArea(int _cve_area, String _nombre_area, int cve_bandera){
        try{
            String query ="UPDATE AREA SET cve_area = ?, nombre_area = ? WHERE cve_area = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _cve_area);
            st.setString(2, _nombre_area);
            st.setInt(3, cve_bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaPais(String _cve_pais, String _nombre_pais, String cve_bandera){
        try{
            String query ="UPDATE pais SET cve_pais = ?, nombre_pais = ? WHERE cve_pais = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _cve_pais);
            st.setString(2, _nombre_pais);
            st.setString(3, cve_bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaTallas(int _numero, int numero_bandera){
        try{
            String query ="UPDATE TALLA SET numero = ? WHERE numero = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _numero);
            st.setInt(2, numero_bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaSueldo(int _nivel_sdo, int _monto_sdo, int cve_bandera){
        try{
            String query ="UPDATE SUELDO SET nivel = ?, monto = ? WHERE nivel = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _nivel_sdo);
            st.setInt(2, _monto_sdo);
            st.setInt(3, cve_bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaCapacitacion(int no_capacitacion, String _nombre_capacitacion, String _nombre_capacitador, int _area){
        try{
            String query ="UPDATE CAPACITACION SET nombre_capacitacion = ?, nombre_capacitador = ?, cve_area = ? WHERE no_capacitacion = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _nombre_capacitacion);
            st.setString(2, _nombre_capacitador);
            st.setInt(3, _area);
            st.setInt(4, no_capacitacion);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaEmpleado(int no_empleado, String _nombre_empleado, String _direccion_empleado, Date fecha_contratacion,int _nivel){
        try{
            String query ="UPDATE EMPLEADO SET nombre_empleado = ?, direccion_empleado = ?, fecha_contratacion = ?,  nivel = ? WHERE no_empleado = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _nombre_empleado);
            st.setString(2, _direccion_empleado);
            st.setDate(3, fecha_contratacion);
            st.setInt(4, _nivel);
            st.setInt(5, no_empleado);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaTipo_Telefono(String _cve_telefono, String _tipo_telefono, String cve_bandera){
        try{
            String query ="UPDATE TIPO_TELEFONO SET cve_telefono = ?, tipo = ? WHERE cve_telefono = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _cve_telefono);
            st.setString(2, _tipo_telefono);
            st.setString(3, cve_bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaUsuario(int usuario_, String contraseña, int cve_bandera){
        try{
            String query ="UPDATE USUARIO SET usuario = ?, contraseña = ? WHERE usuario = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, usuario_);
            st.setString(2, contraseña);
            st.setInt(3, cve_bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaCliente(int _no_cliente, String _nombre_cliente, String _direccion_cliente, String _RFC, String _correo){
        try{
            String query ="UPDATE CLIENTE SET nombre_cliente = ?, domicilio = ?, RFC = ?, correo = ? WHERE no_cliente = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _nombre_cliente);
            st.setString(2, _direccion_cliente);
            st.setString(3, _RFC);
            st.setString(4, _correo);
            st.setInt(5, _no_cliente);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaDepartamento(int _cve_depto, String _nombre, int _bandera){
        try{
            String query ="UPDATE DEPARTAMENTO SET cve_depto = ?, nombre_depto = ? WHERE cve_depto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _cve_depto);
            st.setString(2, _nombre);
            st.setInt(3, _bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaTicket(int _no_ticket, Date _fecha_compra, double _total_compra, int no_empleado){
        try{
            String query ="UPDATE TICKET SET fecha_compra = ?, total_compra = ?, no_empleado = ? WHERE no_ticket = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setDate(1, _fecha_compra);
            st.setDouble(2, _total_compra);
            st.setInt(3,no_empleado);
            st.setInt(4, _no_ticket);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaProducto(int _cve_producto, String _descripcion, String _marca, String _modelo, double precio_venta, double precio_compra,int cve_depto, String cve_pais, int cve_proveedor){
        try{
            String query ="UPDATE PRODUCTOS SET cve_producto = ?, descripcion = ?, marca = ?, modelo = ?, precio_venta = ?, precio_compra = ?, cve_depto = ?,cve_pais = ?, cve_proveedor = ? WHERE cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _cve_producto);
            st.setString(2, _descripcion);
            st.setString(3, _marca);
            st.setString(4, _modelo);
            st.setDouble(5, precio_venta);
            st.setDouble(6, precio_compra);
            st.setInt(7, cve_depto);
            st.setString(8, cve_pais);
            st.setInt(9, cve_proveedor);
            st.setInt(10, _cve_producto);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaProveedor(int _cve_proveedor, String _nombre, String _direccion, String _rfc, int bandera){
        try{
            String query ="UPDATE PROVEEDOR SET cve_proveedor = ?, nombre_proveedor = ?, direccion = ?, RFC = ? WHERE cve_proveedor = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _cve_proveedor);
            st.setString(2, _nombre);
            st.setString(3, _direccion);
            st.setString(4, _rfc);
            st.setInt(5, bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaOrden(Date _fecha_orden, Date _fecha_entrega, Double _subtotal, Double _IVA, Double _pago_total, int _cve_proveedor,int bandera){
        try{
            String query ="UPDATE ORDEN SET fecha_orden = ?, fecha_entrega = ?, subtotal = ?, IVA = ?, pago_total = ?, cve_proveedor = ? WHERE no_orden = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setDate(1, _fecha_orden);
            st.setDate(2, _fecha_entrega);
            st.setDouble(3, _subtotal);
            st.setDouble(4, _IVA);
            st.setDouble(5, _pago_total);
            st.setInt(6, _cve_proveedor);
            st.setInt(7, bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaOrden_Pide_Productos(int cantidad, int no_orden, int cve_producto ,int bandera1,int bandera2){
        try{
            String query ="UPDATE ORDEN_PIDE_PRODUCTOS SET cantidad = ?, no_orden = ?, cve_producto = ? WHERE no_orden = ? AND cve_producto = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cantidad);
            st.setInt(2, no_orden);
            st.setInt(3, cve_producto);
            st.setInt(4, bandera1);
            st.setInt(5, bandera2);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaTallas_Es_De_Producto(int cantidad, int numero, int cve_producto ,int bandera1,int bandera2){
        try{
            String query ="UPDATE TALLAS_ESDE_PRODUCTO SET cantidad_existencia = ?, numero = ?, cve_producto = ? WHERE numero = ? AND cve_producto = ?";
            //String query ="exec excedeExistencia ?,?,?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, cantidad);
            st.setInt(2, numero);
            st.setInt(3, cve_producto);
            st.setInt(4, bandera1);
            st.setInt(5, bandera2);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaTelefono(String numero_, int no_empleado_, String cve_telefono_, String cve_bandera){
        try{
            String query ="UPDATE TELEFONO SET numero = ?, no_empleado = ?, cve_telefono = ? WHERE numero = ? ";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, numero_);
            st.setInt(2, no_empleado_);
            st.setString(3, cve_telefono_);
            st.setString(4, cve_bandera);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaEmpleado_Recibe_Capacitacion(int _evaluacion, int _no_capacitacion, int _no_empleado,int cve_bandera, int cve_bandera1){
        try{
            String query ="UPDATE EMPLEADO_RECIBE_CAPACITACION SET evaluacion = ?, no_capacitacion = ?, no_empleado = ? WHERE no_empleado = ? AND no_capacitacion = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _evaluacion);
            st.setInt(2, _no_capacitacion);
            st.setInt(3, _no_empleado);
            st.setInt(4, cve_bandera);
            st.setInt(5, cve_bandera1);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaTalla_es_de_Producto(int _cantidad_existencia, int _numero, int _cve_producto){
        try{
            String query ="exec excedeExistencia ?,?,?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, _cantidad_existencia);
            st.setInt(2, _numero);
            st.setInt(3, _cve_producto);
            System.out.println(st.execute());
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
}