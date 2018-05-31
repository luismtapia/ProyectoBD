package proyecto5basededatos;

import SQL.SQL;
import SQL.SQL_Actualizar;
import SQL.SQL_Consultar;
import SQL.SQL_Crear;
import SQL.SQL_Eliminar;
import apartado_tiene_productos.Apartado_Tiene_Producto;
import facturas.Factura;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import productos.Producto;
import sistema_de_apartados.Sistema_de_Apartado;
import tallas_es_de_productos.Talla_Es_De_Producto;
import ticket_se_incluye_en_producto.Ticket_Se_Incluye_En_Producto;
import tickets.Ticket;

/**
 *
 * @author luis_
 */

public class Proyecto5BaseDeDatos extends Application implements EventHandler<ActionEvent>{
    private Stage estacionPrimaria= new Stage();
    Metodos metodo=new Metodos();
    SQL_Crear con_crear= new SQL_Crear();
    SQL_Actualizar con_actualizar= new SQL_Actualizar();
    SQL_Consultar con_consultar= new SQL_Consultar();
    SQL_Eliminar con_Eliminar= new SQL_Eliminar();
    
    private ListView lista1,lista2,lista_tallas,lista_apartados;
    private TextField campocant,campo5,campo11;
    private TextField textfiel_cantidad_a_comprar,textfiel_total_ticket;
    private Label no_ticket_a_generar,campo2,campo6,campo22,campo33,campo44,campo55,campo77;
    private DatePicker campo3,campo4;
    private ComboBox campo1,campo66;
    private ComboBox combo_clientes,combo_tickets;
    private TextField fecha_compra,subtotal,iva,total1;
    private Button btn_procesar,btn_selecionar_tallas,btn_agregar_articulo ;
    String[] botones_izquierda,botones_derecha_editar;
    String[] usuario;
    double total=0;
    int apartado;
    int _no_ticket=-1,_cve_apartado=-1;
    
    public BorderPane border;
    
    private MenuBar barraMenu;
    private Menu menuAbout;
    
    SQL conn=new SQL();
    
