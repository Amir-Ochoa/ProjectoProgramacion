module ejemplos2024.proyectoporga {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens ejemplos2024.proyectoporga to javafx.fxml;
    exports ejemplos2024.proyectoporga.Modelos to javafx.fxml;
    exports ejemplos2024.proyectoporga.UI;
    opens ejemplos2024.proyectoporga.Modelos to javafx.base;
    opens ejemplos2024.proyectoporga.UI to javafx.base, javafx.fxml;
}