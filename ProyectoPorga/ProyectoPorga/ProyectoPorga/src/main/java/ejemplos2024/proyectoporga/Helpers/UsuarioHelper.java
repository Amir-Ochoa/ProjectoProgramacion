package ejemplos2024.proyectoporga.Helpers;

import ejemplos2024.proyectoporga.Modelos.Academia;
import ejemplos2024.proyectoporga.Modelos.Administrador;
import ejemplos2024.proyectoporga.Modelos.Usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioHelper {
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

    public Usuario validarUsuario(String correo, String password) {
        try {
            // Imprimir el correo y la contraseña antes del hash
            System.out.println("Correo antes de procesar: " + correo);
            System.out.println("Contraseña antes de hash: " + password);

            var md5 = MessageDigest.getInstance("MD5");
            byte[] digest = password.getBytes(StandardCharsets.UTF_8);
            byte[] hashb = md5.digest(digest);
            password = bytesToHex(hashb).toLowerCase();

            // Imprimir el correo y la contraseña después del hash
            System.out.println("Correo después de procesar: " + correo);
            System.out.println("Contraseña después del hash: " + password);

            correo = correo.replaceAll("'", "").replaceAll(" ", "");

            Connection conn = ConexionDB.obtenerInstancia();

            String instSQL = String.format("SELECT * FROM Usuario WHERE correo='%s' AND contraseña='%s'", correo, password);

            // Imprimir la consulta SQL
            System.out.println("Consulta SQL: " + instSQL);

            Statement inst = conn.createStatement();
            ResultSet resul = inst.executeQuery(instSQL);
            Usuario user = null;
            if (resul.next()) {
                user = new Usuario();
                user.setCorreo(resul.getString("Correo"));
                user.setRol(resul.getString("Rol"));
            } else {
                System.out.println("Usuario no encontrado");
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> obtenerListaUsuario() { // Corregido aquí
        try {
            Connection conn = new ConexionDB().obtenerInstancia();
            Statement inst = conn.createStatement();

            ResultSet resultadoUsuario = inst.executeQuery("SELECT * from usuario order by rol");
            List<Usuario> listaUsuario = new ArrayList<>();
            while (resultadoUsuario.next()) {

                String correo = resultadoUsuario.getString("Correo");
                String rol = resultadoUsuario.getString("Rol");

                Usuario us = new Usuario(correo, rol);
                listaUsuario.add(us);
            }
            return listaUsuario;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}