    private Label departamento_seleccionado;
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        estacionPrimaria = primaryStage;
        initGUI();
        inicializaBaseDatos();
    }

    @SuppressWarnings("empty-statement")
    private void initGUI(){
        StackPane root = new StackPane();
        Scene scene;
        do{
            usuario=metodo.busca();
        }while(usuario[2].equals("false") || usuario[3].equals("false"));
        
        root.setStyle("-fx-background-color: #000000");
        root.getChildren().addAll(diseño_inicio(usuario));
        scene = new Scene(root, 1400, 700);
        estacionPrimaria.setTitle("El Guardarropa de Fernanda");
        estacionPrimaria.getIcons().add(new Image("iconos/logo.png"));
        estacionPrimaria.setResizable(false);
        estacionPrimaria.setScene(scene);
        estacionPrimaria.show();
    }
    
    private void inicializaBaseDatos(){
        conn.Conectar();
    }
    
    private void cerrarBaseDeDatos(){
        conn.closeConnection();
    }
    
    private BorderPane diseño_inicio(String[] usuario){
        border = new BorderPane();
        if (usuario[5].equals(""+1)) {
            border.setTop(metodo.generaEncabezado(usuario[4]));
            border.setLeft(generaMenuLateral_Izquierdo_Nivel_1());
        }else{
            if(usuario[5].equals(""+2)){
                border.setTop(metodo.generaEncabezado(usuario[4]));
                border.setLeft(generaMenuLateral_Nivel_3()); 
            }
            
        }
        return border;
    }
    
    //EMPLEADO GENERAL
    private FlowPane generaMenuLateral_Nivel_3() {
        FlowPane menu_izquierda = new FlowPane();
        menu_izquierda.setPadding(new Insets(2, 0, 2, 5));
        menu_izquierda.setVgap(1);
        menu_izquierda.setHgap(1);
        menu_izquierda.setPrefWrapLength(84);
        menu_izquierda.setStyle("-fx-background-color: #111111;");
        
        botones_izquierda = new String[3];
        botones_izquierda[0] = "VENTAS";
        botones_izquierda[1] = "APARTADO";
        botones_izquierda[2] = "FACTURAS";
        
        String[] imagenes = new String[3];
        imagenes[0] = "iconos/ventas.png";
        imagenes[1] = "iconos/apartado.png";
        imagenes[2] = "iconos/factura.png";
        
        Button pages[] = new Button[3];
        for (int i=0; i<3; i++) {
            pages[i] = new Button(botones_izquierda[i], new ImageView(new Image(imagenes[i])));
            pages[i].setPrefSize(80, 80);
            pages[i].setContentDisplay(ContentDisplay.TOP);
            pages[i].setTextFill(Color.web("#D4AF37"));
            pages[i].setStyle("-fx-background-color: transparent;");
            pages[i].setOnAction(handleBotones);
            menu_izquierda.getChildren().add(pages[i]);
        }

        return menu_izquierda;
    }
    
    //GERENTE
    private FlowPane generaMenuLateral_Izquierdo_Nivel_1() {
        FlowPane menu_izquierda = new FlowPane();
        menu_izquierda.setPadding(new Insets(2, 0, 2, 5));
        menu_izquierda.setVgap(1);
        menu_izquierda.setHgap(1);
        menu_izquierda.setPrefWrapLength(84);
        menu_izquierda.setStyle("-fx-background-color: #111111;");
        
        botones_izquierda = new String[5];
        botones_izquierda[0] = "VENTAS";
        botones_izquierda[1] = "APARTADO";
        botones_izquierda[2] = "FACTURAS";
        botones_izquierda[3] = "EDITAR";
        botones_izquierda[4] = "MAS";
        
        String[] imagenes = new String[5];
        imagenes[0] = "iconos/ventas.png";
        imagenes[1] = "iconos/apartado.png";
        imagenes[2] = "iconos/factura.png";
        imagenes[3] = "iconos/editar.png";
        imagenes[4] = "iconos/mas_opciones.png";
        
        Button pages[] = new Button[5];
        for (int i=0; i<5; i++) {
            pages[i] = new Button(botones_izquierda[i], new ImageView(new Image(imagenes[i])));
            pages[i].setPrefSize(80, 80);
            pages[i].setContentDisplay(ContentDisplay.TOP);
            pages[i].setTextFill(Color.web("#D4AF37"));
            pages[i].setStyle("-fx-background-color: transparent;");
            pages[i].setOnAction(handleBotones);
            menu_izquierda.getChildren().add(pages[i]);
        }

        return menu_izquierda;
    }
    
    private FlowPane generaMenuLateral_Derecha_Nivel_1_Atras(int aoa) {
        FlowPane menu_izquierda = new FlowPane();
        menu_izquierda.setPadding(new Insets(2, 0, 2, 5));
        menu_izquierda.setVgap(1);
        menu_izquierda.setHgap(1);
        menu_izquierda.setPrefWrapLength(84);
        menu_izquierda.setStyle("-fx-background-color: #111111;");
        
        botones_izquierda = new String[7];
        botones_izquierda[0] = "ATRAS";
        botones_izquierda[1] = "";
        botones_izquierda[2] = "";
        botones_izquierda[3] = "";
        botones_izquierda[4] = "";
        botones_izquierda[5] = "";
        botones_izquierda[6] = "ATRAS.";
        
        Button pages[] = new Button[7];
        
        for (int i=1; i<4; i++) {
            pages[i] = new Button(botones_izquierda[i]);
            pages[i].setPrefSize(80, 80);
            pages[i].setStyle("-fx-background-color: transparent;");
            menu_izquierda.getChildren().add(pages[i]);
        }
        
            if(aoa == 0){
                pages[0] = new Button(botones_izquierda[0], new ImageView(new Image("iconos/atras.png")));
                pages[0].setPrefSize(80, 80);
                pages[0].setContentDisplay(ContentDisplay.TOP);
                pages[0].setTextFill(Color.web("#D4AF37"));
                pages[0].setStyle("-fx-background-color: transparent;");
                pages[0].setOnAction(handleBotones);
                menu_izquierda.getChildren().add(pages[0]);
            }else{
                if(aoa == 1){
                    pages[6] = new Button(botones_izquierda[6], new ImageView(new Image("iconos/atras.png")));
                    pages[6].setPrefSize(80, 80);
                    pages[6].setContentDisplay(ContentDisplay.TOP);
                    pages[6].setTextFill(Color.web("#D4AF37"));
                    pages[6].setStyle("-fx-background-color: transparent;");
                    pages[6].setOnAction(handleBotones);
                    menu_izquierda.getChildren().add(pages[6]);
                }
            }

        return menu_izquierda;
    }
    
    //SECCION DE CODIGO PARA EL BOTON LATERAL VENTAS
    private VBox ventas(){
        VBox Vbox_ventas = new VBox();
        Vbox_ventas.setPadding(new Insets(15, 12, 15, 40));
        Vbox_ventas.setSpacing(5);
        Vbox_ventas.setStyle( "-fx-padding: 10;\n" +
                                "-fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5;\n" +
                                "-fx-border-radius: 10;\n" +
                                "-fx-border-color: #D4AF37;");
        
        HBox Hbox_ventas = new HBox();
        Hbox_ventas.setPadding(new Insets(15, 12, 15, 40));
        Hbox_ventas.setSpacing(30);

        HBox titulo = new HBox();
        titulo.setPadding(new Insets(10, 12, 10, 520));
        titulo.setSpacing(5);
        titulo.setStyle("-fx-background-color: #D4AF37;\n" +
                        "-fx-border-style: solid outside;\n" +
                        "-fx-border-width: 4;\n" +
                        "-fx-border-radius: 10;\n" +
                        "-fx-border-color: #000000;");
        Label title =new Label("VENTAS");
        title.centerShapeProperty().setValue(true);
        
        titulo.getChildren().add(title);
        Hbox_ventas.getChildren().addAll(lado_izquierdo(),centro(),lado_derecho());
        Vbox_ventas.getChildren().addAll(titulo,Hbox_ventas);
        return Vbox_ventas;
    }
    
    private VBox lado_izquierdo(){
        VBox Vbox_lado_izquierdo = new VBox();
        Vbox_lado_izquierdo.setPadding(new Insets(0, 12, 0, 12));
        Vbox_lado_izquierdo.setSpacing(5);
        Vbox_lado_izquierdo.setStyle("-fx-background-color: #000000;");
        
        //DEPARTAMENTOS
        HBox departamentos = new HBox();
        departamentos.setPadding(new Insets(10, 10, 10, 50));
        departamentos.setSpacing(40);
        departamentos.setStyle("-fx-background-color: #000000;");
        
        Button btn_ropa = new Button("ROPA", new ImageView(new Image("iconos/ropa.png")));
        btn_ropa.setPrefSize(100, 100);
        btn_ropa.setTextFill(Color.web("#D4AF37"));
        btn_ropa.setContentDisplay(ContentDisplay.TOP);
        btn_ropa.setOnAction(handleBotones);
        btn_ropa.setStyle("-fx-background-color: transparent; -fx-background-radius: 40; -fx-border-color: #D4AF37; -fx-border-insets: 2; -fx-border-width: 2; -fx-border-radius: 40; -fx-border-style: groove; ");
        
        Button btn_accesorios = new Button("ACCESORIOS", new ImageView(new Image("iconos/accesorios.png")));
        btn_accesorios.setPrefSize(100, 100);
        btn_accesorios.setTextFill(Color.web("#D4AF37"));
        btn_accesorios.setContentDisplay(ContentDisplay.TOP);
        btn_accesorios.setOnAction(handleBotones);
        btn_accesorios.setStyle("-fx-background-color: transparent; -fx-background-radius: 40; -fx-border-color: #D4AF37; -fx-border-insets: 2; -fx-border-width: 2; -fx-border-radius: 40; -fx-border-style: groove; ");
        departamentos.getChildren().addAll(btn_ropa,btn_accesorios);
        
        departamento_seleccionado = new Label(" ");
        departamento_seleccionado.setTextFill(Color.web("#D4AF37"));
        departamento_seleccionado.setPrefSize(150, 20);
        
        
        //BUSQUEDA
        HBox busqueda = new HBox();
        busqueda.setPadding(new Insets(15, 12, 15, 12));
        busqueda.setSpacing(10);
        busqueda.setStyle("-fx-background-color: #000000;");
        
        Label item = new Label("TOTAL: ");
        item.setTextFill(Color.web("#D4AF37"));
        item.setPrefSize(60, 20);
        textfiel_total_ticket = new TextField();
        textfiel_total_ticket.setPrefSize(100, 20);
        textfiel_total_ticket.setEditable(false);
        Button btn_buscar = new Button("", new ImageView(new Image("iconos/buscar.png",16,16,true,true,true)));
        btn_buscar.setTextFill(Color.web("#D4AF37"));
        btn_buscar.setStyle("-fx-background-color: transparent;");
        btn_buscar.setOnAction(handleBotones);
        btn_buscar.setVisible(false);
        
        busqueda.getChildren().addAll(item, textfiel_total_ticket, btn_buscar);
        
        //RESULTADOS
        HBox resultados = new HBox();
        resultados.setPadding(new Insets(5, 30, 5, 30));
        resultados.setSpacing(5);
        resultados.setStyle("-fx-background-color: #000000;");
        
        lista1 = new ListView();
        lista1.setPrefSize(400, 400);
        
        HBox caja_btn = new HBox();
        caja_btn.setPadding(new Insets(5, 5, 5, 150));
        caja_btn.setSpacing(5);
        caja_btn.setStyle("-fx-background-color: #000000;");
        
        
        btn_selecionar_tallas = new Button("VER TALLAS");
        btn_selecionar_tallas.setPrefSize(100, 20);
        btn_selecionar_tallas.setTextFill(Color.web("#D4AF37"));
        btn_selecionar_tallas.setOnAction(handleBotones);
        btn_selecionar_tallas.setStyle("-fx-background-color: transparent;");
        
        resultados.getChildren().addAll(lista1);
        caja_btn.getChildren().addAll(btn_selecionar_tallas);
        
        Vbox_lado_izquierdo.getChildren().addAll(departamentos,departamento_seleccionado,busqueda,resultados,caja_btn);
        
        return Vbox_lado_izquierdo;
    }
    
    private VBox centro(){
        VBox Vbox_centro = new VBox();
        Vbox_centro.setPadding(new Insets(0, 12, 0, 12));
        Vbox_centro.setSpacing(5);
        Vbox_centro.setStyle("-fx-background-color: #000000;");
        
        textfiel_cantidad_a_comprar = new TextField();
        textfiel_cantidad_a_comprar.setPrefSize(100, 20);
        textfiel_cantidad_a_comprar.setPromptText("INGRESE LA CANTIDAD");
        
        lista_tallas = new ListView();
        lista_tallas.setPrefSize(400, 400);
        
        //---------------------------------------------------------------
        HBox caja_btn = new HBox();
        caja_btn.setPadding(new Insets(5, 5, 5, 150));
        caja_btn.setSpacing(5);
        caja_btn.setStyle("-fx-background-color: #000000;");
        
        btn_agregar_articulo = new Button("AGREGAR");
        btn_agregar_articulo.setPrefSize(100, 20);
        btn_agregar_articulo.setTextFill(Color.web("#D4AF37"));
        btn_agregar_articulo.setOnAction(handleBotones);
        btn_agregar_articulo.setStyle("-fx-background-color: transparent;");
        btn_agregar_articulo.setVisible(false);
        
        caja_btn.getChildren().addAll(btn_agregar_articulo);
        
        Vbox_centro.getChildren().addAll(textfiel_cantidad_a_comprar,lista_tallas,caja_btn);
        
        return Vbox_centro;
    }
    
    private VBox lado_derecho(){
        VBox Vbox_ticket = new VBox();
        Vbox_ticket.setPadding(new Insets(15, 50, 15, 50));
        Vbox_ticket.setSpacing(10);
        Vbox_ticket.setStyle("-fx-background-color: #000000;");
        
        VBox informacion = new VBox();
        informacion.setPrefSize(450, 180);
        informacion.setPadding(new Insets(15, 12, 15, 12));
        informacion.setSpacing(10);
        informacion.setStyle("-fx-background-color: #111111;");
        
        String info = "\t\t\tFERNANDA\n"
                + "\tAv. Antonio Garcia Cubas\n"
                + "# 533\n"
                + "\t\t\t\t"+metodo.fecha();
        TextArea textArea = new TextArea(info);
        textArea.setPrefSize(400, 100);
        textArea.setCenterShape(true);
        textArea.setEditable(false);
        
        
        informacion.getChildren().addAll(textArea);
        
        
        lista2 = new ListView();
        lista2.setPrefSize(400, 400);
        
        HBox botonera = new HBox();
        //botonera.setPadding(new Insets(10, 10, 10, 120));
        botonera.setSpacing(40);
        botonera.setStyle("-fx-background-color: #000000;");
        
        Button btn_eliminar = new Button("f");
        btn_eliminar.setPrefSize(100, 20);
        btn_eliminar.setTextFill(Color.web("#D4AF37"));
        btn_eliminar.setOnAction(handleBotones);
        btn_eliminar.setStyle("-fx-background-color: transparent;");
        
        btn_procesar = new Button("PROCESAR");
        btn_procesar.setPrefSize(100, 20);
        btn_procesar.setTextFill(Color.web("#D4AF37"));
        btn_procesar.setOnAction(handleBotones);
        btn_procesar.setStyle("-fx-background-color: transparent;");
        btn_procesar.setVisible(false);
        
        no_ticket_a_generar = new Label(" ");
        no_ticket_a_generar.setTextFill(Color.web("#D4AF37"));
        no_ticket_a_generar.setPrefSize(150, 20);
        
        botonera.getChildren().addAll(no_ticket_a_generar,btn_eliminar, btn_procesar);
        
        Vbox_ticket.getChildren().addAll(informacion, lista2, botonera);

        return Vbox_ticket;
    }
    
    //SECCION DE CODIGO PARA EL BOTON LATERAL 
    private VBox sistema_apartado(){
        int x=150,xt=240,y=20;
        VBox Vbox_apartado = new VBox();
        Vbox_apartado.setPadding(new Insets(15, 12, 15, 20));
        Vbox_apartado.setSpacing(10);
        Vbox_apartado.setStyle( "-fx-padding: 10; -fx-border-style: solid inside;\n -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 10;" +
                                "-fx-border-color: #D4AF37;");

        HBox titulo = new HBox();
        titulo.setPadding(new Insets(10, 12, 10, 500));
        titulo.setSpacing(5);
        titulo.setStyle("-fx-background-color: #D4AF37; -fx-border-style: solid outside;\n" +
                        "-fx-border-width: 4;\n" +
                        "-fx-border-radius: 10;\n" +
                        "-fx-border-color: #000000;");
        Label title =new Label("SISTEMA DE APARTADO");
        //title.setTextFill(Color.WHITE);
        title.centerShapeProperty().setValue(true);
        titulo.getChildren().add(title);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 10, 40, 60));
        grid.setStyle("-fx-background-color: #000000;");
        
        Label item1 = new Label("Nombre del Cliente: ");
        item1.setTextFill(Color.web("#D4AF37"));
        item1.setPrefSize(x, y);
        
        campo1 = new ComboBox();
        campo1.setPrefSize(xt, y);
        campo1.setValue("SELECCIONE CLIENTE");
        
        Label item2 = new Label("Nombre del Empleado: ");
        item2.setTextFill(Color.web("D4AF37"));
        item2.setPrefSize(x, y);
        
        campo2 = new Label(usuario[4]);
        campo2.setTextFill(Color.WHITE);
        campo2.setPrefSize(x, y);
        
        Label item3 = new Label("Fecha de Apartado: ");
        item3.setTextFill(Color.web("D4AF37"));
        item3.setPrefSize(x, y);
        
        campo3 = new DatePicker();
        campo3.setPrefSize(xt, y);
        campo3.setValue(LocalDate.parse(""+metodo.fecha()));
        
        Label item4 = new Label("Fecha Liquidaciòn: ");
        item4.setTextFill(Color.web("D4AF37"));
        item4.setPrefSize(x, y);
        
        campo4 = new DatePicker();
        campo4.setPrefSize(xt, y);
        campo4.setValue(LocalDate.parse(""+metodo.fecha()));
        
        Label itemcant = new Label("Cantidad: ");
        itemcant.setTextFill(Color.web("D4AF37"));
        itemcant.setPrefSize(x, y);
        
        campocant = new TextField();
        campocant.setPrefSize(x,y);
        
        
        Label item5 = new Label("Enganche: ");
        item5.setTextFill(Color.web("D4AF37"));
        item5.setPrefSize(x, y);
        
        campo5 = new TextField();
        campo5.setPrefSize(xt, y);
        
        Label item6 = new Label("Saldo Restante: ");
        item6.setTextFill(Color.web("D4AF37"));
        item6.setPrefSize(x, y);
        
        campo6 = new Label("");
        campo6.setTextFill(Color.WHITE);
        campo6.setPrefSize(x, y);
        
        Button btn_aparta = new Button("Aparta");
        btn_aparta.setPrefSize(100, 20);
        btn_aparta.setOnAction(handleBotones);
        btn_aparta.setStyle("-fx-background-color: transparent;");
        btn_aparta.setTextFill(Color.web("#D4AF37"));
        
        Label vacio = new Label(" ");
        vacio.setPrefSize(100, y);
        
        //AGREGA PARTE 1
                                                        grid.add(vacio, 2, 0);
        grid.add(item1, 0, 0);      grid.add(campo1, 1, 0);         grid.add(item3, 3, 0);      grid.add(campo3, 4, 0);
                                                                    grid.add(item4, 3, 1);      grid.add(campo4, 4, 1);
        grid.add(item2, 0, 2);      grid.add(campo2, 1, 2);         grid.add(itemcant, 3, 2);      grid.add(campocant, 4, 2);
                                                                    grid.add(item5, 3, 3);      grid.add(campo5, 4, 3);
                                                                    grid.add(item6, 3, 4);      grid.add(campo6, 4, 4);
                                                                    
        
        HBox datos = new HBox();
        datos.setPadding(new Insets(5, 5, 5, 5));
        datos.setSpacing(5);
        datos.setStyle("-fx-background-color: #000000;");   
                                                                    
        lista_apartados = new ListView();
        lista_apartados.setPrefSize(300, 400);                                                            
        
        datos.getChildren().addAll(grid,lista_apartados);
        
        GridPane grid1 = new GridPane();
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(20, 10, 10, 50));
        grid1.setStyle("-fx-background-color: #000000;");
        
        Label item11 = new Label("Còdigo: ");
        item11.setTextFill(Color.web("D4AF37"));
        item11.setPrefSize(x, y);
        
        campo11 = new TextField();
        campo11.setPrefSize(x,y);
        
        Label item22 = new Label("Departamento: ");
        item22.setTextFill(Color.web("D4AF37"));
        item22.setPrefSize(x, y);
        
        campo22 = new Label("");
        campo22.setTextFill(Color.WHITE);
        campo22.setPrefSize(x, y);
        
        Label item33 = new Label("Descripciòn: ");
        item33.setTextFill(Color.web("D4AF37"));
        item33.setPrefSize(x, y);
        
        campo33 = new Label("");
        campo33.setTextFill(Color.WHITE);
        campo33.setPrefSize(x, y);
        
        Label item44 = new Label("Marca: ");
        item44.setTextFill(Color.web("D4AF37"));
        item44.setPrefSize(x, y);
        
        campo44 = new Label("");
        campo44.setTextFill(Color.WHITE);
        campo44.setPrefSize(x, y);
        
        Label item55 = new Label("Modelo: ");
        item55.setTextFill(Color.web("D4AF37"));
        item55.setPrefSize(x, y);
        
        campo55 = new Label("");
        campo55.setTextFill(Color.WHITE);
        campo55.setPrefSize(xt, y);
        
        Label item66 = new Label("Talla: ");
        item66.setTextFill(Color.web("#D4AF37"));
        item66.setPrefSize(x, y);
        
        campo66 = new ComboBox();
        //campo66.setTextFill(Color.WHITE);
        campo66.setPrefSize(xt, y);
        campo66.setValue("SELECCIONE TALLAS");
        
        Label item77 = new Label("Precio: ");
        item77.setTextFill(Color.web("D4AF37"));
        item77.setPrefSize(x, y);
        
        campo77 = new Label("");
        campo77.setTextFill(Color.WHITE);
        campo77.setPrefSize(xt, y);
        
        Button btn_buscar = new Button("Buscar",new ImageView(new Image("iconos/buscar.png",16,16,true,true,true)));
        btn_buscar.setPrefSize(100, 20);
        btn_buscar.setOnAction(handleBotones);
        btn_buscar.setStyle("-fx-background-color: transparent;");
        btn_buscar.setTextFill(Color.web("#D4AF37"));
        
        //AGREGA PARTE 2
        grid1.add(item11, 0, 0);      grid1.add(campo11, 1, 0);       grid1.add(btn_buscar, 2, 0);
        grid1.add(vacio, 0, 1);
        grid1.add(item22, 0, 3);      grid1.add(campo22, 1, 3);
        grid1.add(item33, 0, 4);      grid1.add(campo33, 1, 4);
        grid1.add(item44, 0, 5);      grid1.add(campo44, 1, 5);         grid1.add(item55, 3, 5);      grid1.add(campo55, 4, 5);
        grid1.add(item77, 0, 6);      grid1.add(campo77, 1, 6);         grid1.add(item66, 3, 6);      grid1.add(campo66, 4, 6);
        
        
        HBox botonera = new HBox();
        botonera.setPadding(new Insets(55, 5, 5, 500));
        botonera.setSpacing(10);
        botonera.setStyle("-fx-background-color: #000000;");
        
        Button btn_agregar_articulos_apartado = new Button("APARTAR");
        btn_agregar_articulos_apartado.alignmentProperty().set(Pos.CENTER);
        btn_agregar_articulos_apartado.setTextFill(Color.web("#D4AF37"));
        btn_agregar_articulos_apartado.setOnAction(handleBotones);
        btn_agregar_articulos_apartado.setStyle("-fx-background-color: transparent; -fx-border-color: #D4AF37;");
        
        Button btn_agregar = new Button("GENERAR APARTADO");
        btn_agregar.alignmentProperty().set(Pos.CENTER);
        btn_agregar.setTextFill(Color.web("#D4AF37"));
        btn_agregar.setOnAction(handleBotones);
        btn_agregar.setStyle("-fx-background-color: transparent; -fx-border-color: #D4AF37;");
        
        
        botonera.getChildren().addAll(btn_agregar_articulos_apartado,btn_agregar);
        
        Vbox_apartado.getChildren().addAll(titulo, datos, grid1,botonera);

        return Vbox_apartado;
    }
    
    public VBox facturas(){
        int x=200;
        VBox Vbox_factura = new VBox();
        Vbox_factura.setPadding(new Insets(15, 12, 15, 12));
        Vbox_factura.setSpacing(10);
        Vbox_factura.setStyle( "-fx-padding: 10;\n" +
                                "-fx-border-style: solid inside;\n" +
                                "-fx-border-width: 2;\n" +
                                "-fx-border-insets: 5;\n" +
                                "-fx-border-radius: 10;\n" +
                                "-fx-border-color: #D4AF37;");

        HBox titulo = new HBox();
        titulo.setPadding(new Insets(10, 12, 10, 12));
        titulo.setSpacing(10);
        titulo.setStyle("-fx-background-color: #D4AF37;\n" +
                        "-fx-border-style: solid outside;\n" +
                        "-fx-border-width: 4;\n" +
                        "-fx-border-radius: 10;\n" +
                        "-fx-border-color: #000000;");
        Label title =new Label("FACTURAS");
        //title.setTextFill(Color.WHITE);
        title.alignmentProperty().set(Pos.CENTER);
        titulo.getChildren().add(title);
        
        
        HBox combox = new HBox();
        combox.setPadding(new Insets(10, 12, 10, 12));
        combox.setSpacing(60);
        combox.setStyle("-fx-background-color: #D4AF37;");
        
        HBox combox_1 = new HBox();
        combox_1.setPadding(new Insets(10, 250, 10, 12));
        combox_1.setSpacing(20);
        combox_1.setStyle("-fx-background-color: #D4AF37;");
        
        Label no_clie =new Label("NUMERO DE CLIENTE: ");
        no_clie.setPrefSize(150, 20);
        
        combo_clientes = new ComboBox();
        combo_clientes.setValue("CLIENTES");
        
        combox_1.getChildren().addAll(no_clie,combo_clientes);
        
        Label no_tick =new Label("NUMERO DE TICKET: ");
        no_tick.setPrefSize(150, 20);
        
        combo_tickets = new ComboBox();
        combo_tickets.setValue("tickets");
        
        HBox combox_2 = new HBox();
        combox_2.setPadding(new Insets(10, 12, 10, 200));
        combox_2.setSpacing(20);
        combox_2.setStyle("-fx-background-color: #D4AF37;");
        
        combox_2.getChildren().addAll(no_tick,combo_tickets);
        
        combox.getChildren().addAll(combox_1,combox_2);
        
        GridPane marco = new GridPane();
        marco.setHgap(10);
        marco.setVgap(10);
        marco.alignmentProperty().set(Pos.CENTER);
        marco.setPadding(new Insets(15, 12, 15, 12));
        marco.setStyle("-fx-background-color: #000000;");
        
        Label item_1 = new Label("FECHA DE COMPRA: ");
        item_1.setTextFill(Color.WHITE);
        item_1.setPrefSize(x, 20);
        
        fecha_compra = new TextField();
        fecha_compra.setPrefSize(200, 20);
        
        Label item_2 = new Label("SUBTOTAL: ");
        item_2.setTextFill(Color.WHITE);
        item_2.setPrefSize(x, 20);
        
        subtotal = new TextField();
        subtotal.setPrefSize(200, 20);
        
        Label item_3 = new Label("IVA: ");
        item_3.setTextFill(Color.WHITE);
        item_3.setPrefSize(x, 20);
        
        iva = new TextField();
        iva.setPrefSize(200, 20);
        
        Label item_4 = new Label("TOTAL: ");
        item_4.setTextFill(Color.WHITE);
        item_4.setPrefSize(x, 20);
        
        total1 = new TextField();
        total1.setPrefSize(200, 20);
        
        marco.add(item_1, 0, 0);      
        marco.add(fecha_compra, 1, 0);         
        marco.add(item_2, 0, 1);      
        marco.add(subtotal, 1, 1);
        marco.add(item_3, 0, 2);      
        marco.add(iva, 1, 2);
        marco.add(item_4, 0, 3);      
        marco.add(total1, 1, 3);
        
        HBox botones = new HBox();
        botones.setStyle("-fx-background-color: #000000;");
        botones.setPadding(new Insets(15, 100, 15, 500));
        
        
        Button btn_buscar = new Button("BUSQUEDA");
        btn_buscar.setPrefSize(100, 20);
        btn_buscar.setTextFill(Color.web("#D4AF37"));
        btn_buscar.setOnAction(handleBotones);
        btn_buscar.setStyle("-fx-border-color: #D4AF37; -fx-background-color: #000000;");
        
        Button btn_facturar = new Button("FACTURAR");
        btn_facturar.setPrefSize(100, 20);
        btn_facturar.setTextFill(Color.web("#D4AF37"));
        btn_facturar.setOnAction(handleBotones);
        btn_facturar.setStyle("-fx-border-color: #D4AF37; -fx-background-color: #000000;");
        
        
        
        botones.getChildren().addAll(btn_buscar,btn_facturar);

        Vbox_factura.getChildren().addAll(titulo, combox, marco, botones);

        
        llenarComboBoxClientes_En_Facturas();
        llenarComboBoxTickets_En_Facturas();
        return Vbox_factura;
    }
    
    
    //SECCION DE CODIGO PARA EL BOTON LATERAL 
    public VBox editar(){
        int constante = 150;
        
        VBox vertical = new VBox();
        vertical.setPadding(new Insets(15, 0, 15, 0));
        vertical.setSpacing(10);
        //vertical.setStyle("-fx-background-color: #000000;");
        vertical.setStyle( "-fx-padding: 10;\n" +
                                "-fx-border-style: solid inside;\n" +
                                "-fx-border-width: 2;\n" +
                                "-fx-border-insets: 5;\n" +
                                "-fx-border-radius: 10;\n" +
                                "-fx-border-color: #D4AF37;");
        
        HBox titulo = new HBox();
        titulo.setPadding(new Insets(10, 12, 10, 500));
        titulo.setSpacing(5);
        titulo.setStyle("-fx-background-color: #D4AF37;\n" +
                        "-fx-border-style: solid outside;\n" +
                        "-fx-border-width: 4;\n" +
                        "-fx-border-radius: 10;\n" +
                        "-fx-border-color: #000000;");
        Label title =new Label("EDITAR TABLAS");
        //title.setTextFill(Color.WHITE);
        title.centerShapeProperty().setValue(true);
        titulo.getChildren().add(title);
        
        
        HBox horizontal = new HBox();
        horizontal.setPadding(new Insets(15, 50, 15, 500));
        horizontal.setSpacing(10);
        horizontal.setStyle("-fx-background-color: #000000;");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 200));
        
        Button productos = new Button("PRODUCTOS", new ImageView(new Image("iconos/productos.png")));
        productos.setPrefSize(constante, constante);
        productos.setTextFill(Color.web("#D4AF37"));
        productos.setContentDisplay(ContentDisplay.TOP);
        productos.setStyle("-fx-background-color: transparent;");
        productos.setOnAction(handleBotones);
        
        Button a = new Button("DEPARTAMENTOS", new ImageView(new Image("iconos/departamento.png")));
        a.setPrefSize(constante, constante);
        a.setTextFill(Color.web("#D4AF37"));
        a.setContentDisplay(ContentDisplay.TOP);
        a.setStyle("-fx-background-color: transparent;");
        a.setOnAction(handleBotones);
        
        Button b = new Button("EMPLEADOS", new ImageView(new Image("iconos/usuario.png")));
        b.setPrefSize(constante, constante);
        b.setTextFill(Color.web("#D4AF37"));
        b.setContentDisplay(ContentDisplay.TOP);
        b.setStyle("-fx-background-color: transparent;");
        b.setOnAction(handleBotones);
        
        Button c = new Button("PROVEEDORES", new ImageView(new Image("iconos/proveedores.png")));
        c.setPrefSize(constante, constante);
        c.setTextFill(Color.web("#D4AF37"));
        c.setContentDisplay(ContentDisplay.TOP);
        c.setStyle("-fx-background-color: transparent;");
        c.setOnAction(handleBotones);
        
        Button d = new Button("TALLAS", new ImageView(new Image("iconos/talla.png")));
        d.setPrefSize(constante, constante);
        d.setTextFill(Color.web("#D4AF37"));
        d.setContentDisplay(ContentDisplay.TOP);
        d.setStyle("-fx-background-color: transparent;");
        d.setOnAction(handleBotones);
        
        Button e = new Button("PAIS", new ImageView(new Image("iconos/pais.png")));
        e.setPrefSize(constante, constante);
        e.setTextFill(Color.web("#D4AF37"));
        e.setContentDisplay(ContentDisplay.TOP);
        e.setStyle("-fx-background-color: transparent;");
        e.setOnAction(handleBotones);
        
        Button f = new Button("ORDENES", new ImageView(new Image("iconos/ordenes.png")));
        f.setPrefSize(constante, constante);
        f.setTextFill(Color.web("#D4AF37"));
        f.setContentDisplay(ContentDisplay.TOP);
        f.setStyle("-fx-background-color: transparent;");
        f.setOnAction(handleBotones);
        
        Button g = new Button("NUMERO TELÉFONO", new ImageView(new Image("iconos/agenda.png")));
        g.setPrefSize(constante, constante);
        g.setTextFill(Color.web("#D4AF37"));
        g.setContentDisplay(ContentDisplay.TOP);
        g.setStyle("-fx-background-color: transparent;");
        g.setOnAction(handleBotones);
        
        Button h = new Button("TIPO DE TELÉFONO", new ImageView(new Image("iconos/tipo_telefono.png")));
        h.setPrefSize(constante, constante);
        h.setTextFill(Color.web("#D4AF37"));
        h.setContentDisplay(ContentDisplay.TOP);
        h.setStyle("-fx-background-color: transparent;");
        h.setOnAction(handleBotones);
        
        Button i = new Button("SUELDOS", new ImageView(new Image("iconos/sueldos.png")));
        i.setPrefSize(constante, constante);
        i.setTextFill(Color.web("#D4AF37"));
        i.setContentDisplay(ContentDisplay.TOP);
        i.setStyle("-fx-background-color: transparent;");
        i.setOnAction(handleBotones);
        
        Button j = new Button("USUARIOS", new ImageView(new Image("iconos/usuarios.png")));
        j.setPrefSize(constante, constante);
        j.setTextFill(Color.web("#D4AF37"));
        j.setContentDisplay(ContentDisplay.TOP);
        j.setStyle("-fx-background-color: transparent;");
        j.setOnAction(handleBotones);
        
        Button k = new Button("CAPACITACIÓN", new ImageView(new Image("iconos/capacitacion.png")));
        k.setPrefSize(constante, constante);
        k.setTextFill(Color.web("#D4AF37"));
        k.setContentDisplay(ContentDisplay.TOP);
        k.setStyle("-fx-background-color: transparent;");
        k.setOnAction(handleBotones);
        
        Button l = new Button("AREA", new ImageView(new Image("iconos/area.png")));
        l.setPrefSize(constante, constante);
        l.setTextFill(Color.web("#D4AF37"));
        l.setContentDisplay(ContentDisplay.TOP);
        l.setStyle("-fx-background-color: transparent;");
        l.setOnAction(handleBotones);
        
        Button q = new Button("CLIENTES", new ImageView(new Image("iconos/cliente.png")));
        q.setPrefSize(constante, constante);
        q.setTextFill(Color.web("#D4AF37"));
        q.setContentDisplay(ContentDisplay.TOP);
        q.setStyle("-fx-background-color: transparent;");
        q.setOnAction(handleBotones);
        
        
        //grid.add(new ImageView(new Image("iconos/logo.png",400,400,true,true,true)), 2, 0);
        
        horizontal.getChildren().add(new ImageView(new Image("iconos/logo.png")));
        
        
        grid.add(productos, 0, 1);      grid.add(a, 1, 1);          grid.add(b, 2, 1);          grid.add(c, 3, 1);          grid.add(l, 4, 1);
        
        grid.add(d, 0, 2);              grid.add(e, 1, 2);          grid.add(f, 2, 2);          grid.add(g, 3, 2);          grid.add(q, 4, 2);
        
        grid.add(h, 0, 3);              grid.add(i, 1, 3);          grid.add(j, 2, 3);          grid.add(k, 3, 3);
        
        
        vertical.getChildren().addAll(titulo,horizontal,grid);
        return vertical;
    }
    
    private VBox mas(){
        int constante = 200;
        
        VBox vertical = new VBox();
        vertical.setPadding(new Insets(15, 0, 15, 0));
        vertical.setSpacing(10);
        //vertical.setStyle("-fx-background-color: #000000;");
        vertical.setStyle( "-fx-padding: 10;\n" +
                                "-fx-border-style: solid inside;\n" +
                                "-fx-border-width: 2;\n" +
                                "-fx-border-insets: 5;\n" +
                                "-fx-border-radius: 10;\n" +
                                "-fx-border-color: #D4AF37;");
        
        HBox titulo = new HBox();
        titulo.setPadding(new Insets(10, 12, 10, 500));
        titulo.setSpacing(5);
        titulo.setStyle("-fx-background-color: #D4AF37;\n" +
                        "-fx-border-style: solid outside;\n" +
                        "-fx-border-width: 4;\n" +
                        "-fx-border-radius: 10;\n" +
                        "-fx-border-color: #000000;");
        Label title =new Label("EDITAR TABLAS");
        //title.setTextFill(Color.WHITE);
        title.centerShapeProperty().setValue(true);
        
        
        titulo.getChildren().add(title);
        
        
        HBox horizontal = new HBox();
        horizontal.setPadding(new Insets(15, 50, 15, 500));
        horizontal.setSpacing(10);
        horizontal.setStyle("-fx-background-color: #000000;");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 200));
        
        Button m = new Button("TALLA ES DE PRODUCTO", new ImageView(new Image("iconos/talla_esde_producto.png")));
        m.setPrefSize(constante, constante);
        m.setTextFill(Color.web("#D4AF37"));
        m.setContentDisplay(ContentDisplay.TOP);
        m.setStyle("-fx-background-color: transparent;");
        m.setOnAction(handleBotones);
        
        Button n = new Button("ORDEN PIDE PRODUCTOS", new ImageView(new Image("iconos/orden_pide_producto.png")));
        n.setPrefSize(constante, constante);
        n.setTextFill(Color.web("#D4AF37"));
        n.setContentDisplay(ContentDisplay.TOP);
        n.setStyle("-fx-background-color: transparent;");
        n.setOnAction(handleBotones);
        
        Button o = new Button("APARTADO TIENE", new ImageView(new Image("iconos/apartado_productos.png")));
        o.setPrefSize(constante, constante);
        o.setTextFill(Color.web("#D4AF37"));
        o.setContentDisplay(ContentDisplay.TOP);
        o.setStyle("-fx-background-color: transparent;");
        o.setOnAction(handleBotones);
        
        Button p = new Button("EMPLEADO RECIBE CAPACITACION", new ImageView(new Image("iconos/empleado_recibe_capacitacion.png")));
        p.setPrefSize(constante, constante);
        p.setTextFill(Color.web("#D4AF37"));
        p.setContentDisplay(ContentDisplay.TOP);
        p.setStyle("-fx-background-color: transparent;");
        p.setOnAction(handleBotones);
        
        Button r = new Button("TICKET SE INCLUYE EN PRODUCTO", new ImageView(new Image("iconos/ticket_en_factura.png")));
        r.setPrefSize(constante, constante);
        r.setTextFill(Color.web("#D4AF37"));
        r.setContentDisplay(ContentDisplay.TOP);
        r.setStyle("-fx-background-color: transparent;");
        r.setOnAction(handleBotones);
        
        Button s = new Button("TICKET", new ImageView(new Image("iconos/ticket_en_factura.png")));
        s.setPrefSize(constante, constante);
        s.setTextFill(Color.web("#D4AF37"));
        s.setContentDisplay(ContentDisplay.TOP);
        s.setStyle("-fx-background-color: transparent;");
        s.setOnAction(handleBotones);
        
        //grid.add(new ImageView(new Image("iconos/logo.png",400,400,true,true,true)), 2, 0);
        
        horizontal.getChildren().add(new ImageView(new Image("iconos/logo.png")));
        
        
        grid.add(n, 0, 1);              grid.add(m, 1, 1);          
        
        grid.add(o, 0, 2);              grid.add(p, 1, 2);
        
        grid.add(r, 2, 1);              grid.add(s, 1, 3);
        
        
        vertical.getChildren().addAll(titulo,horizontal,grid);
        return vertical;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        System.err.println("ERROR");
    }
    
    final EventHandler<ActionEvent> handleBotones = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            Button x = (Button) event.getSource();
            switch (x.getText()) {
                case "VENTAS":
                    border.setCenter(ventas());
                    border.setRight(null);
                    break;
                case "FACTURAS":
                    border.setCenter(facturas());//SIRVE
                    border.setRight(null);
                    break;
                case "APARTADO":
                    border.setCenter(sistema_apartado());
                    llenarComboBoxClientes();
                    border.setRight(null);
                    break;
                case "EDITAR":
                    border.setCenter(editar());
                    border.setRight(generaMenuLateral_Derecha_Nivel_1_Atras(0));
                    break;
                case "MAS":
                    border.setCenter(mas());
                    border.setRight(generaMenuLateral_Derecha_Nivel_1_Atras(1));
                    break;
//-----------------------------------------------------------------------------------------------------------------------------------
                case "ATRAS":
                    border.setCenter(editar());
                    break;
                case "ATRAS.":
                    border.setCenter(mas());
                    break;
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
                case "AREA":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/areas/Areas.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "CAPACITACIÓN":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/capacitaciones/Capacitaciones.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "EMPLEADOS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/empleados/Empleados.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "SUELDOS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/sueldos/Sueldos.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "TIPO DE TELÉFONO":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/tipos_telefonos/Tipos_Telefonos.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "PROVEEDORES":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/proveedores/Proveedores.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println("Proveedores: "+e);}
                    break;
                case "PRODUCTOS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/productos/Productos.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "DEPARTAMENTOS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/departamentos/Departamentos.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "PAIS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/paises/Paises.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "USUARIOS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/usuarios/Usuarios.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "EMPLEADO RECIBE CAPACITACION":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/empleados_reciben_capacitacion/Empleados_Reciben_Capacitacion.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "NUMERO TELÉFONO":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/telefonos/Telefonos.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "TALLAS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/tallas/Tallas.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "CLIENTES":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/clientes/Clientes.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "ORDENES":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/ordenes/Ordenes.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "ORDEN PIDE PRODUCTOS":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/orden_pide_productos/Orden_Pide_Productos.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "APARTADO TIENE":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/apartado_tiene_productos/Apartado_tiene_Producto.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "TALLA ES DE PRODUCTO":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/tallas_es_de_productos/Tallas_Es_De_Productos.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "TICKET SE INCLUYE EN PRODUCTO":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/ticket_se_incluye_en_producto/Ticket_Se_Incluye_En_Producto.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "TICKET":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/tickets/Ticket.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                    
