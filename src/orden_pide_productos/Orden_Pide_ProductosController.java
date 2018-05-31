package orden_pide_productos;

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
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;

public class Orden_Pide_ProductosController implements Initializable {
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int no_orden_anterior,cve_producto_anterior;
    
    @FXML
    private TextField cantidad;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2, lbl3;
    @FXML
    private ListView lista_;
    @FXML
    private ComboBox cmb_cve_orden,cmb_cve_producto;
    
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
                                    limpiar();
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
        llenarcomboboxOrden();
        llenarcomboboxProducto();
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
        
        cantidad.setVisible(vista);
        cmb_cve_orden.setVisible(vista);
        cmb_cve_producto.setVisible(vista);
        btn_aceptar_crear.setVisible(vista);
    }
    
    private void vista_consultar(boolean vista){
        lista_.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        cantidad.setVisible(vista);
        cmb_cve_orden.setVisible(vista);
        cmb_cve_producto.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        lista_.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_eliminar(boolean vista){
        lista_.setVisible(vista);
        btn_aceptar_eliminar.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(vista);
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
    
    private void llenarcomboboxOrden(){
        cmb_cve_orden.getItems().clear();
        cmb_cve_orden.setPromptText("SELECCIONE ORDEN");
        ResultSet rs = con_consultar.consultarOrdenes();
        try {
            while (rs.next()) {                
                cmb_cve_orden.getItems().add(rs.getInt("no_orden"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     private void llenarcomboboxProducto(){
        cmb_cve_producto.getItems().clear();
        cmb_cve_producto.setPromptText("SELECCIONE PRODUCTO");
        ResultSet rs = con_consultar.consultarProductos();
        try {
            while (rs.next()) {                
                cmb_cve_producto.getItems().add(rs.getInt("cve_producto"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void busqueda() throws SQLException{
        lista_.getItems().clear();
        lista_.setItems(con_consultar.getOrden_Pide_Producto());
    }
    
    private void crear(){
        try {
            
            Orden_Pide_Producto nuevo= new Orden_Pide_Producto(
                Integer.parseInt(cantidad.getText()),
                Integer.parseInt(String.valueOf(cmb_cve_orden.getSelectionModel().getSelectedItem())),
                Integer.parseInt(String.valueOf(cmb_cve_producto.getSelectionModel().getSelectedItem()))
            );
                
                if(con_crear.insertaOrden_Pide_Producto(nuevo))
                {
                   Alert msg=new Alert(Alert.AlertType.INFORMATION);
                   msg.setTitle("Registro guardado");
                   msg.setContentText("guardado correctamente");
                   msg.setGraphic(new ImageView(new Image("iconos/ordenes.png")));
                   msg.show();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
    }
    
    private void seleccionar(){
        List<Orden_Pide_Producto> sval = lista_.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Orden_Pide_Producto Seleccionado = sval.get(i);
                try{
                    no_orden_anterior = Seleccionado.getNo_orden();
                    cve_producto_anterior = Seleccionado.getCve_producto();
                    cantidad.setText(String.valueOf(Seleccionado.getCantidad()));
                    cmb_cve_producto.setValue(Seleccionado.getCve_producto());
                    cmb_cve_orden.setValue(Seleccionado.getNo_orden());
                }
                catch(Exception e){
                    System.out.println(""+e);
                }
            }
    }
    
    private void actualizar(){
        if(con_actualizar.ActualizaOrden_Pide_Productos(
        Integer.parseInt(cantidad.getText()), 
        Integer.parseInt(String.valueOf(cmb_cve_orden.getSelectionModel().getSelectedItem())),
        Integer.parseInt(String.valueOf(cmb_cve_producto.getSelectionModel().getSelectedItem())),
        no_orden_anterior,
        cve_producto_anterior)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Orden_pide");
            m.setContentText("Orden_pide actualizada correctamente");
            m.setGraphic(new ImageView(new Image("iconos/pais.png")));
            m.show();
        }else{
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("");
            m.setContentText(" no pudo ser actualizado");
            m.setGraphic(new ImageView(new Image("iconos/ordenes.png")));
            m.show();
        }
    }
    
    private void limpiar(){
        cantidad.setText("");
        cmb_cve_orden.setPromptText("SELECCIONE ORDEN");
        cmb_cve_producto.setPromptText("SELECCIONE PRODUCTO");
    }
    
    private void eliminar(){
        List<Orden_Pide_Producto> sval = lista_.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Orden_Pide_Producto Seleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Orden");
                    msg.setContentText("¿Seguro de eliminar la orden seleccionada?\n\n"+Seleccionado.getNo_orden());
                    msg.setGraphic(new ImageView(new Image("iconos/ordenes.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK){
                        if(con_Eliminar.eliminaOrden_Pide_Productos(Seleccionado.getNo_orden(),Seleccionado.getCve_producto())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle((""));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado la Orden "+ Seleccionado.getNo_orden() +" correctamente");
                            ms.setGraphic(new ImageView(new Image("iconos/ordenes.png")));
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }
}