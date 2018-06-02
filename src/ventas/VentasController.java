package ventas;

import empleados.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import SQL.SQL_Actualizar;
import SQL.SQL_Consultar;
import SQL.SQL_Crear;
import SQL.SQL_Ejecutar;
import SQL.SQL_Eliminar;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import productos.Producto;
import tallas_es_de_productos.Talla_Es_De_Producto;
import ticket_se_incluye_en_producto.Ticket_Se_Incluye_En_Producto;
import tickets.Ticket;

public class VentasController implements Initializable{
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    SQL_Ejecutar con_ejecutar = new SQL_Ejecutar();
    int no_empleado_anterior;
    
    @FXML
    private TextField cantidad;
    @FXML
    private Button btn_depto_1,btn_depto_2,btn_ver_tallas,btn_agregar_a_ticket,btn_quitar_de_ticket,btn_cancelar,btn_cobrar;
    @FXML
    private Label lbl1,lbl2,lbl3,lbl4,lbl_total_a_pagar;
    @FXML
    private ComboBox cmb_cliente;
    @FXML
    private ListView lista1,lista2,lista3;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
        if(event.getSource() == btn_depto_1){
            lbl3.setText("ROPA");
            llenarProductos();
        }else
            if(event.getSource() == btn_depto_2){
                lbl3.setText("ACCESORIOS");
                llenarProductos();
            }else
                if(event.getSource() == btn_ver_tallas){
                    buscarTallas();
                }else
                    if(event.getSource() == btn_agregar_a_ticket){
                        agregarATicket();
                    }else
                        if(event.getSource() == btn_quitar_de_ticket){
                            quitarATicket();
                        }else
                        if(event.getSource() == btn_cancelar){
                            
                        }else
                            if(event.getSource() == btn_cobrar){
                                
                            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        color(Color.web("#D4AF37"));
        llenarcomboboxCliente();
        imagenes();
//        nombre_empleado.setPromptText("Nombre del empleado");
//        direccion_empleado.setPromptText("Direccion del Empleado");
    }
    
    private void color(Color color){
        btn_depto_1.setTextFill(color);
        btn_depto_2.setTextFill(color);
        btn_agregar_a_ticket.setTextFill(color);
        btn_ver_tallas.setTextFill(color);
        btn_quitar_de_ticket.setTextFill(color);
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
        lbl_total_a_pagar.setTextFill(color);
    }
    
    private void llenarProductos(){
        String depto = lbl3.getText();
        int _cve_departamento=0;
        try {
            lista1.getItems().clear();
            
            if(depto.equals("ROPA")){
                _cve_departamento = 40;
            }else
                if(depto.equals("ACCESORIOS")){
                    _cve_departamento = 50;
                }
            lista1.setItems(con_consultar.getDepartamento_Especifico(_cve_departamento));
            
        } catch (Exception e) {System.err.println(""+e);}
    }
    
    private void agregarATicket() throws SQLException{
        try{
            int  cantidad_anterior=0, numero=0, cve_producto=0,cantidad_nueva=1,cant=0;
            double total=0,precio_venta=0;
            int cantidad_de_compra_y_quitar = Integer.parseInt(cantidad.getText());
            String descripcion = null;
            List<Talla_Es_De_Producto> sexistencia = lista2.getSelectionModel().getSelectedItems();
                for(int i=0;i<sexistencia.size();i++){
                    Talla_Es_De_Producto seleccion = sexistencia.get(i);
                    cantidad_anterior = seleccion.getCantidad_existencia();
                    numero = seleccion.getNumero();
                    cve_producto = seleccion.getCve_producto();
                }
            System.out.println(""+cve_producto);
            //ejecuta existencia despues revisa esta actualizacion hecha
            con_ejecutar.ejecutarexcedeExistencia(cantidad_de_compra_y_quitar, numero, cve_producto);
            ResultSet rs = con_consultar.consultarDESDEtablaCONespecificacion("TALLAS_ESDE_PRODUCTO", "cantidad_existencia","cve_producto",cve_producto+" AND numero = "+numero);
            while(rs.next()){
                cantidad_nueva = rs.getInt("cantidad_existencia");
            }
            
            if(cantidad_anterior == cantidad_nueva){
                Alert m=new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Actualizando existencia");
                m.setContentText("no se puede procesar compra");
                //m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
                m.show();
            }else{
                for (int j = 1; j <= cantidad_de_compra_y_quitar; j++) {
                    List<Producto> sval1 = lista1.getSelectionModel().getSelectedItems();
                        for(int i=0;i<sval1.size();i++){
                            Producto seleccion_inven = sval1.get(i);
                            cve_producto = seleccion_inven.getCve_producto();
                            descripcion = seleccion_inven.getDescripcion();
                            precio_venta = seleccion_inven.getPrecio_venta();
                        }
                    List<Talla_Es_De_Producto> selec = lista2.getSelectionModel().getSelectedItems();
                        for(int i=0;i<selec.size();i++){
                            Talla_Es_De_Producto selecc = selec.get(i);
                            numero = selecc.getNumero();
                        }
                    VistaTicket nota = new VistaTicket(cve_producto, descripcion, precio_venta, numero);
                            lista3.getItems().add(nota);
                            total += precio_venta;
                            lbl1.setText(""+total);
                }
            }
            buscarTallas();
        }catch(NumberFormatException e){
            System.out.println(""+e);
        }
    }
    
