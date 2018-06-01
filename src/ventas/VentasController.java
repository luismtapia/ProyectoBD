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
import SQL.SQL_Eliminar;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import productos.Producto;

public class VentasController implements Initializable{
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int no_empleado_anterior;
    
    @FXML
    private TextField cantidad;
    @FXML
    private Button btn_depto_1,btn_depto_2,btn_ver_tallas,btn_agregar_a_ticket,btn_quitar_de_ticket;
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
                }//else
//                    if(event.getSource() == btn_eliminar){
//                        vista_crear(false);
//                        vista_consultar(false);
//                        vista_actualizar(false);
//                        vista_eliminar(true);
//                        busqueda();
//                        btn_cancelar.setVisible(false);
//                        btn_aceptar_actualizar.setVisible(false);
//                    }else
//                        if(event.getSource() == btn_seleccionar){
//                            seleccionar();
//                            btn_seleccionar.setVisible(false);
//                            btn_cancelar.setVisible(true);
//                            btn_aceptar_actualizar.setVisible(true);
//                            seleccionar();
//                            //
//                        }else
//                        if(event.getSource() == btn_cancelar){
//                            btn_seleccionar.setVisible(true);
//                            btn_cancelar.setVisible(false);
//                            btn_aceptar_actualizar.setVisible(false);
//                            limpiar();
//                        }else
//                            if(event.getSource() == btn_aceptar_actualizar){
//                                actualizar();
//                                busqueda();
//                                btn_aceptar_actualizar.setVisible(false);
//                                limpiar();
//                            }else
//                                if(event.getSource() == btn_aceptar_crear){
//                                    crear();
//                                }else
//                                    if(event.getSource() == btn_aceptar_eliminar){
//                                        eliminar();
//                                        busqueda();
//                                    }
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
    
    private void vista_crear(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
//        lbl3.setVisible(vista);
//        lbl4.setVisible(vista);
//        cmb_nivel.setVisible(vista);
//        nombre_empleado.setVisible(vista);
//        direccion_empleado.setVisible(vista);
//        fecha_contratacion.setVisible(vista);
//        btn_aceptar_crear.setVisible(vista);
    }
    
    private void vista_consultar(boolean vista){
//        lista.setVisible(vista);
    }
    
    private void vista_actualizar(boolean vista){
//        lbl1.setVisible(vista);
//        lbl2.setVisible(vista);
//        lbl3.setVisible(vista);
//        lbl4.setVisible(vista);
//        cmb_nivel.setVisible(vista);
//        nombre_empleado.setVisible(vista);
//        direccion_empleado.setVisible(vista);
//        fecha_contratacion.setVisible(vista);
//        btn_seleccionar.setVisible(vista);
//        lista.setVisible(vista);
        //btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_eliminar(boolean vista){
////        lista.setVisible(vista);
////        
////        btn_aceptar_eliminar.setVisible(vista);
    }
    
    private void imagenes(){
        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void busqueda() throws SQLException{
//        lista.getItems().clear();
//        lista.setItems(con_consultar.getEmpleados());
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
    }
    
    private void actualizar() throws SQLException{
//        String nombre_empleado_=nombre_empleado.getText();
//        String direccion_empleado_=direccion_empleado.getText();
//        Date fecha_contratacion_=Date.valueOf(fecha_contratacion.getValue().toString());
//        int nivel_=Integer.parseInt(String.valueOf(cmb_nivel.getSelectionModel().getSelectedItem()));
//
//        if(con_actualizar.ActualizaEmpleado(no_empleado_anterior,nombre_empleado_,direccion_empleado_,fecha_contratacion_,nivel_)){
//            Alert m=new Alert(Alert.AlertType.INFORMATION);
//            m.setTitle("Empleado");
//            m.setContentText("Empleado actualizado correctamente");
//            m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
//            m.show();
//        }else{
//            Alert m=new Alert(Alert.AlertType.INFORMATION);
//            m.setTitle("Empleado");
//            m.setContentText("Empleado no pudo ser actualizado");
//            m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
//            m.show();
//        }
    }
    
    private void limpiar(){
//        direccion_empleado.setText("");
//        nombre_empleado.setText("");
    }
    
    private void crear(){
        //try {
//            int nivel_=Integer.parseInt(String.valueOf(cmb_nivel.getSelectionModel().getSelectedItem()));
//            Date _Date_contratacion=Date.valueOf(fecha_contratacion.getValue().toString());
//            
//            Venta nuevoEmpleado= new Venta(0, nombre_empleado.getText(),  direccion_empleado.getText(), _Date_contratacion, nivel_);
////            if(con_crear.insertaEmpleado(nuevoEmpleado)){
//               Alert msg=new Alert(Alert.AlertType.INFORMATION);
//               msg.setTitle("Registro guardado");
//               msg.setContentText("guardado correctamente");
//               msg.setGraphic(new ImageView(new Image("iconos/usuario.png")));
//               msg.show();
//            }
//        } catch (Exception e) {    
//            Alert m=new Alert(Alert.AlertType.INFORMATION);
//            m.setTitle("ERROR");
//            m.setContentText("Empleado no pudo ser creado"+e);
//            m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
//            m.show();
//            System.out.println(e);
//        }
    }
    
    private void eliminar(){
//        List<Venta> sval = lista.getSelectionModel().getSelectedItems();
//            for(int i=0;i<sval.size();i++){
//                Venta empleadoSeleccionado = sval.get(i);
//                try{
//                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
//                    msg.setTitle(("ELiminar"));
//                    msg.setHeaderText("Capacitacion");
//                    msg.setContentText("¿Seguro de eliminar la capacitacion seleccionada?\n\n"+empleadoSeleccionado.getNombre_empleado());
//                    msg.setGraphic(new ImageView(new Image("iconos/capacitacion.png")));
//                    Optional<ButtonType> res= msg.showAndWait();
//                    if(res.get()==ButtonType.OK)
//                    {
////                        if(con_Eliminar.eliminaEmpleado(empleadoSeleccionado.getNo_empleado())){
////                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
////                            ms.setTitle(("Empleado"));
////                            ms.setHeaderText("Eliminado");
////                            ms.setContentText("Se ha eliminado el empleado"+ empleadoSeleccionado.getNo_empleado()+ " "+empleadoSeleccionado.getNombre_empleado() + " correctamente");
////                            ms.setGraphic(new ImageView(new Image("iconos/usuario.png")));
////                        }
//                    }
//                }
//                catch(Exception e){
//                    System.out.println(e.getMessage());
//                }
            //}
    }
}