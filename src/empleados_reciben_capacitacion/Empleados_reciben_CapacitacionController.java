package empleados_reciben_capacitacion;


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
import java.sql.ResultSet;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class Empleados_reciben_CapacitacionController implements Initializable{
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int recibe_capacitacion_anterior, recibe_empleado_anterior;
    
    @FXML
    private TextField evaluacion_capacitacion;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2,lbl3;
    @FXML
    private ComboBox numero_capacitacion, numero_empleado;
    @FXML
    private ListView lista_capacitacionesDempleados;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
        if(event.getSource() == btn_crear){
            vista_consultar(false);
            vista_actualizar(false);
            vista_eliminar(false);
            vista_crear(true);
            btn_aceptar_actualizar.setVisible(false);
            btn_cancelar.setVisible(false);
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
                                btn_cancelar.setVisible(false);
                                btn_seleccionar.setVisible(true);
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
        llenarcomboboxNumeroEmpleado();
        llenarcomboboxNumeroCapacitacion();
        imagenes();
        evaluacion_capacitacion.setPromptText("Calificacion del Empleado");
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
    }
    
    private void vista_crear(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        evaluacion_capacitacion.setVisible(vista);
        numero_empleado.setVisible(vista);
        numero_capacitacion.setVisible(vista);
        btn_aceptar_crear.setVisible(vista);
    }
    
    private void vista_consultar(boolean vista){
        lista_capacitacionesDempleados.setVisible(vista);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        evaluacion_capacitacion.setVisible(vista);
        numero_empleado.setVisible(vista);
        numero_capacitacion.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        lista_capacitacionesDempleados.setVisible(vista);
        //btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_eliminar(boolean vista){
        lista_capacitacionesDempleados.setVisible(vista);
        
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
        lista_capacitacionesDempleados.getItems().clear();
        lista_capacitacionesDempleados.setItems(con_consultar.getEmpleado_Recibe_Capacitacion());
    }
    
    private void llenarcomboboxNumeroEmpleado(){
        numero_empleado.getItems().clear();
        numero_empleado.setPromptText("SELECCIONE EL EMPLEADO");
        ResultSet rs = con_consultar.consultarEmpleado();
        try {
            while (rs.next()) {                
                numero_empleado.getItems().add(rs.getString("nombre_empleado"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void llenarcomboboxNumeroCapacitacion(){
        numero_capacitacion.getItems().clear();
        numero_capacitacion.setPromptText("SELECCIONE LA CAPACITACIÓN");
        ResultSet rs = con_consultar.consultarCapacitacion();
        try {
            while (rs.next()) {                
                numero_capacitacion.getItems().add(rs.getString("nombre_capacitacion"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void seleccionar(){
        List<Empleado_Recibe_Capacitacion> sval = lista_capacitacionesDempleados.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Empleado_Recibe_Capacitacion Empleado_Recibe_CapacitacionSeleccionado = sval.get(i);
                try{
                       int evaluacion_=Empleado_Recibe_CapacitacionSeleccionado.getEvaluacion();
                       int no_cap=Empleado_Recibe_CapacitacionSeleccionado.getNo_capacitacion();
                       int no_empleado=Empleado_Recibe_CapacitacionSeleccionado.getNo_empleado();
                       recibe_capacitacion_anterior=Empleado_Recibe_CapacitacionSeleccionado.getNo_capacitacion();
                       recibe_empleado_anterior=Empleado_Recibe_CapacitacionSeleccionado.getNo_empleado();
                    
                      ResultSet rs=con_consultar.getCapacitacionPorNo_Capacitacion(no_cap);
                      String _capacitacion="";
                      
                      while (rs.next()) {                        
                         _capacitacion=rs.getString("nombre_capacitacion");
                        }
         
                      ResultSet rs1=con_consultar.getEmpleadoPorNumero(no_empleado);
                      String _empleado="";
                      
                       while (rs1.next()) {                        
                         _empleado=rs1.getString("nombre_empleado");
                        }
                      
                       evaluacion_capacitacion.setText(""+evaluacion_);
                       numero_capacitacion.setValue(_capacitacion);
                       numero_empleado.setValue(_empleado);  
                    }
                catch(Exception e){
                    System.out.println(""+e);  
            }
        }
    }
    
    private void actualizar() throws SQLException{
        int evaluacion_=Integer.parseInt(evaluacion_capacitacion.getText());
        String capacitacion=String.valueOf(numero_capacitacion.getSelectionModel().getSelectedItem());
        int no_cap=0;
        
        ResultSet rs=con_consultar.getCapacitacionPorNombre(capacitacion);
        while (rs.next()) {                
            no_cap=rs.getInt("no_capacitacion");
        }
        String empleado=String.valueOf(numero_empleado.getSelectionModel().getSelectedItem());
        int no_emp=0;
        ResultSet rs1=con_consultar.getEmpleadoPorNombre(empleado);
        while (rs1.next()) {                
            no_emp=rs1.getInt("no_empleado");
        }
        System.err.println(""+evaluacion_+"\t"+capacitacion+"\t"+no_cap+"\t-"+empleado+"-\t"+no_emp);
        
        if(con_actualizar.ActualizaEmpleado_Recibe_Capacitacion(evaluacion_,no_cap,no_emp,recibe_empleado_anterior,recibe_capacitacion_anterior)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Calificación");
            m.setContentText("La calificación ha sido actualizada correctamente");
            m.setGraphic(new ImageView(new Image("iconos/empleado_recibe_capacitacion.png")));
            m.show();
        }else{
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Calificación");
            m.setContentText("La calificación no pudo ser actualizada");
            m.setGraphic(new ImageView(new Image("iconos/empleado_recibe_capacitacion.png")));
            m.show();
        }
    }
    
    private void limpiar(){
        evaluacion_capacitacion.setText("");
        numero_capacitacion.setPromptText("SELECCIONA LA CAPACITACION");
        numero_empleado.setPromptText("SELECCIONA EL EMPLEADO");
       
    }
//    
    private void crear(){
        String numero_empleado_="",numero_capacitacion_="";
        try {
            int evaluacion_=Integer.parseInt(evaluacion_capacitacion.getText());
            numero_empleado_=String.valueOf(numero_empleado.getSelectionModel().getSelectedItem());
            int num_empleado_=-1;
            
            ResultSet rs = con_consultar.getEmpleadoPorNombre(numero_empleado_);
            try {
                while (rs.next()) {                
                    num_empleado_=rs.getInt("no_empleado");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
            
            
            numero_capacitacion_=String.valueOf(numero_capacitacion.getSelectionModel().getSelectedItem());
            int num_capacitacion_=-1;
            
            ResultSet rs1 = con_consultar.getCapacitacionPorNombre(numero_capacitacion_);
            try {
                while (rs1.next()) {                
                    num_capacitacion_=rs1.getInt("no_capacitacion");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
      
            Empleado_Recibe_Capacitacion nuevoempleadorecibecapacitacion= new Empleado_Recibe_Capacitacion(evaluacion_,num_capacitacion_,num_empleado_);
                if(con_crear.insertaEmpleado_Recibe_Capacitacion(nuevoempleadorecibecapacitacion)){
                   Alert msg=new Alert(Alert.AlertType.INFORMATION);
                   msg.setTitle("Registro guardado");
                   msg.setContentText("guardado correctamente");
                   msg.setGraphic(new ImageView(new Image("iconos/empleado_recibe_capacitacion.png")));
                   msg.show();
                }
            } catch (Exception e) {
                Alert m=new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Calificación");
                m.setContentText("La calificación del empleado no pudo ser creada"+e);
                m.setGraphic(new ImageView(new Image("iconos/empleado_recibe_capacitacion.png")));
                m.show();
                System.out.println(e);
            }
    }
    
    private void eliminar(){
        List<Empleado_Recibe_Capacitacion> sval = lista_capacitacionesDempleados.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Empleado_Recibe_Capacitacion Empleado_Recibe_CapacitacionSeleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Calificación");
                    msg.setContentText("¿Seguro de eliminar la calificación seleccionada?\n\n"+Empleado_Recibe_CapacitacionSeleccionado.getEvaluacion());
                    msg.setGraphic(new ImageView(new Image("iconos/empleado_recibe_capacitacion.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK)
                    {
                        if(con_Eliminar.eliminaEmpleado_Recibe_Capacitacion(Empleado_Recibe_CapacitacionSeleccionado.getEvaluacion())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle(("Calificación"));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado la calificación del empleado "+ Empleado_Recibe_CapacitacionSeleccionado.getEvaluacion()+ " "+Empleado_Recibe_CapacitacionSeleccionado.getNo_capacitacion()+ " "+Empleado_Recibe_CapacitacionSeleccionado.getNo_empleado()+ " correctamente");
                            ms.setGraphic(new ImageView(new Image("iconos/IconosMenu/empleado_recibe_capacitacion.png")));
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }
}