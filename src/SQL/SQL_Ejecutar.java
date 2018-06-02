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
public class SQL_Ejecutar {
    public ResultSet ejecutarCompras(int no_ticket, Double total_a_pagar, int no_empleado) {
            String query = "exec procedure compra "+no_ticket+",GETDATE(),"+total_a_pagar+","+no_empleado;
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
    
    public ResultSet ejecutarexcedeExistencia(int cantidad_a_quitar,int numero, int cve_producto) {
            String query = "exec excedeExistencia "+cantidad_a_quitar+","+numero+","+cve_producto;
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println("ERROR EN SQL: "+e);
            }
            return resultado;
    }
}