//-----------------------------------------------BOTONES EDITAR---------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------------------------
                case "ROPA":
                    try {
                        departamento_seleccionado.setText("Ropa");
                        lista1.getItems().clear();
                        ResultSet rs2=con_consultar.consultarDepartamento_Especifico("Ropa");
                        int _cve_departamento=-1;
                        while (rs2.next()) {                    
                            _cve_departamento = rs2.getInt("cve_depto");
                        }
                        lista1.setItems(con_consultar.getDepartamento_Especifico(_cve_departamento));
                    } catch (Exception e) {
                        System.err.println(""+e);
                    }
                    
                    break;
                case "ACCESORIOS":
                    try {
                        departamento_seleccionado.setText("Accesorios");
                        lista1.getItems().clear();
                        ResultSet rs2=con_consultar.consultarDepartamento_Especifico("Accesorios");
                        int _cve_departamento=-1;
                        while (rs2.next()) {                    
                            _cve_departamento = rs2.getInt("cve_depto");
                        }
                        lista1.setItems(con_consultar.getDepartamento_Especifico(_cve_departamento));
                    } catch (Exception e) {
                    }
                    break;
                case "AGREGAR":
                    {
                        try {
                            agregar();
                            btn_procesar.setVisible(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(Proyecto5BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "VER TALLAS":
                    {
                        try {
                            Sele();
                            btn_agregar_articulo.setVisible(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(Proyecto5BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;    
                case "ELIMINAR":
                    //quitar();
                    break;
                case "PROCESAR":
                    actualizar_compra();
                    break;
                case "Buscar":
                    buscarProducto(Integer.parseInt(campo11.getText()));
                    break;
                case "APARTAR":
                    apartado();
                    break;
                case "GENERAR APARTADO":
                    sistema_de_apartado();
                    break;
                case "FACTURAR":
                    facturar();
                    break;
                case "BUSQUEDA":
                    busqueda();
                    break;
                case "f":
                    procesar_compra();
                    break;
                default:
                    break;
            }
        }
    };
    
    private void busqueda_Tallas(int codigo){
        lista_tallas.getItems().clear();
        lista_tallas.setItems(con_consultar.getTallas_Por_Clave_Producto_List(codigo));
    }
    
    private void Sele() throws SQLException{
        try{
            ResultSet rs = con_consultar.getUltimo_Ticket();
            while(rs.next()){
                _no_ticket = rs.getInt("no_ticket");
            }
            no_ticket_a_generar.setText(""+_no_ticket);
            
            List<Producto> sval = lista1.getSelectionModel().getSelectedItems();
                for(int i=0;i<sval.size();i++){
                    Producto seleccion = sval.get(i);
                    int codigo = seleccion.getCve_producto();
                    busqueda_Tallas(codigo);
                }
        }catch(Exception e){
            System.out.println(""+e);
        }
    }
    
    private void agregar() throws SQLException{
        try{
            int cve_producto=0;
            List<Producto> sval = lista1.getSelectionModel().getSelectedItems();
                for(int i=0;i<sval.size();i++){
                    Producto seleccion_inven = sval.get(i);
                    cve_producto = seleccion_inven.getCve_producto();
                    lista2.getItems().add(seleccion_inven);
                    total += seleccion_inven.getPrecio_venta();
                    textfiel_total_ticket.setText(""+total);
                }
                
            actualiza_existencia();
            Ticket_Se_Incluye_En_Producto nuevo = new Ticket_Se_Incluye_En_Producto(Integer.parseInt(textfiel_cantidad_a_comprar.getText()), _no_ticket, cve_producto);
            con_crear.insertaTicket_se_incluye_en_producto(nuevo);
            
        }catch(SQLException | NumberFormatException e){
            System.out.println(""+e);
        }
    }
    
    private void actualiza_existencia() throws SQLException{
        int _cantidad_a_quitar = Integer.parseInt(textfiel_cantidad_a_comprar.getText());
        int number=-1;
        int cve=-1;
        
        List<Talla_Es_De_Producto> sval_tallas = lista_tallas.getSelectionModel().getSelectedItems();
        for(int i=0;i<sval_tallas.size();i++){
            Talla_Es_De_Producto seleccion_talla = sval_tallas.get(i);
            number = seleccion_talla.getNumero();
            cve = seleccion_talla.getCve_producto();
        }
//                    ResultSet g = con_consultar.getTalla_Es_De_ProductoPorClaves(number, cve);
//                    while(g.next()){
//                        _cantidad_existencia = g.getInt("cantidad_existencia");
//                    }
       // System.out.println(""+number+"\n"+cve+"\n"+_cantidad_existencia+"\n"+_cantidad_a_quitar);

        if(con_actualizar.ActualizaTalla_es_de_Producto(_cantidad_a_quitar, number, cve) == true){
            Alert msg=new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Actualizadas las existencias");
            msg.setContentText("Agregdo al carrito correctamente");
            msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
            msg.show();
         }else
            System.err.println("falso");
        
        busqueda_Tallas(cve);
    }
    
    private void procesar_compra(){
        try {
            Ticket nuevo_ticket = new Ticket(0, metodo.fecha(), total, Integer.parseInt(usuario[6]));
            if(con_crear.insertaTicket(nuevo_ticket)){
                Alert msg=new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Compra registrada");
                msg.setContentText("Su compra fue relizada correctamente");
                msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
                msg.show();
                
                ResultSet rs = con_consultar.getUltimo_Ticket();
                while(rs.next()){
                    _no_ticket = rs.getInt("no_ticket");
                }
             }else{
                Alert msg=new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Hay un Problema");
                msg.setContentText("Llame a SOPORTE TÉCNICO");
                msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
                msg.show();
            }
            
        } catch (NumberFormatException | SQLException e) {}
    }
    
    private void actualizar_compra(){
        try {
            
            if(con_actualizar.ActualizaTicket(_no_ticket, metodo.fecha(), total, Integer.parseInt(usuario[6]))){
                Alert msg=new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Compra registrada");
                msg.setContentText("Su compra fue relizada correctamente");
                msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
                msg.show();
                
                ResultSet rs = con_consultar.getUltimo_Ticket();
                while(rs.next()){
                    _no_ticket = rs.getInt("no_ticket");
                }
             }else{
                Alert msg=new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Hay un Problema");
                msg.setContentText("Llame a SOPORTE TÉCNICO");
                msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
                msg.show();
            }
            
        } catch (NumberFormatException | SQLException e) {}
    }
    
    private void quitar(){
//        List<Producto> sval = lista2.getSelectionModel().getSelectedItems();
//            for(int i=0;i<sval.size();i++){
//                Producto seleccion = sval.get(i);
//                try{   
//                    lista2.getItems().remove(seleccion);
//                    total -= seleccion.getPrecio_venta();
//                    textfiel_total_ticket.setText(""+total);
//                }
//                catch(Exception e){
//                    System.out.println(e.getMessage());
//                }
//            }
    }
    
    private void buscarProducto(int codigo){
        try {
            ResultSet rs_talla_de_producto=con_consultar.getTallas_Es_De_Producto_Por_Cve_Producto(codigo);
            campo66.getItems().clear();
            while (rs_talla_de_producto.next()) {
                campo66.getItems().add(rs_talla_de_producto.getInt("numero"));
            }
            ResultSet rs=con_consultar.getProductoPorCodigo(codigo);
            while (rs.next()) {
                campo22.setText(""+rs.getInt("cve_depto"));
                campo33.setText(""+rs.getString("descripcion"));
                campo44.setText(""+rs.getString("marca"));
                campo55.setText(""+rs.getString("modelo"));
                campo77.setText(""+rs.getDouble("precio_venta"));
                
                double enganche = Double.parseDouble(campo5.getText());
                double precio = Double.parseDouble(campo77.getText());
                double cantidad = Double.parseDouble(campocant.getText());
                double total_ = cantidad*precio-enganche;
                campo6.setText(""+total_);
            }
        } catch (SQLException | NumberFormatException e) {}
    }
    
    private void sistema_de_apartado(){
        try {
            String _nombre_cliente = String.valueOf(campo1.getSelectionModel().getSelectedItem());
            String _nombre_empleado = campo2.getText();
            Date _fecha_apartado=Date.valueOf(campo3.getValue().toString());
            Date _fecha_liquidacion=Date.valueOf(campo4.getValue().toString());
            double _enganche = Double.parseDouble(campo5.getText());
            double _restante = Double.parseDouble(campo6.getText());
            int _cve_depto = Integer.parseInt(campo22.getText());
            
            int _no_cliente=-1;
            int _no_empleado=-1;
            
            ResultSet rs_clientes=con_consultar.getClientePorNombre(_nombre_cliente);
            while (rs_clientes.next()) {
                _no_cliente = rs_clientes.getInt("no_cliente");
            }
            ResultSet rs_empleados=con_consultar.getEmpleadoPorNombre(_nombre_empleado);
            while (rs_empleados.next()) {
                _no_empleado = rs_empleados.getInt("no_empleado");
            }
            
            Sistema_de_Apartado nuevoApartado = new Sistema_de_Apartado(0,_fecha_apartado,_fecha_liquidacion,_enganche,_restante,_no_empleado,_no_cliente,_cve_depto);
            if(con_crear.insertaSistema_De_Apartado(nuevoApartado)){
               Alert msg=new Alert(Alert.AlertType.INFORMATION);
               msg.setTitle("Registro guardado");
               msg.setContentText("guardado correctamente");
               msg.setGraphic(new ImageView(new Image("iconos/apartado.png")));
               msg.show();
            }
        } catch (NumberFormatException | SQLException e) {    
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("ERROR");
            m.setContentText("Apartado no pudo ser creado"+e);
            m.setGraphic(new ImageView(new Image("iconos/apartado.png")));
            m.show();
            System.out.println(e);
        }
    }
    
    private void apartado(){
        try {
            ResultSet rs = con_consultar.getUltimo_Apartado();
            while(rs.next()){
                _cve_apartado = rs.getInt("cve_apartado");
            }
            
            System.err.println("cve:"+_cve_apartado);
            
            int codigo = Integer.parseInt(campo11.getText());
            int numero = Integer.parseInt(String.valueOf(campo66.getSelectionModel().getSelectedItem()));
            int _cantidad_existencia=-1;
            int _cantidad_a_quitar = Integer.parseInt(campocant.getText());
            int _cantidad_nueva;
            
            ResultSet rsul = con_consultar.getTalla_Es_De_ProductoPorClaves(numero,codigo);
            while(rs.next()){
                _cantidad_existencia = rsul.getInt("cantidad_existencia");
            }
            
            _cantidad_nueva = _cantidad_existencia - _cantidad_a_quitar;
            
            Apartado_Tiene_Producto nuevo = new Apartado_Tiene_Producto(_cantidad_a_quitar, _cve_apartado, codigo);
            if(con_crear.insertaApartado_Tiene_Productos(nuevo)){
                System.err.println("correcto");
                if(con_actualizar.ActualizaTalla_es_de_Producto(_cantidad_nueva, numero, codigo)){
                    Alert msg=new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Registro guardado");
                    msg.setContentText("correctamente");
                    msg.setGraphic(new ImageView(new Image("iconos/ticket_en_factura.png")));
                    msg.show();
                    
                    lista_apartados.getItems().add(""+_cantidad_a_quitar+"\t"+codigo+"\n"+numero);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Proyecto5BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void llenarComboBoxClientes(){
        try {
            ResultSet rs_clientes=con_consultar.consultarClientes();
            campo1.getItems().clear();
            while (rs_clientes.next()) {
                campo1.getItems().add(rs_clientes.getString("nombre_cliente"));
            }
        } catch (Exception e) {
        }
    }
    
    private void llenarComboBoxClientes_En_Facturas(){
        try {
            ResultSet rs_clientes=con_consultar.consultarClientes();
            combo_clientes.getItems().clear();
            while (rs_clientes.next()) {
                combo_clientes.getItems().add(rs_clientes.getString("nombre_cliente"));
            }
        } catch (Exception e) {
        }
    }
    
    private void llenarComboBoxTickets_En_Facturas(){
        try {
            ResultSet rs_tickets=con_consultar.consultarTickets();
            combo_tickets.getItems().clear();
            while (rs_tickets.next()) {
                combo_tickets.getItems().add(rs_tickets.getInt("no_ticket"));
            }
        } catch (Exception e) {
        }
    }
    
    private void facturar(){
        try {
            String _nombre_cliente = String.valueOf(combo_clientes.getSelectionModel().getSelectedItem());
            int _numero_ticket = Integer.parseInt(String.valueOf(combo_tickets.getSelectionModel().getSelectedItem()));
            
            int _no_cliente=-1;
            int _no_ticket_factura=-1;
            Date _fecha_compra = null;
            double _subtotal,_iva,_total=0;
            
            ResultSet rs_clientes=con_consultar.getClientePorNombre(_nombre_cliente);
            while (rs_clientes.next()) {
                _no_cliente = rs_clientes.getInt("no_cliente");
            }
            
            ResultSet rs_ticket=con_consultar.getTicketPorNumero(_numero_ticket);
            while (rs_ticket.next()) {
                _no_ticket_factura = rs_ticket.getInt("no_ticket");
                _fecha_compra = rs_ticket.getDate("fecha_compra");
                _total = rs_ticket.getDouble("total_compra");
            }
            _subtotal = _total*0.84;
            _iva = _total*0.16;
            
            Factura nuevo= new Factura(0,_fecha_compra, _subtotal, _iva, _total, _no_ticket_factura, _no_cliente);
            if(con_crear.insertaFactura(nuevo)){
               Alert msg=new Alert(Alert.AlertType.INFORMATION);
               msg.setTitle("Registro guardado");
               msg.setContentText("guardado correctamente");
               msg.setGraphic(new ImageView(new Image("iconos/apartado.png")));
               msg.show();
            }
        } catch (NumberFormatException | SQLException e) {    
            Alert m=new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("ERROR");
            m.setContentText("Apartado no pudo ser creado"+e);
            m.setGraphic(new ImageView(new Image("iconos/apartado.png")));
            m.show();
            System.out.println(e);
        }
    }
    
    private void busqueda(){
        try {
            int _numero_ticket = Integer.parseInt(String.valueOf(combo_tickets.getSelectionModel().getSelectedItem()));
            
            Date _fecha_compra = null;
            double _subtotal=0,_iva=0,_total=0;
            
            ResultSet rs_ticket=con_consultar.getTicketPorNumero(_numero_ticket);
            while (rs_ticket.next()) {
                _fecha_compra = rs_ticket.getDate("fecha_compra");
                _total = rs_ticket.getDouble("total_compra");
            }
            
            _subtotal = _total-(_total*0.16);
            _iva = _total*0.16;
            fecha_compra.setText(""+_fecha_compra);
            subtotal.setText(""+_subtotal);
            iva.setText(""+_iva);
            total1.setText(""+_total);
            
            
        } catch (NumberFormatException | SQLException e) {    
            System.err.println(""+e);
        }
    }
}