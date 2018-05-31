package proyecto5basededatos;

import SQL.SQL;
import SQL.SQL_Consultar;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class Metodos {
    String[] botones;
    private EventHandler<ActionEvent> handleBotones;
    SQL_Consultar con_consultar = new SQL_Consultar();
    SQL conn=new SQL();
    
    public String[] login(){
        String[] usuario = new String[2];
        
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Iniciar sesión");
        dialog.setHeaderText("Bienvenido");
        
        dialog.setGraphic(new ImageView(new Image("iconos/usuario.png",16,16,true,true,true)));// Set the icon
        
        // Set the button types.
        ButtonType tipoBotonLogin = new ButtonType("Entrar", ButtonBar.ButtonData.OK_DONE);
        ButtonType tipoBotonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(tipoBotonLogin, tipoBotonCancelar);
        
        VBox diseño = new VBox();
        diseño.setPadding(new Insets(10, 10, 20, 50));
        diseño.setStyle("-fx-background-color: #000000;");
        
        HBox mensaje = new HBox();
        mensaje.setPadding(new Insets(10, 10, 20, 50));
        
        HBox logo = new HBox();
        logo.setPadding(new Insets(10, 10, 20, 60));
        logo.getChildren().add(new ImageView(new Image("iconos/logo.png")));
                
        diseño.getChildren().addAll(mensaje,logo);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 60));
        grid.setStyle("-fx-background-color: #000000;");

        TextField username = new TextField("");
        username.setPromptText("Usuario");
        PasswordField password = new PasswordField();
        password.setPromptText("Contraseña");
        
        grid.add(new ImageView(new Image("/iconos/usuario.png",16,16,true,true,true)), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new ImageView(new Image("/iconos/contraseña.png",16,16,true,true,true)), 0, 1);
        grid.add(password, 1, 1);

        
        diseño.getChildren().add(grid);
        
        // Enable/Disable login button depending on whether a username was entered.
        Node btn_IniciarSesion = dialog.getDialogPane().lookupButton(tipoBotonLogin);
        btn_IniciarSesion.setStyle("-fx-background-color: #D4AF37;");
        btn_IniciarSesion.setDisable(true);
        
        Node btn_cancelar = dialog.getDialogPane().lookupButton(tipoBotonCancelar);
        btn_cancelar.setStyle("-fx-background-color: #D4AF37;");

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            btn_IniciarSesion.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(diseño);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == tipoBotonLogin) {
                return new Pair<>(username.getText(), password.getText());
            }else
                if (dialogButton == tipoBotonCancelar) {
                    System.exit(0);
                }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            usuario[0] = username.getText();
            usuario[1] = password.getText();
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
        
        return usuario;
    }
    
    public String[] busca(){
        conn.Conectar();
        String[] usuario = new String[7];  // usuario   contraseña  existe usuario      existe contraseña       nombre_empleado     nivel   no_empleado
        String[] usuario_ingresado;
        int registros=0;
        
        usuario_ingresado = login();
        //cuenta los usuarios
        ResultSet contador = con_consultar.consultarUsuarios();
        try {
            while (contador.next()) {registros++;}
        } catch (Exception e) {System.out.println(e);}
        
        String[] usuarios_en_BD = new String[registros];
        String[] contraseñas_en_BD = new String[registros];
        String[] numeros_de_empleado = new String[registros];
        
        registros=0;
        ResultSet rs = con_consultar.consultarUsuarios();
        try {
            while (rs.next()) {                
                usuarios_en_BD[registros] = String.valueOf(rs.getInt("usuario"));
                contraseñas_en_BD[registros] = rs.getString("contraseña");
                numeros_de_empleado[registros] = String.valueOf(rs.getInt("no_empleado"));
                registros++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        for (int j = 0; j < registros; j++) {
            if(usuario_ingresado[0].equals(usuarios_en_BD[j])){
                usuario[0] = usuarios_en_BD[j];
                usuario[2] = "true";
                if(usuario_ingresado[1].equals(contraseñas_en_BD[j])){
                    int nivel=0;
                    usuario[1] = contraseñas_en_BD[j];
                    usuario[3] = "true";
                                        ResultSet rs1 = con_consultar.getEmpleadoPorNumero(Integer.parseInt(numeros_de_empleado[j]));
                                        try {
                                            while (rs1.next()) {
                                                usuario[4] = rs1.getString("nombre_empleado");
                                                nivel = rs1.getInt("nivel");
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        }
                    usuario[5] = ""+nivel;
                    usuario[6] = numeros_de_empleado[j];
                    return usuario;//si esta regresa datos validos
                }else{
                    usuario[1] = "0";
                    usuario[3] = "false";
                    usuario[4] ="0";
                    usuario[5] = "-1";
                    usuario[6] = "0";
                    System.out.println("la contraseña es incorrecta");
                    conn.closeConnection();
                    return usuario;
                }
            }else{
                System.out.println("No esta usuario en posicion "+usuarios_en_BD[j]);
            }
        }
        usuario[0] = "0";           //usuario
        usuario[2] = "false";       //existe
        usuario[1] = "0";           //contraseña
        usuario[3] = "false";       //existe
        usuario[4] = "0";           //nombre_empleado
        usuario[5] = "-1";          //nivel
        usuario[6] = "0";           //no_empleado
        conn.closeConnection();
        return usuario; //regresa datos NO validos
    }
    
    //ENCABEZADO
    public HBox generaEncabezado(String usuario){
        HBox Hbox_ENCABEZADO = new HBox();
        Hbox_ENCABEZADO.setPadding(new Insets(10, 30, 10, 100));
        
        Label nombre_usuario = new Label(usuario, new ImageView(new Image("iconos/usuario.png",16,16,true,true,true)));
        nombre_usuario.setTextFill(Color.web("#D4AF37"));
        nombre_usuario.setPrefSize(250, 20);
        
        Hbox_ENCABEZADO.getChildren().addAll(nombre_usuario);
        
        return Hbox_ENCABEZADO;
    }
    
    
    public Date fecha(){
        java.util.Date utilDate = new Date(System.currentTimeMillis());
        Date sqlDate;
        sqlDate = (Date) utilDate;
        return sqlDate;
    }
    
    public Date fecha_apartado(int depto){
        java.util.Date utilDate = new Date(System.currentTimeMillis());
        Date sqlDate;
        sqlDate = (Date) utilDate;
        return sqlDate;
    }
}
