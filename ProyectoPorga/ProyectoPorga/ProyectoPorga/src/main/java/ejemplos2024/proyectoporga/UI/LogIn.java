package ejemplos2024.proyectoporga.UI;

import ejemplos2024.proyectoporga.Helpers.UsuarioHelper;
import ejemplos2024.proyectoporga.Modelos.Academia;
import ejemplos2024.proyectoporga.Modelos.Administrador;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LogIn extends Application{

    private Stage loginStage;

    public void start(Stage primaryStage) {
        loginStage = primaryStage;
        // Crear el StackPane para contener los elementos
        StackPane stackPane = new StackPane();

        // Crear el fondo con degradado y agregarlo al StackPane
        Canvas gradientBackground = drawGradientBackground(1000, 1000);
        stackPane.getChildren().add(gradientBackground);

        // Crear la etiqueta para el título "Sistema gestor de academia UV"
        Label titleUVLabel = new Label("Sistema gestor de academia UV");
        titleUVLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        titleUVLabel.setTextFill(Color.WHITE);

        // Crear un HBox para contener la etiqueta y centrarla
        HBox hbox = new HBox(titleUVLabel);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.setPadding(new Insets(20));

        // Agregar la etiqueta al StackPane
        stackPane.getChildren().add(hbox);

        // Crear un rectángulo blanco para contener el VBox
        Rectangle rectangle = new Rectangle(400, 400);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);

        // Crear el VBox para los elementos de inicio de sesión
        VBox vbox = createLoginForm();

        // Alinear el VBox en el centro del rectángulo
        StackPane.setAlignment(vbox, Pos.CENTER);

        // Agregar el rectángulo y el VBox al StackPane
        stackPane.getChildren().addAll(rectangle, vbox);

        // Establecer la escena
        Scene scene = new Scene(stackPane, 600, 600); // Aumentamos la altura de la ventana
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.setResizable(false); // Evitar que la ventana sea redimensionable
        primaryStage.show();
    }

    // Método para dibujar el fondo con degradado
    private Canvas drawGradientBackground(double width, double height) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Definir los colores para el degradado (azul y verde más fuertes)
        Stop[] stops = new Stop[]{
                new Stop(0.0, Color.rgb(0, 100, 200)), // Azul más fuerte (arriba)
                new Stop(1.0, Color.rgb(100, 200, 100)) // Verde más fuerte (abajo)
        };

        // Crear el degradado lineal desde arriba hacia abajo
        LinearGradient gradient = new LinearGradient(0, 0, 0, height, false, CycleMethod.NO_CYCLE, stops);

        // Rellenar el fondo con el degradado
        gc.setFill(gradient);
        gc.fillRect(0, 0, width, height);

        return canvas;
    }

    // Método para crear el VBox con el formulario de inicio de sesión
    private VBox createLoginForm() {
        // Crear el VBox
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));

        // Crear el título
        Label titleLabel = new Label("Inicio de Sesión");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLACK);

        // Crear los elementos de inicio de sesión
        Label usernameLabel = new Label("Usuario:");
        TextField usernameInput = new TextField();
        usernameLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
        usernameLabel.setTextFill(Color.BLACK);
        usernameInput.setStyle("-fx-background-color: #f2f2f2;");
        usernameInput.setMaxWidth(150); // Establecer el ancho máximo
        usernameInput.setAlignment(Pos.CENTER_LEFT); // Alinear el texto a la izquierda

        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordInput = new PasswordField();
        passwordLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
        passwordLabel.setTextFill(Color.BLACK);
        passwordInput.setStyle("-fx-background-color: #f2f2f2;");
        passwordInput.setMaxWidth(150); // Establecer el ancho máximo
        passwordInput.setAlignment(Pos.CENTER_LEFT); // Alinear el texto a la izquierda

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        loginButton.setPrefWidth(120);
        loginButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));

        loginButton.setOnAction(evt -> {
            String sCorreo = usernameInput.getText().toString();
            String sPassword = passwordInput.getText().toString();
            UsuarioHelper uh = new UsuarioHelper();
            var us = uh.validarUsuario(sCorreo, sPassword);



            if (us != null) {
                if (us.getRol().equals("Administrador")) {
                    // Si el usuario es un administrador, muestra la ventana de administrador
                    try {
                        loginStage.close(); // Ocultar la ventana de inicio de sesión
                        // Lógica para mostrar la ventana de administrador
                        InterfazAcademia ventanaPrincipal = new InterfazAcademia();

                        // Mostrar la nueva ventana
                        ventanaPrincipal.start(new Stage());

                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else if ("Coordinador".equals(us.getRol())) {
                    // Si el usuario es un coordinador, muestra la ventana de coordinador
                    try {
                        loginStage.close(); // Ocultar la ventana de inicio de sesión
                        // Lógica para mostrar la ventana de coordinador
                        InterfazAcademiaCoordinador ventanaPrincipal = new InterfazAcademiaCoordinador();

                        // Mostrar la nueva ventana
                        ventanaPrincipal.start(new Stage());
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // Si el rol no coincide con "administrador" ni "coordinador", muestra un mensaje de error
                    System.out.println("Usuario no autorizado");
                }
            } else {
                // Si el usuario no es válido, muestra un mensaje de error
                System.out.println("Correo o contraseña incorrectos");
            }
        });

        // Agregar elementos al VBox
        vbox.getChildren().addAll(titleLabel, usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton);

        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