    private void quitarATicket(){
        List<VistaTicket> sval = lista3.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                VistaTicket seleccionado = sval.get(i);
                try{   
                    lista3.getItems().remove(seleccionado);
                    Double total = Double.parseDouble(lbl1.getText()) - seleccionado.getPrecio_venta();
                    int numero = seleccionado.getNumero();
                    int cve_producto = seleccionado.getCve_producto();
                    lbl1.setText(""+total);
                    //*******************************************************************
                    //con_actualizar.Actualizar1Campo("TALLAS_ESDE_PRODUCTOS", "cantidad_existencia", "(select max(cantidad_existencia,0))-1", " where numero = "+numero+" AND cve_producto = "+cve_producto);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }
    
    private void buscarTallas() throws SQLException{
        try{
//            ResultSet rs = con_consultar.getUltimo_Ticket();
//            while(rs.next()){
//                _no_ticket = rs.getInt("no_ticket");
//            }
//            no_ticket_a_generar.setText(""+_no_ticket);
            
            List<Producto> sval = lista1.getSelectionModel().getSelectedItems();
                for(int i=0;i<sval.size();i++){
                    Producto seleccion = sval.get(i);
                    int codigo = seleccion.getCve_producto();
                    llenarTallas(codigo);
                }
        }catch(Exception e){System.out.println(""+e);}
    }
    
    private void llenarTallas(int codigo){
        lista2.getItems().clear();
        lista2.setItems(con_consultar.getTallas_Por_Clave_Producto_List(codigo));
    }
    
    private void imagenes(){
        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void ejecutar_compra(){
//        try {
//            Ticket nuevo_ticket = new Ticket(0, metodo.fecha(), total, Integer.parseInt(usuario[6]));
//            if(con_crear.insertaTicket(nuevo_ticket)){
//                Alert msg=new Alert(Alert.AlertType.INFORMATION);
//                msg.setTitle("Compra registrada");
//                msg.setContentText("Su compra fue relizada correctamente");
//                msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
//                msg.show();
//                
//                ResultSet rs = con_consultar.getUltimo_Ticket();
//                while(rs.next()){
//                    _no_ticket = rs.getInt("no_ticket");
//                }
//             }else{
//                Alert msg=new Alert(Alert.AlertType.INFORMATION);
//                msg.setTitle("Hay un Problema");
//                msg.setContentText("Llame a SOPORTE TÉCNICO");
//                msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
//                msg.show();
//            }
            
//        } catch (NumberFormatException | SQLException e) {}
    }
    
    private void llenarcomboboxCliente(){
        cmb_cliente.getItems().clear();
        cmb_cliente.setPromptText("SELECCIONE EL CLIENTE");
        ResultSet rs = con_consultar.consultarDESDEtabla("CLIENTE");
        try {
            while (rs.next()) {                
                cmb_cliente.getItems().add(rs.getString("nombre_cliente"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void seleccionar(){
//        List<Venta> sval = lista.getSelectionModel().getSelectedItems();
//            for(int i=0;i<sval.size();i++){
//                Venta empleadoSeleccionado = sval.get(i);
//                try{
//                    no_empleado_anterior = empleadoSeleccionado.getNo_empleado();
//                    String nombre_empleado_ = empleadoSeleccionado.getNombre_empleado();
//                    String direccion_empleado_ = empleadoSeleccionado.getDireccion_empleado();
//                    Date fecha_contratacion_ = empleadoSeleccionado.getFecha_contratacion();
//                    int nivel_ = empleadoSeleccionado.getNivel();
//                    
//                    nombre_empleado.setText(nombre_empleado_);
//                    direccion_empleado.setText(direccion_empleado_);
//                    fecha_contratacion.setValue(LocalDate.parse(""+fecha_contratacion_));
//                    cmb_nivel.setPromptText(""+nivel_);
//                }
//                catch(Exception e){
//                    System.out.println(""+e);
//                }
//            }

//            String info = "\t\t\tFERNANDA\n"
//                + "\tAv. Antonio Garcia Cubas\n"
//                + "# 533\n"
//                + "\t\t\t\t"+metodo.fecha();
//                TextArea textArea = new TextArea(info);
//                textArea.setPrefSize(400, 100);
//                textArea.setCenterShape(true);
//                textArea.setEditable(false);
    }
}