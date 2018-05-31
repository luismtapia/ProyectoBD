package ticket_se_incluye_en_producto;

import tickets.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import SQL.SQL_Consultar;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Ticket_Se_Incluye_En_ProductoController implements Initializable {
    SQL_Consultar con_consultar= new SQL_Consultar();
    
    @FXML
    private ListView lista_;
    @FXML
    private Label lbl1,lbl2,lbl3;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        busqueda();
        lbl1.setTextFill(Color.web("D4AF37"));
        lbl2.setTextFill(Color.web("D4AF37"));
        lbl3.setTextFill(Color.web("D4AF37"));
    }
    
    private void busqueda(){
        try {
            lista_.getItems().clear();
            lista_.setItems(con_consultar.getTicket_Se_Incluye());
        } catch (Exception e) {
        }
    }
    
}