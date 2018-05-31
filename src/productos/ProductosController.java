
package productos;

import SQL.SQL_Actualizar;
import SQL.SQL_Consultar;
import SQL.SQL_Crear;
import SQL.SQL_Eliminar;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import paises.Pais;

public class ProductosController implements Initializable{
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    int cve_producto_anterior;
    
    @FXML
    private Label titulo,lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11;
    @FXML
    private Button btn_crear,btn_leer,btn_eliminar,btn_actualizar,btn_aceptar_crear,btn_aceptar_actualizar,btn_aceptar_eliminar,btn_seleccionar,btn_cancelar;
    @FXML
    private TextField descripcion,codigo,marca,modelo,precio_compra,precio_venta,cantidad;
    @FXML
    private ComboBox departamento,talla,proveedor,pais_origen;
    @FXML
    private ListView lista_;
    @FXML
    public void handleBotonesProducto(ActionEvent event){
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
    public void initialize(URL url, ResourceBundle rb) {//constructor
       
        color(Color.web("#D4AF37"));
        vista_crear(false);
        vista_actualizar(false);
        vista_consultar(false);
        vista_eliminar(false);
        btn_cancelar.setVisible(false);
        lbl6.setVisible(false);
        talla.setVisible(false);
        lbl9.setVisible(false);
        cantidad.setVisible(false);
        imagenes();
        
        llenarcomboboxDepartamentos();
        llenarcomboboxTallas();
        llenarcomboboxProveedores();
        llenarcomboboxPaises();
    }
    
    private void llenarcomboboxDepartamentos(){
        departamento.getItems().clear();
        departamento.setPromptText("SELECCIONE UN DEPARTAMENTO");
        ResultSet rs = con_consultar.consultarDepartamentos();
        try {
            while (rs.next()) {
                departamento.getItems().add(rs.getString("nombre_depto"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void llenarcomboboxPaises(){
        pais_origen.getItems().clear();
        pais_origen.setPromptText("SELECCIONE UN PAIS");
        ResultSet rs = con_consultar.consultarPaises();
        try {
            while (rs.next()) {
                pais_origen.getItems().add(rs.getString("nombre_pais"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void llenarcomboboxTallas(){
//        talla.getItems().clear();
//        talla.setPromptText("SELECCIONE UNA TALLA");
//        ResultSet rs = con_consultar.consultarTallas();
//        try {
//            while (rs.next()) {
//                talla.getItems().add(rs.getString("numero"));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }
    
    private void llenarcomboboxProveedores(){
        proveedor.getItems().clear();
        proveedor.setPromptText("SELECCIONE UN PROVEEDOR");
        ResultSet rs = con_consultar.consultarProveedores();
        try {
            while (rs.next()) {
                proveedor.getItems().add(rs.getString("nombre_proveedor"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void color(Color color){
        titulo.setTextFill(Color.WHITE);
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
        lbl5.setTextFill(color);
//        lbl6.setTextFill(color);
        lbl7.setTextFill(color);
        lbl8.setTextFill(color);
//        lbl9.setTextFill(color);
        lbl10.setTextFill(color);
        lbl11.setTextFill(color);

        btn_crear.setTextFill(color);
        btn_leer.setTextFill(color);
        btn_actualizar.setTextFill(color);
        btn_eliminar.setTextFill(color);
        btn_aceptar_crear.setTextFill(color);
        btn_aceptar_actualizar.setTextFill(color);
        btn_aceptar_eliminar.setTextFill(color);
        btn_cancelar.setTextFill(color);
        btn_seleccionar.setTextFill(color);
     }   
   
    private void vista_crear(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        lbl4.setVisible(vista);
        lbl5.setVisible(vista);
//        lbl6.setVisible(vista);
        lbl7.setVisible(vista);
        lbl8.setVisible(vista);
//        lbl9.setVisible(vista);
        lbl10.setVisible(vista);
        lbl11.setVisible(vista);
        departamento.setVisible(vista);
        descripcion.setVisible(vista);
        codigo.setVisible(vista);
        marca.setVisible(vista);
        //cantidad.setVisible(vista);
        proveedor.setVisible(vista);
        pais_origen.setVisible(vista);
//        talla.setVisible(vista);
        modelo.setVisible(vista);
        precio_compra.setVisible(vista);
        precio_venta.setVisible(vista);
        btn_aceptar_crear.setVisible(vista);
        btn_cancelar.setVisible(vista);
     } 
    
    private void vista_consultar(boolean vista){
        lista_.setVisible(vista);
    }
    
    private void vista_actualizar(boolean vista){
        lbl1.setVisible(vista);
        lbl2.setVisible(vista);
        lbl3.setVisible(vista);
        lbl4.setVisible(vista);
        lbl5.setVisible(vista);
//        lbl6.setVisible(vista);
        lbl7.setVisible(vista);
        lbl8.setVisible(vista);
//        lbl9.setVisible(vista);
        lbl10.setVisible(vista);
        lbl11.setVisible(vista);
        departamento.setVisible(vista);
        descripcion.setVisible(vista);
        codigo.setVisible(vista);
        marca.setVisible(vista);
//        cantidad.setVisible(vista);
        proveedor.setVisible(vista);
        pais_origen.setVisible(vista);
//        talla.setVisible(vista);
        modelo.setVisible(vista);
        precio_compra.setVisible(vista);
        precio_venta.setVisible(vista);
        lista_.setVisible(vista);
        btn_seleccionar.setVisible(vista);
        btn_aceptar_actualizar.setVisible(vista);
    }
    
    private void vista_eliminar (boolean vista){
        lista_.setVisible(vista);
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
    
    private void crear(){
        try {
            String _nombre_depto=String.valueOf(departamento.getSelectionModel().getSelectedItem()); //int
            String _nombre_pais=String.valueOf(pais_origen.getSelectionModel().getSelectedItem());
            String _nombre_proveedor=String.valueOf(proveedor.getSelectionModel().getSelectedItem());     //int
            
            ResultSet rs=con_consultar.getDepartamentoPorNombre(_nombre_depto);
            int _cve_depto=-1;
            while (rs.next()) {                    
                _cve_depto=rs.getInt("cve_depto");
            }
            
            ResultSet rs1=con_consultar.getPaisPorNombre(_nombre_pais);
            String _cve_pais="";
            while (rs1.next()) {                    
                _cve_pais=rs1.getString("cve_pais");
            }
            
            System.out.println("cve: "+_cve_pais);
            System.out.println("nombre "+_nombre_pais);
            
            ResultSet rs2=con_consultar.getProveedorPorNombre(_nombre_proveedor);
            int _cve_proveedor=-1;
            while (rs2.next()) {                    
                _cve_proveedor=rs2.getInt("cve_proveedor");
            }
            
            Producto nuevoProducto= new Producto(
                Integer.parseInt(codigo.getText()),
                descripcion.getText(),
                marca.getText(),
                modelo.getText(),
                Double.parseDouble(precio_venta.getText()),
                Double.parseDouble(precio_compra.getText()),
                _cve_depto,
                _cve_pais,
                _cve_proveedor
            );

            if(con_crear.insertaProducto(nuevoProducto))
            {
               Alert msg=new Alert(Alert.AlertType.INFORMATION);
               msg.setTitle("Registro guardado");
               msg.setContentText("guardado correctamente");
               msg.setGraphic(new ImageView(new Image("iconos/productos.png")));
               msg.show();
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        }
    }
    
    private void busqueda(){
        lista_.getItems().clear();
        lista_.setItems(con_consultar.getProductos());
    }
    
    private void seleccionar(){
        List<Producto> sval = lista_.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Producto Seleccion = sval.get(i);
                try{
                    descripcion.setText(Seleccion.getDescripcion());
                    marca.setText(Seleccion.getMarca());
                    modelo.setText(Seleccion.getModelo());
                    double _precio_venta= Double.parseDouble(String.valueOf(Seleccion.getPrecio_venta()));
                    precio_venta.setText(""+_precio_venta);
                    double _precio_compra= Double.parseDouble(String.valueOf(Seleccion.getPrecio_venta()));
                    precio_compra.setText(""+_precio_compra);
                    codigo.setText(""+Seleccion.getCve_producto());
                    
                    String _nombre_proveedor = "";
                    String _nombre_pais = "";
                    String _nombre_depto = "";
                    ResultSet rs=con_consultar.getProveedorPorClave(Seleccion.getCve_depto());
                    ResultSet rs1=con_consultar.getPaisPorClave(Seleccion.getCve_pais());
                    ResultSet rs2=con_consultar.getDepartamentoPorClave(Seleccion.getCve_proveedor());
                    System.out.println("prov: "+Seleccion.getCve_proveedor()+"   pai: "+Seleccion.getCve_pais()+"    depto: "+Seleccion.getCve_depto());
                    while (rs.next()) {
                        _nombre_proveedor = rs.getString("nombre_proveedor");
                    }
                    while (rs1.next()) {
                        _nombre_pais= rs1.getString("nombre_pais");
                        System.err.println(""+_nombre_pais);
                    }
                    while (rs2.next()) {
                        _nombre_depto = rs2.getString("nombre_depto");
                    }
            
                    System.out.println("prov: "+_nombre_proveedor+"   pai: "+_nombre_pais+"    depto: "+_nombre_depto);
                    departamento.setValue(_nombre_depto);
                    pais_origen.setValue(_nombre_pais);
                    proveedor.setValue(_nombre_proveedor);
                    
                    cve_producto_anterior = Seleccion.getCve_producto();
                }
                catch(NumberFormatException | SQLException e){
                    System.out.println(""+e);
                }
            }
    }
    
    private void actualizar(){
        try {
            String _nombre_proveedor =String.valueOf(proveedor.getSelectionModel().getSelectedItem());
            String _nombre_pais =String.valueOf(pais_origen.getSelectionModel().getSelectedItem());
            String _nombre_depto =String.valueOf(departamento.getSelectionModel().getSelectedItem());
            int _cve_proveedor = 0;
            String _cve_pais = "";
            int _cve_depto = 0;
            ResultSet rs=con_consultar.getProveedorPorNombre(_nombre_proveedor);
            ResultSet rs1=con_consultar.getPaisPorNombre(_nombre_pais);
            ResultSet rs2=con_consultar.getDepartamentoPorNombre(_nombre_depto);
            while (rs.next()) {
                _cve_proveedor = rs.getInt("cve_proveedor");
            }
            while (rs1.next()) {
                _cve_pais= rs.getString("cve_pais");
            }
            while (rs2.next()) {
                _cve_depto = rs.getInt("cve_depto");
            }
            
            double _precio_venta= Double.parseDouble(String.valueOf(precio_venta.getText()));
            double _precio_compra= Double.parseDouble(String.valueOf(precio_compra.getText()));
                    
            
            if(con_actualizar.ActualizaProducto(
                cve_producto_anterior, 
                descripcion.getText(),
                marca.getText(),
                modelo.getText(),
                _precio_venta,
                _precio_compra,
                _cve_depto,
                _cve_pais,
                _cve_proveedor)){
                Alert m=new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("P");
                m.setContentText("P actualizado correctamente");
                m.setGraphic(new ImageView(new Image("iconos/productos.png")));
                m.show();
            }else{
                Alert m=new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Producto");
                m.setContentText("Producto no pudo ser actualizado");
                m.setGraphic(new ImageView(new Image("iconos/productos.png")));
                m.show();
            }
        } catch (Exception e) {
        }
    }
    
    private void limpiar(){
        descripcion.setText("");
        codigo.setText("");
        marca.setText("");
        modelo.setText("");
        precio_compra.setText("");
        precio_venta.setText("");
        departamento.setValue("SELECCIONE UN DEPARTAMENTO");
        proveedor.setValue("SELECCIONE PROVEEDOR");
        pais_origen.setValue("SELECCIONE UN PAIS");
    }
    
    private void eliminar(){
        List<Producto> sval = lista_.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                Producto Seleccionado = sval.get(i);
                try{
                    Alert msg =new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle(("ELiminar"));
                    msg.setHeaderText("Producto");
                    msg.setContentText("¿Seguro de eliminar al producto seleccionado?\n\n"+Seleccionado.getCve_producto()+" "+Seleccionado.getDescripcion());
                    msg.setGraphic(new ImageView(new Image("iconos/productos.png")));
                    Optional<ButtonType> res= msg.showAndWait();
                    if(res.get()==ButtonType.OK)
                    {
                        if(con_Eliminar.eliminaProductos(Seleccionado.getCve_producto())){
                            Alert ms =new Alert(Alert.AlertType.CONFIRMATION);
                            ms.setTitle(("Pais"));
                            ms.setHeaderText("Eliminado");
                            ms.setContentText("Se ha eliminado el empleado "+ Seleccionado.getCve_producto()+ " "+Seleccionado.getDescripcion() + " correctamente");
                            ms.setGraphic(new ImageView(new Image("iconos/productos.png")));
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }
}