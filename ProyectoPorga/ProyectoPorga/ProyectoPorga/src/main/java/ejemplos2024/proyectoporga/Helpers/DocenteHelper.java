package ejemplos2024.proyectoporga.Helpers;

import ejemplos2024.proyectoporga.Modelos.Docente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocenteHelper {
    public List<Docente> obetenerListaDocente() {
        try {
            Connection conn = new ConexionDB().obtenerInstancia();
            Statement inst = conn.createStatement();

            ResultSet resultadoDocente = inst
                    .executeQuery("SELECT * from docente order by nombre");
            List<Docente> listaDocente = new ArrayList<>();
            while(resultadoDocente.next()) {
                int id = resultadoDocente.getInt("IdDocente");
                String nombre = resultadoDocente.getString("Nombre");
                String rol = resultadoDocente.getString("Rol");
                int academia = resultadoDocente.getInt("Academia");
                int carrera = resultadoDocente.getInt("Carrera");
                int ee = resultadoDocente.getInt("EE");
                boolean estado = resultadoDocente.getBoolean("Estado");


                Docente docente = new Docente(id, nombre, rol, estado, academia, carrera, ee);
                listaDocente.add(docente);
            }
            return listaDocente;



        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
