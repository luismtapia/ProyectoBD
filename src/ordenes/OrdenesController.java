package ordenes;

import clientes.*;
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
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class OrdenesController implements Initializable {
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int no_orden_anterior;
    
    @FXML
    private TextField subtotal, iva, total;
    @FXML
    private DatePicker fecha_orden, fecha_entrega;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_calcular,btn_seleccionar,btn_cancelar;
    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
    @FXML
    private ListView lista_;
    @FXML
    private ComboBox cmb_cve_proveedor;
    
    @FXML
    private void handleBotonesPaises(ActionEvent event) throws SQLException{
        if(event.getSource() == btn_crear){
            vista_consultar(false);
            vista_actualizar(false);
            vista_eliminar(false);
            vista_crear(true);
            btn_calcular.setVisible(true);
            limpiar();
        }else
            if(event.getSource() == btn_leer){
                vista_crear(false);
                vista_actualizar(false);
                vista_eliminar(false);
                vista_consultar(true);
                btn_calcular.setVisible(false);
                busqueda();
                limpiar();
            }else
                if(event.getSource() == btn_actualizar){
                    vista_crear(false);
                    vista_consultar(false);
                    vista_eliminar(false);
                    vista_actualizar(true);
                    btn_calcular.setVisible(true);
                    limpiar();
                    busqueda();
                }else
                    if(event.getSource() == btn_eliminar){
                        vista_crear(false);
                        vista_consultar(false);
                        vista_actualizar(false);
                        vista_eliminar(true);
                        btn_calcular.setVisible(false);
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
                                    }else
                                        if(event.getSource() == btn_calcular){
                                            calcular();
                                            btn_aceptar_crear.setVisible(true);
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
        llenarcomboboxProveedor();
        fecha_orden.setPromptText("SELECCIONE ");
        fecha_entrega.setPromptText("SELECCIONE");
        iva.setEditable(false);
        total.setEditable(false);
        iva.setPromptText("CALCULE EL IVA");
        btn_calcular.setVisible(false);
    }
    
    private void color(Color color){
        btn_crear.setTextFill(color);
        btn_leer.setTextFill(color);
        btn_actualizar.setTextFill(color);
        btn_eliminar.setTextFill(color);
        
        btn_seleccionar.setTextFill(color);
        btn_cancelar.setTextFill(color);
        
        btn_calcular.setTextFill(color);
        btn_aceptar_crear.setTextFill(color);
        btn_aceptar_actualizar.setTextFill(color);
        btn_aceptar_eliminar.setTextFill(color);
        
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
        lbl5.setTextFill(color);
        lbl6.setTextFill(color);
    }
    
    private void vista_crear(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        lbl4.setVisible(vista);
        lbl5.setVisible(vista);
        lbl6.setVisible(vista);
        fecha_orden.setVisible(vista);
        fecha_entrega.setVisible(vista);
        iva.setVisible(vista);
        subtotal.setVisible(vista);
        total.setVisible(vista);
        cmb_cve_proveedor.setVisible(vista);
        btn_aceptar_crear.setVisible(false);
    }
    
    private void vista_consultar(boolean vista){
        lista_.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_aceptar_actualizar.setVisible(false);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        lbl4.setVisible(vista);
        lbl5.setVisible(vista);
        lbl6.setVisible(vista);
        fecha_orden.setVisible(vista);
        iva.setVisible(vista);
        subtotal.setVisible(vista);
        fecha_entrega.setVisible(vista);
        total.setVisible(vista);
        cmb_cve_proveedor.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        lista_.setVisible(vista);
        btn_cancelar.setVisible(false);
        btn_calcular.setVisible(vista);
        btn_aceptar_actualizar.setVisible(false);
    }
    
    private void vista_eliminar(boolean vista){
        lista_.setVisible(vista);
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
    
    private void llenarcomboboxProveedor(){
        cmb_cve_proveedor.getItems().clear();
        cmb_cve_proveedor.setPromptText("SELECCIONE PROVEEDOR");
        ResultSet rs = con_consultar.consultarProveedores();
        try {
            while (rs.next()) {                
                cmb_cve_proveedor.getItems().add(rs.getInt("cve_proveedor"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void busqueda() throws SQLException{
        //Todos los paises":
        lista_.getItems().clear();
        lista_.setItems(con_consultar.getOrdenes());
    }
    
    private void calcular(){
        double IVA = Double.parseDouble(subtotal.getText());
        IVA = IVA*0.16;
        double TOTAL = IVA + Double.parseDouble(subtotal.getText());
        iva.setText(""+IVA);
        total.setText(""+TOTAL);
    }
    
    private void crear(){
        try {
            double IVA = Double.parseDouble(subtotal.getText());
            IVA = IVA*0.16;
            double TOTAL = IVA + Double.parseDouble(subtotal.getText());
            
            Orden nuevo= new Orden(
                0,
                Date.valueOf(fecha_orden.getValue().toString()),
                Date.valueOf(fecha_entrega.getValue().toString()),
                Double.parseDouble(subtotal.getText()),
                IVA,
                TOTAL,
                Integer.parseInt(String.valueOf(cmb_cve_proveedor.getSelectionModel().getSelectedItem()))
            );
                
                if(con_crear.insertaOrden(nuevo))
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
        List<Orden> sval = lista_.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Orden Seleccionado = sval.get(i);
                try{
                    no_orden_anterior = Seleccionado.getNo_orden();
                    fecha_orden.setValue(LocalDate.parse(String.valueOf(Seleccionado.getFecha_orden())));
                    fecha_entrega.setValue(LocalDate.parse(String.valueOf(Seleccionado.getFecha_entrega())));
                    subtotal.setText(String.valueOf(Seleccionado.getSubtotal()));
                    iva.setText(String.valueOf(Seleccionado.getIVA()));
                    total.setText(String.valueOf(Seleccionado.getPago_total()));
                    cmb_cve_proveedor.setValue(Seleccionado.getCve_proveedor());
                }
                catch(Exception e){
                    System.out.println(""+e);
                }
            }
    }
    
    private void actualizar(){
        if(con_actualizar.ActualizaOrden(
        Date.valueOf(LocalDate.parse(fecha_orden.getValue().toString())), 
        Date.valueOf(LocalDate.parse(fecha_entrega.getValue().toString())),
        Double.parseDouble(subtotal.getText()),
        Double.parseDouble(iva.getText()),
        Double.parseDouble(total.getText()),
        Integer.parseInt(String.valueOf(cmb_cve_proveedor.getSelectionModel().getSelectedItem())),
        no_orden_anterior)){
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Orden");
            m.setContentText("Orden actualizada correctamente");
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
        iva.setText("");
        fecha_orden.setPromptText("SELECCIONE FECHA");
        fecha_entrega.setPromptText("SELECIONE FECHA");
        subtotal.setText("");
        total.setText("");
        cmb_cve_proveedor.setPromptText("SELECCIONE UN PROVEEDOR");
    }
    
    private void eliminar(){
        List<Orden> sval = lista_.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Orden Seleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Orden");
                    msg.setContentText("¿Seguro de eliminar la orden seleccionada?\n\n"+Seleccionado.getNo_orden());
                    msg.setGraphic(new ImageView(new Image("iconos/ordenes.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK){
                        if(con_Eliminar.eliminaOrden(Seleccionado.getNo_orden())){
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