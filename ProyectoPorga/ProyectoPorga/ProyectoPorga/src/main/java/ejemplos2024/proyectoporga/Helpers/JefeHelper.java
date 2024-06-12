package ejemplos2024.proyectoporga.Helpers;

import ejemplos2024.proyectoporga.Modelos.Docente;
import ejemplos2024.proyectoporga.Modelos.JefeCarrera;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JefeHelper {
    public List<JefeCarrera> obetenerListaDocente() {
        try {
            Connection conn = new ConexionDB().obtenerInstancia();
            Statement inst = conn.createStatement();

            ResultSet resultadoJefe = inst
                    .executeQuery("SELECT * from jefe_carrera order by nombre");
            List<JefeCarrera> listaJefe = new ArrayList<>();
            while(resultadoJefe.next()) {
                int id = resultadoJefe.getInt("IdDocente");
                String nombre = resultadoJefe.getString("Nombre");
                String rol = resultadoJefe.getString("Rol");
                boolean estado = resultadoJefe.getBoolean("Estado");
                int carrera = resultadoJefe.getInt("Carrera");


                JefeCarrera jfc = new JefeCarrera(id, nombre, rol, estado, carrera);
                listaJefe.add(jfc);
            }
            return listaJefe;



        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
