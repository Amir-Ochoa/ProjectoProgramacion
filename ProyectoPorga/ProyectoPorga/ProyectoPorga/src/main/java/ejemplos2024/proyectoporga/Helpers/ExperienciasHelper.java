package ejemplos2024.proyectoporga.Helpers;

import ejemplos2024.proyectoporga.Modelos.Academia;
import ejemplos2024.proyectoporga.Modelos.ExperienciaEducativa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExperienciasHelper {
    public List<ExperienciaEducativa> obtenerListaAcademia() { // Corregido aquí
        try {
            Connection conn = new ConexionDB().obtenerInstancia();
            Statement inst = conn.createStatement();

            ResultSet resultadoEE = inst.executeQuery("SELECT * from experiencia_educativa order by nombre");
            List<ExperienciaEducativa> listaEE = new ArrayList<>();
            while (resultadoEE.next()) {
                int id = resultadoEE.getInt("NRC");
                String nombre = resultadoEE.getString("Nombre");
                String modalidad = resultadoEE.getString("Modalidad");
                int creditos = resultadoEE.getInt("Creditos");
                String area = resultadoEE.getString("Area");
                int bloque = resultadoEE.getInt("Bloque");
                String descripcion = resultadoEE.getString("Descripcion");
                int horas = resultadoEE.getInt("Horas");
                ExperienciaEducativa ee = new ExperienciaEducativa(id, nombre, modalidad, creditos, area, bloque, descripcion, horas);
                listaEE.add(ee);
            }
            return listaEE;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
