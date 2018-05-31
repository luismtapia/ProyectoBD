package usuarios;

import paises.*;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import SQL.SQL;
import SQL.SQL_Actualizar;
import SQL.SQL_Consultar;
import SQL.SQL_Crear;
import SQL.SQL_Eliminar;
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;

public class UsuariosController implements Initializable {
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int usuario_anterior;
    
    @FXML
    private ComboBox usuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2;
    @FXML
    private ListView lista_usuarios;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
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
                    btn_cancelar.setVisible(false);
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
                            btn_seleccionar.setVisible(true);
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
        usuario.setPromptText("SELECCIONE UN USUARIO");
        contraseña.setPromptText("Contraseña");
        
        llenarcomboboxArea();
    }
    
     private void llenarcomboboxArea(){
        usuario.getItems().clear();
        usuario.setPromptText("SELECCIONE EL USUARIO");
        ResultSet rs = con_consultar.consultarEmpleado();
        try {
            while (rs.next()) {                
                usuario.getItems().add(rs.getString("no_empleado"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
        usuario.setVisible(vista);
        contraseña.setVisible(vista);
        
        btn_aceptar_crear.setVisible(vista);
    }
    
    private void vista_consultar(boolean vista){
        lista_usuarios.setVisible(vista);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        usuario.setVisible(vista);
        contraseña.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        lista_usuarios.setVisible(vista);
        btn_cancelar.setVisible(vista);
        //btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_eliminar(boolean vista){
        lista_usuarios.setVisible(vista);
        
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
        //Todos los paises":
        lista_usuarios.getItems().clear();
        lista_usuarios.setItems(con_consultar.getUsuarios());
    }
    
    private void seleccionar(){
        List<Usuario> sval = lista_usuarios.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Usuario usuarioSeleccionado = sval.get(i);
                try{
                    int user=usuarioSeleccionado.getUsuario();
                    String pass=usuarioSeleccionado.getContraseña();
                    contraseña.setText(pass);
                    usuario.setValue(user);
                    usuario_anterior = user;
                }
                catch(Exception e){
                    System.out.println(""+e);
                }
            }
    }
    
    private void actualizar(){
        String pass=contraseña.getText();
        int name=Integer.parseInt(String.valueOf(usuario.getSelectionModel().getSelectedItem()));

        if(con_actualizar.ActualizaUsuario(name,pass,usuario_anterior)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("");
            m.setContentText(" actualizado correctamente");
            m.setGraphic(new ImageView(new Image("iconos/usuarios.png")));
            m.show();
        }else{
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Pais");
            m.setContentText("Pais no pudo ser actualizado");
            m.setGraphic(new ImageView(new Image("iconos/usuarios.png")));
            m.show();
        }
    }
    
    private void limpiar(){
        contraseña.setText("");
        usuario.setPromptText("SELECCIONE EL USUARIO");
    }
    
    private void crear(){
        int user=0;
        try {
            //Valores recuperados de cada campo
            user=Integer.parseInt(String.valueOf(usuario.getSelectionModel().getSelectedItem()));
            
            Usuario nuevousuario= new Usuario(
                user,
                contraseña.getText());
                
                if(con_crear.insertaUsuario(nuevousuario))
                {
                   Alert msg=new Alert(Alert.AlertType.INFORMATION);
                   msg.setTitle("Registro guardado");
                   msg.setContentText("guardado correctamente");
                   msg.setGraphic(new ImageView(new Image("iconos/usuarios.png")));
                   msg.show();
                }
            } catch (Exception e) {
                System.out.println(e+" \t "+contraseña.getText()+"\t"+user);
            }
    }
    
    private void eliminar(){
        List<Usuario> sval = lista_usuarios.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Usuario usuarioSeleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Usuario");
                    msg.setContentText("¿Seguro de eliminar al usuario seleccionado?\n\n"+usuarioSeleccionado.getUsuario());
                    msg.setGraphic(new ImageView(new Image("iconos/pais.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK)
                    {
                        if(con_Eliminar.eliminaUsuario(usuarioSeleccionado.getUsuario())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle(("Usuario"));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado el usuario"+ usuarioSeleccionado.getUsuario() + " correctamente");
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