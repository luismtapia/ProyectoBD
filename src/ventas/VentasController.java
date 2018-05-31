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

public class VentasController implements Initializable{
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int no_empleado_anterior;
    
    @FXML
    private TextField nombre_empleado,direccion_empleado;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2,lbl3,lbl4;
    @FXML
    private ComboBox cmb_nivel;
    @FXML
    private DatePicker fecha_contratacion;
    @FXML
    private ListView lista;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
        if(event.getSource() == btn_crear){
            vista_consultar(false);
            vista_actualizar(false);
            vista_eliminar(false);
            vista_crear(true);
            btn_aceptar_actualizar.setVisible(false);
            btn_cancelar.setVisible(false);
            llenarcomboboxNivel();
            limpiar();
        }else
            if(event.getSource() == btn_leer){
                vista_crear(false);
                vista_actualizar(false);
                vista_eliminar(false);
                vista_consultar(true);
                btn_cancelar.setVisible(false);
                btn_aceptar_actualizar.setVisible(false);
                busqueda();
                limpiar();
            }else
                if(event.getSource() == btn_actualizar){
                    vista_crear(false);
                    vista_consultar(false);
                    vista_eliminar(false);
                    vista_actualizar(true);
                    btn_cancelar.setVisible(false);
                    btn_aceptar_actualizar.setVisible(false);
                    limpiar();
                    llenarcomboboxNivel();
                    busqueda();
                }else
                    if(event.getSource() == btn_eliminar){
                        vista_crear(false);
                        vista_consultar(false);
                        vista_actualizar(false);
                        vista_eliminar(true);
                        busqueda();
                        btn_cancelar.setVisible(false);
                        btn_aceptar_actualizar.setVisible(false);
                    }else
                        if(event.getSource() == btn_seleccionar){
                            seleccionar();
                            btn_seleccionar.setVisible(false);
                            btn_cancelar.setVisible(true);
                            btn_aceptar_actualizar.setVisible(true);
                            seleccionar();
                            //
                        }else
                        if(event.getSource() == btn_cancelar){
                            btn_seleccionar.setVisible(true);
                            btn_cancelar.setVisible(false);
                            btn_aceptar_actualizar.setVisible(false);
                            limpiar();
                        }else
                            if(event.getSource() == btn_aceptar_actualizar){
                                actualizar();
                                busqueda();
                                btn_aceptar_actualizar.setVisible(false);
                                limpiar();
                            }else
                                if(event.getSource() == btn_aceptar_crear){
                                    crear();
                                }else
                                    if(event.getSource() == btn_aceptar_eliminar){
                                        eliminar();
                                        busqueda();
                                    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        color(Color.web("#D4AF37"));
        vista_crear(false);
        vista_consultar(false);
        vista_actualizar(false);
        vista_eliminar(false);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(false);
        imagenes();
        nombre_empleado.setPromptText("Nombre del empleado");
        direccion_empleado.setPromptText("Direccion del Empleado");
    }
    
    private void color(Color color){
        btn_crear.setTextFill(color);
        btn_leer.setTextFill(color);
        btn_actualizar.setTextFill(color);
        btn_eliminar.setTextFill(color);
        
        btn_seleccionar.setTextFill(color);
        btn_cancelar.setTextFill(color);
        
        btn_aceptar_crear.setTextFill(color);
        btn_aceptar_actualizar.setTextFill(color);
        btn_aceptar_eliminar.setTextFill(color);
        
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
    }
    
    private void vista_crear(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        lbl4.setVisible(vista);
        cmb_nivel.setVisible(vista);
        nombre_empleado.setVisible(vista);
        direccion_empleado.setVisible(vista);
        fecha_contratacion.setVisible(vista);
        btn_aceptar_crear.setVisible(vista);
    }
    
    private void vista_consultar(boolean vista){
        lista.setVisible(vista);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        lbl4.setVisible(vista);
        cmb_nivel.setVisible(vista);
        nombre_empleado.setVisible(vista);
        direccion_empleado.setVisible(vista);
        fecha_contratacion.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        lista.setVisible(vista);
        //btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_eliminar(boolean vista){
        lista.setVisible(vista);
        
        btn_aceptar_eliminar.setVisible(vista);
    }
    
