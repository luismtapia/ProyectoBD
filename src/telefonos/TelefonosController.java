package telefonos;


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

public class TelefonosController implements Initializable{
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    String telefono_anterior;
    
    @FXML
    private TextField telefono_empleado;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2,lbl3;
    @FXML
    private ComboBox numero_empleado, cve_telefonos;
    @FXML
    private ListView lista_telefonos;
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
        llenarcomboboxClaveTelefono();
        imagenes();
        telefono_empleado.setPromptText("Número Telefónico");
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
        numero_empleado.setVisible(vista);
        cve_telefonos.setVisible(vista);
        telefono_empleado.setVisible(vista);
        btn_aceptar_crear.setVisible(vista);
    }
    
    private void vista_consultar(boolean vista){
        lista_telefonos.setVisible(vista);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        numero_empleado.setVisible(vista);
        cve_telefonos.setVisible(vista);
        telefono_empleado.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        lista_telefonos.setVisible(vista);
        //btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_eliminar(boolean vista){
        lista_telefonos.setVisible(vista);
        
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
        lista_telefonos.getItems().clear();
        lista_telefonos.setItems(con_consultar.getTelefono());
    }
    
    private void llenarcomboboxNumeroEmpleado(){
        numero_empleado.getItems().clear();
        numero_empleado.setPromptText("SELECCIONE EL NUMERO DE EMPLEADO");
        ResultSet rs = con_consultar.consultarEmpleado();
        try {
            while (rs.next()) {                
                numero_empleado.getItems().add(rs.getString("nombre_empleado"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void llenarcomboboxClaveTelefono(){
        cve_telefonos.getItems().clear();
        cve_telefonos.setPromptText("SELECCIONE LA CLAVE DEL TELEFONO");
        ResultSet rs = con_consultar.consultarTipo_Telefono();
        try {
            while (rs.next()) {                
                cve_telefonos.getItems().add(rs.getString("tipo"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void seleccionar(){
        List<Telefono> sval = lista_telefonos.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Telefono telefonoSeleccionado = sval.get(i);
                try{
                      telefono_anterior=telefonoSeleccionado.getNumero();
                      String telefono_empleado_=telefonoSeleccionado.getNumero();
                      int no_empleado=telefonoSeleccionado.getNo_empleado();
                    
                      ResultSet rs=con_consultar.getEmpleadoPorNumero(no_empleado);
                      String _empleado="";
                      while (rs.next()) {                        
                         _empleado=rs.getString("nombre_empleado");
                        }
                       telefono_empleado.setText(telefono_empleado_);
                       numero_empleado.setValue(_empleado);            
                    }
                catch(Exception e){
                    System.out.println(""+e);
                    }
                    String cve_telefono=telefonoSeleccionado.getCve_telefono();
                    
                    ResultSet rs1=con_consultar.getTipo_TelefonoPorClave(cve_telefono);
                    String _telefono="";
                      try{
                      while (rs1.next()) {                        
                         _telefono=rs1.getString("tipo");
                        }
                      }
                catch(Exception e){
                    System.out.println(""+e);  
            }
                    
                    cve_telefonos.setValue(_telefono);
    }
    }
    
    private void actualizar() throws SQLException{
        String telefono_empleado_=telefono_empleado.getText();
      
        String nombre_emp=String.valueOf(numero_empleado.getSelectionModel().getSelectedItem());
        int no_empleado=0;
        ResultSet rs=con_consultar.getEmpleadoPorNombre(nombre_emp);
        while (rs.next()) {                
            no_empleado=rs.getInt("no_empleado");
        }
        String cve_telefono_=String.valueOf(cve_telefonos.getSelectionModel().getSelectedItem());
        String cve_telefono="";
        ResultSet rs1=con_consultar.getTipo_TelefonoPorTipo(cve_telefono_);
        while (rs1.next()) {                
            cve_telefono=rs1.getString("cve_telefono");
        }
        System.err.println(""+no_empleado+"\n"+nombre_emp+"\t"+telefono_empleado_+"\t-"+cve_telefono+"-\t"+cve_telefono_);
        
        if(con_actualizar.ActualizaTelefono(telefono_empleado_,no_empleado,cve_telefono,telefono_anterior)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Número de Teléfono");
            m.setContentText("El número de teléfono ha sido actualizado correctamente");
            m.setGraphic(new ImageView(new Image("iconos/agenda.png")));
            m.show();
        }else{
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Número de Teléfono");
            m.setContentText("El número de Teléfono no pudo ser actualizado");
            m.setGraphic(new ImageView(new Image("iconos/agenda.png")));
            m.show();
        }
    }
    
    private void limpiar(){
        telefono_empleado.setText("");
        numero_empleado.setPromptText("SELECCIONA EL EMPLEADO");
        cve_telefonos.setPromptText("SELECCIONA EL EMPLEADO");
    }
//    
    private void crear(){
        try {
            String numero_=telefono_empleado.getText();
            String numero_empleado_=String.valueOf(numero_empleado.getSelectionModel().getSelectedItem());
            int num_empleado_=-1;
            
            ResultSet rs = con_consultar.getEmpleadoPorNombre(numero_empleado_);
            try {
                while (rs.next()) {                
                    num_empleado_=rs.getInt("no_empleado");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
            
            String cve_tipo_telefono_=String.valueOf(cve_telefonos.getSelectionModel().getSelectedItem());
            String cve_tipo_tel_="";
            
            ResultSet rs1 = con_consultar.getTipo_TelefonoPorTipo(cve_tipo_telefono_);
            try {
                while (rs1.next()) {                
                    cve_tipo_tel_=rs1.getString("cve_telefono");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
            Telefono nuevotelefono= new Telefono(numero_,num_empleado_,cve_tipo_tel_);
                if(con_crear.insertaTelefono(nuevotelefono)){
                   Alert msg=new Alert(Alert.AlertType.INFORMATION);
                   msg.setTitle("Registro guardado");
                   msg.setContentText("guardado correctamente");
                   msg.setGraphic(new ImageView(new Image("iconos/tipo_telefono.png")));
                   msg.show();
                }
            } catch (Exception e) {
                Alert m=new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Número de Teléfono");
                m.setContentText("El número de teléfono no pudo ser creada"+e);
                m.setGraphic(new ImageView(new Image("iconos/tipo_telefono.png")));
                m.show();
                System.out.println(e);
            }
    }
    
    private void eliminar(){
        List<Telefono> sval = lista_telefonos.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Telefono telefonoSeleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Número de Teléfono");
                    msg.setContentText("¿Seguro de eliminar el número de teléfono seleccionado?\n\n"+telefonoSeleccionado.getNumero());
                    msg.setGraphic(new ImageView(new Image("iconos/agenda.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK)
                    {
                        if(con_Eliminar.eliminaTelefono(telefonoSeleccionado.getNumero())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle(("Número de Teléfono"));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado el número de teléfono "+ telefonoSeleccionado.getNo_empleado()+ " "+telefonoSeleccionado.getCve_telefono() + " "+telefonoSeleccionado.getNumero()+ " correctamente");
                            ms.setGraphic(new ImageView(new Image("iconos/IconosMenu/agenda.png")));
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }
}