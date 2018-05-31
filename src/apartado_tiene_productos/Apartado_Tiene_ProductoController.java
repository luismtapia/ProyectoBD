package apartado_tiene_productos;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import SQL.SQL_Consultar;

public class Apartado_Tiene_ProductoController implements Initializable {
    SQL_Consultar con_consultar= new SQL_Consultar();
    
    @FXML
    private ListView lista_;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        busqueda();
    }
    
    private void busqueda(){
        try {
            lista_.getItems().clear();
            lista_.setItems(con_consultar.getApartado_Tiene_Producto());
        } catch (Exception e) {
        }
    }
    
}