    private void imagenes(){
        btn_crear.setGraphic(new ImageView(new Image("iconos/crear.png")));
        btn_crear.setContentDisplay(ContentDisplay.BOTTOM);
        btn_leer.setGraphic(new ImageView(new Image("iconos/consultar.png")));
        btn_leer.setContentDisplay(ContentDisplay.BOTTOM);
        btn_actualizar.setGraphic(new ImageView(new Image("iconos/actualizar.png")));
        btn_actualizar.setContentDisplay(ContentDisplay.BOTTOM);
        btn_eliminar.setGraphic(new ImageView(new Image("iconos/eliminar.png")));
        btn_eliminar.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void busqueda() throws SQLException{
        lista.getItems().clear();
        lista.setItems(con_consultar.getEmpleados());
    }
    
    private void llenarcomboboxNivel(){
        cmb_nivel.getItems().clear();
        cmb_nivel.setPromptText("SELECCIONE EL NIVEL");
        ResultSet rs = con_consultar.consultarNivel();
        try {
            while (rs.next()) {                
                cmb_nivel.getItems().add(rs.getInt("nivel"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void seleccionar(){
        List<Venta> sval = lista.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Venta empleadoSeleccionado = sval.get(i);
                try{
                    no_empleado_anterior = empleadoSeleccionado.getNo_empleado();
                    String nombre_empleado_ = empleadoSeleccionado.getNombre_empleado();
                    String direccion_empleado_ = empleadoSeleccionado.getDireccion_empleado();
                    Date fecha_contratacion_ = empleadoSeleccionado.getFecha_contratacion();
                    int nivel_ = empleadoSeleccionado.getNivel();
                    
                    nombre_empleado.setText(nombre_empleado_);
                    direccion_empleado.setText(direccion_empleado_);
                    fecha_contratacion.setValue(LocalDate.parse(""+fecha_contratacion_));
                    cmb_nivel.setPromptText(""+nivel_);
                }
                catch(Exception e){
                    System.out.println(""+e);
                }
            }
    }
    
    private void actualizar() throws SQLException{
        String nombre_empleado_=nombre_empleado.getText();
        String direccion_empleado_=direccion_empleado.getText();
        Date fecha_contratacion_=Date.valueOf(fecha_contratacion.getValue().toString());
        int nivel_=Integer.parseInt(String.valueOf(cmb_nivel.getSelectionModel().getSelectedItem()));

        if(con_actualizar.ActualizaEmpleado(no_empleado_anterior,nombre_empleado_,direccion_empleado_,fecha_contratacion_,nivel_)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Empleado");
            m.setContentText("Empleado actualizado correctamente");
            m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
            m.show();
        }else{
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Empleado");
            m.setContentText("Empleado no pudo ser actualizado");
            m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
            m.show();
        }
    }
    
    private void limpiar(){
        direccion_empleado.setText("");
        nombre_empleado.setText("");
    }
    
    private void crear(){
        try {
            int nivel_=Integer.parseInt(String.valueOf(cmb_nivel.getSelectionModel().getSelectedItem()));
            Date _Date_contratacion=Date.valueOf(fecha_contratacion.getValue().toString());
            
            Venta nuevoEmpleado= new Venta(0, nombre_empleado.getText(),  direccion_empleado.getText(), _Date_contratacion, nivel_);
//            if(con_crear.insertaEmpleado(nuevoEmpleado)){
//               Alert msg=new Alert(Alert.AlertType.INFORMATION);
//               msg.setTitle("Registro guardado");
//               msg.setContentText("guardado correctamente");
//               msg.setGraphic(new ImageView(new Image("iconos/usuario.png")));
//               msg.show();
//            }
        } catch (Exception e) {    
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("ERROR");
            m.setContentText("Empleado no pudo ser creado"+e);
            m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
            m.show();
            System.out.println(e);
        }
    }
    
    private void eliminar(){
        List<Venta> sval = lista.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Venta empleadoSeleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Capacitacion");
                    msg.setContentText("¿Seguro de eliminar la capacitacion seleccionada?\n\n"+empleadoSeleccionado.getNombre_empleado());
                    msg.setGraphic(new ImageView(new Image("iconos/capacitacion.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK)
                    {
                        if(con_Eliminar.eliminaEmpleado(empleadoSeleccionado.getNo_empleado())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle(("Empleado"));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado el empleado"+ empleadoSeleccionado.getNo_empleado()+ " "+empleadoSeleccionado.getNombre_empleado() + " correctamente");
                            ms.setGraphic(new ImageView(new Image("iconos/usuario.png")));
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }
}