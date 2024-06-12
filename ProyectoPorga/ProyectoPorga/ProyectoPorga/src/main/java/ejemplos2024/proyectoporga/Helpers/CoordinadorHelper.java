package ejemplos2024.proyectoporga.Helpers;

import ejemplos2024.proyectoporga.Modelos.Administrador;
import ejemplos2024.proyectoporga.Modelos.CoordinadorAcademia;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CoordinadorHelper {
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    public CoordinadorAcademia validarCoordinador(String correo, String password) {
        try {
            var md5 = MessageDigest.getInstance("MD5");
            byte[] digest = password.getBytes(StandardCharsets.UTF_8);
            byte[] hashb = md5.digest(digest);
            password = bytesToHex(hashb).toLowerCase();


            correo = correo.replaceAll("'", "").replaceAll(" ", "");

            Connection conn = ConexionDB.obtenerInstancia();

            String instSQL = String.format("SELECT * FROM Administrador WHERE correo='%s' AND contraseña='%s'", correo, password);

            // Imprimir la consulta SQL
            System.out.println("Consulta SQL: " + instSQL);

            Statement inst = conn.createStatement();
            ResultSet resul = inst.executeQuery(instSQL);
            CoordinadorAcademia ca = null;
            if (resul.next()) {
                ca = new CoordinadorAcademia();
                ca.setCorreo(resul.getString("Correo"));
                ca.setNombre(resul.getString("Nombre"));
                ca.setRol(resul.getString("Rol"));
            } else {
                System.out.println("Usuario no encontrado");
            }
            return ca;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
