package ejemplos2024.proyectoporga.UI;

import ejemplos2024.proyectoporga.Helpers.ConexionDB;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PDFUploader extends Application {

    private static final String DB_INSERT_QUERY = "INSERT INTO Academia (nombre, ciudad, facultad, areaConocimiento, lineaInvestigación, programaEducativo, Actividad) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String[] ACTIVIDAD_OPTIONS = {"Activo", "Inactivo"};
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Subir PDF a Base de Datos");

        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();

        Label cityLabel = new Label("Ciudad:");
        TextField cityField = new TextField();

        Label facultyLabel = new Label("Facultad:");
        TextField facultyField = new TextField();

        Label areaLabel = new Label("Área de Conocimiento:");
        TextField areaField = new TextField();

        Label researchLabel = new Label("Línea de Investigación:");
        TextField researchField = new TextField();

        Label fileLabel = new Label("Seleccionar PDF:");
        TextField fileField = new TextField();
        fileField.setEditable(false);

        Button fileButton = new Button("...");
        fileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecciona un archivo PDF");
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                fileField.setText(file.getPath());
            }
        });

        Label activityLabel = new Label("Actividad:");
        ComboBox<String> activityComboBox = new ComboBox<>(FXCollections.observableArrayList(ACTIVIDAD_OPTIONS));
        activityComboBox.setValue(ACTIVIDAD_OPTIONS[0]);

        Button uploadButton = new Button("Guardar");
        uploadButton.setOnAction(e -> {
            String nombre = nameField.getText();
            String ciudad = cityField.getText();
            String facultad = facultyField.getText();
            String areaConocimiento = areaField.getText();
            String lineaInvestigacion = researchField.getText();
            String archivoPDF = fileField.getText();
            String actividad = activityComboBox.getValue();

            if (!nombre.isEmpty() && !ciudad.isEmpty() && !facultad.isEmpty() && !areaConocimiento.isEmpty() && !lineaInvestigacion.isEmpty() && !archivoPDF.isEmpty() && !actividad.isEmpty()) {
                try {
                    Connection conn = ConexionDB.obtenerInstancia();
                    try (InputStream inputStream = new FileInputStream(new File(archivoPDF))) {
                        PreparedStatement statement = conn.prepareStatement(DB_INSERT_QUERY);
                        statement.setString(1, nombre);
                        statement.setString(2, ciudad);
                        statement.setString(3, facultad);
                        statement.setString(4, areaConocimiento);
                        statement.setString(5, lineaInvestigacion);
                        statement.setBinaryStream(6, inputStream, (int) new File(archivoPDF).length());
                        statement.setString(7, actividad);
                        statement.executeUpdate();
                        System.out.println("PDF cargado correctamente en la base de datos.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Por favor completa todos los campos.");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, cityLabel, cityField);
        gridPane.addRow(2, facultyLabel, facultyField);
        gridPane.addRow(3, areaLabel, areaField);
        gridPane.addRow(4, researchLabel, researchField);
        gridPane.addRow(5, fileLabel, fileField, fileButton);
        gridPane.addRow(6, activityLabel, activityComboBox);

        VBox vBox = new VBox(10, gridPane, uploadButton);
        Scene scene = new Scene(vBox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}