package paises;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import SQL.SQL;
import SQL.SQL_Actualizar;
import SQL.SQL_Consultar;
import SQL.SQL_Crear;
import SQL.SQL_Eliminar;

public class PaisesController implements Initializable {
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    String cve_anterior;
    
    @FXML
    private TextField nombre, clave;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2;
    @FXML
    private ListView lista_paises;
    @FXML
    private void handleBotonesPaises(ActionEvent event) throws SQLException{
        if(event.getSource() == btn_crear){
            vista_consultar(false);
            vista_actualizar(false);
            vista_eliminar(false);
            vista_crear(true);
            limpiar();
        }else
            if(event.getSource() == btn_leer){
                vista_crear(false);
                vista_actualizar(false);
                vista_eliminar(false);
                vista_consultar(true);
                busqueda();
                limpiar();
            }else
                if(event.getSource() == btn_actualizar){
                    vista_crear(false);
                    vista_consultar(false);
                    vista_eliminar(false);
                    vista_actualizar(true);
                    limpiar();
                    busqueda();
                }else
                    if(event.getSource() == btn_eliminar){
                        vista_crear(false);
                        vista_consultar(false);
                        vista_actualizar(false);
                        vista_eliminar(true);
                        busqueda();
                    }else
                        if(event.getSource() == btn_seleccionar){
                            seleccionar();
                            btn_seleccionar.setVisible(false);
                            btn_cancelar.setVisible(true);
                            btn_aceptar_actualizar.setVisible(true);
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
        nombre.setPromptText("Nombre del Pais");
        clave.setPromptText("Clave del Pais");
        
        
        /*cmb_busqueda.setValue("Seleccione criterio de busqueda");
        
        try {
            llenarCombo();
        } catch (SQLException ex) {
            Logger.getLogger(PaisesController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
    }
    
    private void vista_crear(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        nombre.setVisible(vista);
        clave.setVisible(vista);
        
        btn_aceptar_crear.setVisible(vista);
    }
    
    private void vista_consultar(boolean vista){
        lista_paises.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(false);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        nombre.setVisible(vista);
        clave.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        lista_paises.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(false);
    }
    
    private void vista_eliminar(boolean vista){
        lista_paises.setVisible(vista);
        btn_aceptar_eliminar.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(false);
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
        //Todos los paises":
        lista_paises.getItems().clear();
        lista_paises.setItems(con_consultar.getPaises());
    }
    
    private void seleccionar(){
        List<Pais> sval = lista_paises.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Pais paisSeleccionado = sval.get(i);
                try{
                    String cve_pais=paisSeleccionado.getCve_pais();
                    String nombre_pais=paisSeleccionado.getNombre_pais();
                    clave.setText(cve_pais);
                    nombre.setText(nombre_pais);
                    cve_anterior = cve_pais;
                }
                catch(Exception e){
                    System.out.println(""+e);
                }
            }
    }
    
    private void actualizar(){
        String cve=clave.getText();
        String name=nombre.getText();

        if(con_actualizar.ActualizaPais(cve, name, cve_anterior)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Pais");
            m.setContentText("Pais actualizado correctamente");
            m.setGraphic(new ImageView(new Image("iconos/pais.png")));
            m.show();
        }else{
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Pais");
            m.setContentText("Pais no pudo ser actualizado");
            m.setGraphic(new ImageView(new Image("iconos/pais.png")));
            m.show();
        }
    }
    
    private void limpiar(){
        clave.setText("");
        nombre.setText("");
    }
    
    private void crear(){
        try {
            //Valores recuperados de cada campo
            Pais nuevopais= new Pais(
                clave.getText(),
                nombre.getText());
                
                if(con_crear.insertaPais(nuevopais))
                {
                   Alert msg=new Alert(Alert.AlertType.INFORMATION);
                   msg.setTitle("Registro guardado");
                   msg.setContentText("guardado correctamente");
                   msg.setGraphic(new ImageView(new Image("iconos/pais.png")));
                   msg.show();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
    }
    
    private void eliminar(){
        List<Pais> sval = lista_paises.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Pais PaisSeleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Pais");
                    msg.setContentText("¿Seguro de eliminar al pais seleccionado?\n\n"+PaisSeleccionado.getCve_pais()+" "+PaisSeleccionado.getNombre_pais());
                    msg.setGraphic(new ImageView(new Image("iconos/pais.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK)
                    {
                        if(con_Eliminar.eliminaPais(PaisSeleccionado.getCve_pais())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle(("Pais"));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado el empleado "+ PaisSeleccionado.getCve_pais()+ " "+PaisSeleccionado.getNombre_pais() + " correctamente");
                            ms.setGraphic(new ImageView(new Image("iconos/IconosMenu/empleado.png")));
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }
}