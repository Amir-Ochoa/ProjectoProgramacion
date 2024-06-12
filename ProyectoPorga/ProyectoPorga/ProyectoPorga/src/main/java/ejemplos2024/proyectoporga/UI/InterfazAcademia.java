package ejemplos2024.proyectoporga.UI;
import ejemplos2024.proyectoporga.Helpers.AcademiaHelper;
import ejemplos2024.proyectoporga.Modelos.Academia;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InterfazAcademia extends Application {
    private TableView<Academia> tabla;
    private ObservableList<Academia> observableListaAcademias;

    @Override
    public void start(Stage primaryStage) {
        // Crear el rectángulo azul oscuro
        Rectangle darkBlueRectangle = new Rectangle();
        darkBlueRectangle.setFill(Color.rgb(64, 112, 169)); // Un azul más oscuro
        darkBlueRectangle.setHeight(30); // Ajusta la altura del rectángulo según sea necesario
        darkBlueRectangle.setWidth(850); // Ajusta el ancho del rectángulo según el ancho de la escena inicial

        // Crear la etiqueta
        Text titleLabel = new Text("SISTEMA GESTOR DE ACADEMIAS UV");
        titleLabel.setFill(Color.WHITE); // Color de la fuente
        titleLabel.setFont(Font.font("Times New Roman", 18));

        // Crear un StackPane para contener el rectángulo y la etiqueta
        StackPane topBar = new StackPane();
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0)); // Asegúrate de que no haya padding
        topBar.getChildren().addAll(darkBlueRectangle, titleLabel);

        // Hacer que el rectángulo se extienda de izquierda a derecha
        darkBlueRectangle.widthProperty().bind(topBar.widthProperty());

        // Crear el rectángulo azul claro
        Rectangle lightBlueRectangle = new Rectangle();
        lightBlueRectangle.setFill(Color.rgb(100, 149, 237)); // Un azul claro
        lightBlueRectangle.setHeight(80); // Ajusta la altura del rectángulo según sea necesario
        lightBlueRectangle.setWidth(850); // Ajusta el ancho del rectángulo según el ancho de la escena inicial

        // Crear el rectángulo blanco en la parte izquierda (más pequeño)
        Rectangle whiteRectangle = new Rectangle();
        whiteRectangle.setFill(Color.WHITE);
        whiteRectangle.setWidth(67); // Ancho más pequeño para el rectángulo blanco
        whiteRectangle.setHeight(67);

        // Crear un StackPane para contener el rectángulo azul claro y el blanco
        Pane bottomBar = new Pane();
        // bottomBar.setAlignment(Pos.CENTER_LEFT); // Cambiar la alineación a la izquierda
        bottomBar.setPadding(new Insets(0)); // Asegúrate de que no haya padding
        bottomBar.setTranslateY(0);
        bottomBar.getChildren().addAll(lightBlueRectangle, whiteRectangle);



        // Crear un StackPane para el cuadro blanco y el muñeco
        Pane whitePane = new Pane();
        whitePane.setPadding(new Insets(10, 0, 0, 20)); // Agregar padding para mover a la derecha
        //whitePane.setAlignment(Pos.CENTER_LEFT); // Alineación a la izquierda

        // Ajustar la posición del Pane blanco para mover el muñeco a la derecha
        whitePane.setLayoutX((67 - 30) / 2); // Calcula la posición horizontal para centrar el muñeco
        whitePane.setLayoutY((67 - 30) / 2); // Calcula la posición vertical para centrar el muñeco
        // Crear el círculo para la cabeza
        Circle headCircle = new Circle(31, 15, 13); // Ajuste del tamaño del círculo de la cabeza
        headCircle.setFill(Color.BLACK);

        // Crear la elipse para el cuerpo
        Ellipse body = new Ellipse(31, 45, 25, 10); // Ajuste del tamaño y posición de la elipse del cuerpo
        body.setFill(Color.BLACK);

        // Agrupar las formas juntas
        Group person = new Group(headCircle, body);

        // Agregar el muñeco al StackPane blanco
        whitePane.getChildren().addAll(whiteRectangle, person);

        // Crear un VBox para las etiquetas
        VBox labelBox = new VBox(5); // Espaciado de 5 entre etiquetas
        labelBox.setPadding(new Insets(10, 0, 0, 10)); // Ajusta el padding según sea necesario

        Text nombreLabel = new Text("Nombre: ");
        Text academiaLabel = new Text("Nombre de la academia: ");
        Text perfilLabel = new Text("Tipo de perfil (Administrador)");

        // Establecer la fuente Times New Roman
        nombreLabel.setFont(Font.font("Times New Roman"));
        academiaLabel.setFont(Font.font("Times New Roman"));
        perfilLabel.setFont(Font.font("Times New Roman"));

        labelBox.getChildren().addAll(nombreLabel, academiaLabel, perfilLabel);

        // Crear un HBox para los botones
        HBox buttonLabelBox = new HBox(10); // Espaciado de 10 entre los bot
        Button academiaButton = new Button("Academia");
        Button usuariosButton = new Button("Usuarios");
        Button experienciasButton = new Button("Experiencias Educativas");
        Button perfilButton = new Button("Perfil");
        Button cerrarSesionButton = new Button("Cerrar Sesión");

        // Estilo de los botones para que parezcan texto
        String buttonStyle = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black; -fx-padding: 0; -fx-font-size: 14px;";

        academiaButton.setStyle(buttonStyle);
        usuariosButton.setStyle(buttonStyle);
        experienciasButton.setStyle(buttonStyle);
        perfilButton.setStyle(buttonStyle);
        cerrarSesionButton.setStyle(buttonStyle);

        // Establecer la fuente Times New Roman para los botones
        academiaButton.setFont(Font.font("Times New Roman"));
        usuariosButton.setFont(Font.font("Times New Roman"));
        experienciasButton.setFont(Font.font("Times New Roman"));
        perfilButton.setFont(Font.font("Times New Roman"));
        cerrarSesionButton.setFont(Font.font("Times New Roman"));

        // Manejar los eventos de los botones
        academiaButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Academia");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenido a academia");
            alert.showAndWait();
        });

        usuariosButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Usuarios");
            alert.setHeaderText(null);
            alert.setContentText("Los usuarios son:");
            alert.showAndWait();
        });

        experienciasButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Experiencias Educativas");
            alert.setHeaderText(null);
            alert.setContentText("Estas son las experiencias:");
            alert.showAndWait();
        });

        perfilButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Perfil");
            alert.setHeaderText(null);
            alert.setContentText("Los perfiles son:");
            alert.showAndWait();
        });

        cerrarSesionButton.setOnAction(e -> {
            System.out.println("Cerrar Sesión Button Clicked");
            LogIn loginApp = new LogIn();
            try {
                loginApp.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Agregar botones al HBox
        buttonLabelBox.getChildren().addAll(academiaButton, usuariosButton, experienciasButton, perfilButton, cerrarSesionButton);

        // Crear un HBox para alinear el cuadro blanco, las etiquetas y los botones horizontalmente
        HBox hbox = new HBox(20); // Espaciado de 20 entre el cuadro blanco, las etiquetas y los botones
        hbox.setPadding(new Insets(0, 0, 0, 10)); // Ajustar padding para mover más a la derecha
        hbox.getChildren().addAll(whitePane, labelBox, buttonLabelBox);

        // Hacer que el segundo rectángulo se extienda de izquierda a derecha
        lightBlueRectangle.widthProperty().bind(bottomBar.widthProperty());

        // Crear un VBox para alinear los elementos del bottomBar verticalmente
        VBox bottomContent = new VBox(10, hbox);
        bottomContent.setPadding(new Insets(10, 0, 0, 0)); // Ajustar padding según sea necesario

        // Agregar el VBox al bottomBar
        bottomBar.getChildren().add(bottomContent);

        // Crear un VBox para alinear los StackPanes verticalmente
        VBox vbox = new VBox(topBar, bottomBar);

        // Crear el BorderPane y configurar el VBox en el centro
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setStyle("-fx-background-color: rgba(147,221,250,0.33);"); // Establecer el color de fondo en un tono de gris medio

        // Crear la etiqueta para "Academias"
        Label academiasLabel = new Label("Academias");

        // Crear el TextField para buscar
        TextField buscarField = new TextField();
        buscarField.setPromptText("Buscar ");



        // Crear los botones Filtrar y Agregar
        Button filtrarButton = new Button("Filtrar");
        Button agregarButton = new Button("Agregar");
        Button verAcademiaButton = new Button("Vizualizar");
        Button modificarButton = new Button("Modificar");

        // Establecer estilos para los botones
        buttonStyle = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black; -fx-padding: 0; -fx-font-size: 14px;";
        filtrarButton.setStyle(buttonStyle);
        agregarButton.setStyle(buttonStyle);
        verAcademiaButton.setStyle(buttonStyle);
        modificarButton.setStyle(buttonStyle);
        filtrarButton.setFont(Font.font("Times New Roman"));
        agregarButton.setFont(Font.font("Times New Roman"));
        verAcademiaButton.setFont(Font.font("Times New Roman"));
        modificarButton.setFont(Font.font("Times New Roman"));

        // Establecer la fuente Times New Roman para los botones
        academiasLabel.setFont(Font.font("Times New Roman"));
        buscarField.setFont(Font.font("Times New Roman"));
        filtrarButton.setFont(Font.font("Times New Roman"));
        agregarButton.setFont(Font.font("Times New Roman"));
        verAcademiaButton.setFont(Font.font("Times New Roman"));
        modificarButton.setFont(Font.font("Times New Roman"));

        // Crear un HBox para los elementos de filtrar y agregar
        HBox filterAddBox = new HBox(10, academiasLabel, buscarField, filtrarButton, agregarButton);
        filterAddBox.setPadding(new Insets(10)); // Añadir un poco de espacio alrededor del HBox
        filterAddBox.setAlignment(Pos.CENTER_LEFT); // Alinear los elementos a la izquierda


        // Añadir el HBox de filtrar y agregar al VBox del bottomBar
        bottomContent.getChildren().add(filterAddBox);
        // Crear la tabla
        TableView<Academia> tabla = new TableView<>();

        // Crear las columnas
        TableColumn<Academia, String> idColumna = new TableColumn<>("IdAcademia");
        TableColumn<Academia, String> nombreColumna = new TableColumn<>("Nombre");
        TableColumn<Academia, String> ciudadColumna = new TableColumn<>("Ciudad");
        TableColumn<Academia, String> facultadColumna = new TableColumn<>("Facultad");
        TableColumn<Academia, String> areaColumna = new TableColumn<>("Área de Conocimiento");
        TableColumn<Academia, String> lineaColumna = new TableColumn<>("Línea de Investigación");
        TableColumn<Academia, Byte[]> programaColumna = new TableColumn<>("Programa Educativo");
        TableColumn<Academia, String> actividadColumna = new TableColumn<>("Actividad");

        // Asignar valores de las columnas
        // Asignar valores de las columnas
        idColumna.setCellValueFactory(new PropertyValueFactory<>("IdAcademia"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ciudadColumna.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        facultadColumna.setCellValueFactory(new PropertyValueFactory<>("facultad"));
        areaColumna.setCellValueFactory(new PropertyValueFactory<>("areaConocimiento"));
        lineaColumna.setCellValueFactory(new PropertyValueFactory<>("lineaInvestigacion"));
        programaColumna.setCellValueFactory(new PropertyValueFactory<>("programaEducativo"));
        actividadColumna.setCellValueFactory(new PropertyValueFactory<>("actividad"));

        // Agregar columnas a la tabla
        tabla.getColumns().addAll(idColumna, nombreColumna, ciudadColumna, facultadColumna, areaColumna, lineaColumna, programaColumna, actividadColumna);

        AcademiaHelper academiaHelper = new AcademiaHelper();
        List<Academia> listaAcademias = academiaHelper.obtenerListaAcademia();

        // Crear los datos de ejemplo
        // Convierte la lista de academias en una ObservableList
        ObservableList<Academia> observableListaAcademias = FXCollections.observableArrayList(listaAcademias);

        // Asigna la ObservableList a la tabla
        tabla.setItems(observableListaAcademias);

        // Quitar el relleno del VBox bottomContent
        bottomContent.setPadding(new Insets(0));


        buscarField.setPromptText("Buscar por Nombre");

        // Botón para filtrar
               filtrarButton.setOnAction(e -> {
            String nombreBuscado = buscarField.getText().trim();
            if (!nombreBuscado.isEmpty()) {
                List<Academia> academiasFiltradas = listaAcademias.stream()
                        .filter(academia -> academia.getNombre().toLowerCase().contains(nombreBuscado.toLowerCase()))
                        .collect(Collectors.toList());
                tabla.setItems(FXCollections.observableArrayList(academiasFiltradas));
            } else {
                tabla.setItems(observableListaAcademias);
            }
        });
        agregarButton.setOnAction(e -> mostrarVentanaPDFUploader());
        usuariosButton.setOnAction(e -> mostrarVentanaUsuario(primaryStage));
        experienciasButton.setOnAction(e -> mostrarVentanaEE(primaryStage));



// Quitar el relleno del HBox hbox
        hbox.setPadding(new Insets(0));

// Vincular el ancho preferido de la tabla con el ancho del contenedor bottomContent
        tabla.prefWidthProperty().bind(bottomContent.widthProperty());

        // Crear un VBox para contener la tabla
        VBox tablaBox = new VBox(tabla);
        tablaBox.setPadding(new Insets(10)); // Añadir un poco de espacio alrededor de la tabla

        // Agregar la tabla al VBox del bottomBar
        bottomContent.getChildren().add(tablaBox);

        // Crear la escena
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Sistema Gestor de Academias UV");
        primaryStage.setScene(scene);
        primaryStage.setWidth(tabla.prefWidth(0) + 735);
        //primaryStage.setResizable(false);

        primaryStage.show();
    }
    private void mostrarVentanaPDFUploader() {
        Stage pdfStage = new Stage();
        PDFUploader pdfUploader = new PDFUploader();
        pdfUploader.start(pdfStage);
    }

    private void mostrarVentanaUsuario(Stage currentStage) {
        currentStage.close();
        Stage usi = new Stage();
        UsuarioInerfaz ventanaUsuarios = new UsuarioInerfaz();
        ventanaUsuarios.start(usi);

    }
    private void mostrarVentanaEE(Stage currentStage) {
        currentStage.close();
        Stage ee = new Stage();
        InterfazEE ventanaUsuarios = new InterfazEE();
        ventanaUsuarios.start(ee);

    }

    public static void main(String[] args) {
        launch(args);
    }
}