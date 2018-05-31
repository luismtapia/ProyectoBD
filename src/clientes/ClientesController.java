package clientes;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import SQL.SQL_Actualizar;
import SQL.SQL_Consultar;
import SQL.SQL_Crear;
import SQL.SQL_Eliminar;

public class ClientesController implements Initializable {
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int no_cliente_anterior;
    
    @FXML
    private TextField nombre, direccion, rfc, correo;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2, lbl3,lbl4;
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
                                btn_seleccionar.setVisible(true);
                                btn_cancelar.setVisible(false);
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
        nombre.setPromptText("Nombre del Cliente");
        direccion.setPromptText("Domicilio del cliente");
        correo.setPromptText("correo electronico");
        rfc.setPromptText("Campo requerido");
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
        nombre.setVisible(vista);
        direccion.setVisible(vista);
        correo.setVisible(vista);
        rfc.setVisible(vista);
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
        lbl3.setVisible(vista);
        lbl4.setVisible(vista);
        nombre.setVisible(vista);
        correo.setVisible(vista);
        rfc.setVisible(vista);
        direccion.setVisible(vista);
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
        lista_paises.setItems(con_consultar.getClientes());
    }
    
    private void seleccionar(){
        List<Cliente> sval = lista_paises.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Cliente clienteSeleccionado = sval.get(i);
                try{
                    int no_cliente=clienteSeleccionado.getNo_cliente();
                    String nombre_=clienteSeleccionado.getNombre_cliente();
                    String domicilio_=clienteSeleccionado.getDomicilio();
                    String RFC_=clienteSeleccionado.getRFC();
                    String correo_=clienteSeleccionado.getCorreo();
                    
                    no_cliente_anterior = no_cliente;
                    nombre.setText(nombre_);
                    direccion.setText(domicilio_);
                    rfc.setText(RFC_);
                    correo.setText(correo_);
                    
                }
                catch(Exception e){
                    System.out.println(""+e);
                }
            }
    }
    
    private void actualizar(){
        String _nombre=nombre.getText();
        String _direccion=direccion.getText();
        String _rfc=rfc.getText();
        String _correo=correo.getText();

        if(con_actualizar.ActualizaCliente(no_cliente_anterior, _nombre, _direccion, _rfc, _correo)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Cliente");
            m.setContentText("Cliente actualizado correctamente");
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
        correo.setText("");
        nombre.setText("");
        direccion.setText("");
        rfc.setText("");
    }
    
    private void crear(){
        try {
            Cliente nuevocliente= new Cliente(
                0,
                nombre.getText(),
                direccion.getText(),
                rfc.getText(),
                correo.getText()
            );
                
                if(con_crear.insertaCliente(nuevocliente))
                {
                   Alert msg=new Alert(Alert.AlertType.INFORMATION);
                   msg.setTitle("Registro guardado");
                   msg.setContentText("guardado correctamente");
                   msg.setGraphic(new ImageView(new Image("iconos/cliente.png")));
                   msg.show();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
    }
    
    private void eliminar(){
        List<Cliente> sval = lista_paises.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Cliente clienteSeleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Cliente");
                    msg.setContentText("¿Seguro de eliminar al cliente seleccionado?\n\n"+clienteSeleccionado.getNo_cliente());
                    msg.setGraphic(new ImageView(new Image("iconos/pais.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK){
                        if(con_Eliminar.eliminaCliente(clienteSeleccionado.getNo_cliente())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle(("Cliente"));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado el cliente "+ clienteSeleccionado.getNombre_cliente() +" correctamente");
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