package ejemplos2024.proyectoporga.Helpers;

import ejemplos2024.proyectoporga.Modelos.Academia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcademiaHelper {
    public List<Academia> obtenerListaAcademia() { // Corregido aquí
        try {
            Connection conn = new ConexionDB().obtenerInstancia();
            Statement inst = conn.createStatement();

            ResultSet resultadoAcademia = inst.executeQuery("SELECT * from academia order by nombre");
            List<Academia> listaAcademia = new ArrayList<>();
            while (resultadoAcademia.next()) {
                int id = resultadoAcademia.getInt("IdAcademia");
                String nombre = resultadoAcademia.getString("Nombre");
                String ciudad = resultadoAcademia.getString("Ciudad");
                String facultad = resultadoAcademia.getString("Facultad");
                String areaConocimiento = resultadoAcademia.getString("AreaConocimiento");
                String lineaConocimiento = resultadoAcademia.getString("LineaInvestigación");
                byte[] programaEducativo = resultadoAcademia.getBytes("ProgramaEducativo");
                String actividad = resultadoAcademia.getString("Actividad");
                Academia academia = new Academia(id, nombre, ciudad, facultad, areaConocimiento, lineaConocimiento, programaEducativo, actividad);
                listaAcademia.add(academia);
            }
            return listaAcademia;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean crearAcademia(Academia academia) {
        try {
            Connection conn = ConexionDB.obtenerInstancia();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO academia (Nombre, Ciudad, Facultad, AreaConocimiento, LineaInvestigación, ProgramaEducativo, Actividad) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setString(1, academia.getNombre());
            stmt.setString(2, academia.getCiudad());
            stmt.setString(3, academia.getFacultad());
            stmt.setString(4, academia.getAreaConocimiento()); // Corregido aquí
            stmt.setString(5, academia.getLineaInvestigacion());
            stmt.setBytes(6, academia.getProgramaEducativo());
            stmt.setString(7, academia.getActividad());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarAcademia(Academia academia) {
        try {
            Connection conn = ConexionDB.obtenerInstancia();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE academia SET Nombre=?, Ciudad=?, Facultad=?, AreaConocimiento=?, LineaInvestigación=?, ProgramaEducativo=?, Actividad=? WHERE IdAcademia=?"
            );
            stmt.setString(1, academia.getNombre());
            stmt.setString(2, academia.getCiudad());
            stmt.setString(3, academia.getFacultad());
            stmt.setString(4, academia.getAreaConocimiento()); // Corregido aquí
            stmt.setString(5, academia.getLineaInvestigacion());
            stmt.setBytes(6, academia.getProgramaEducativo());
            stmt.setString(7, academia.getActividad());
            stmt.setInt(8, academia.getIdAcademia());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
