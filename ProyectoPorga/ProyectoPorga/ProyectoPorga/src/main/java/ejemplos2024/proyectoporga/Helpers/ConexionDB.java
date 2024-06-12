package ejemplos2024.proyectoporga.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static Connection conn = null;

    protected ConexionDB() {
        // Constructor protegido para evitar instanciación
    }

    public static Connection obtenerInstancia() throws Exception {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/SGCA?user=root&password=P@ssw0rd1704");
                System.out.println("Conexión establecida correctamente.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: No se encontró el controlador de MySQL.");
                e.printStackTrace();
                throw new Exception("No se encontró el controlador de MySQL", e);
            } catch (SQLException e) {
                System.err.println("Error: No se pudo establecer la conexión con la base de datos.");
                e.printStackTrace();
                throw new Exception("No se pudo establecer la conexión con la base de datos", e);
            }
        }
        return conn;
    }
}
