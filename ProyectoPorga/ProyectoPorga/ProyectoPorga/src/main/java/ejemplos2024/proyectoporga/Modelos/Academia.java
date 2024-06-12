package ejemplos2024.proyectoporga.Modelos;

public class Academia {
    private int IdAcademia;
    private String nombre;
    private String ciudad;
    private String facultad;
    private String areaConocimiento; // Corregido aquí
    private String lineaInvestigacion;
    private byte[] programaEducativo;
    private String actividad;

    // Constructor y otros métodos omitidos para brevedad...


    public Academia() {

    }

    public Academia(int idAcademia, String nombre, String ciudad, String facultad, String areaConocimiento, String lineaInvestigacion, byte[] programaEducativo, String actividad) {
        IdAcademia = idAcademia;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.facultad = facultad;
        this.areaConocimiento = areaConocimiento; // Corregido aquí
        this.lineaInvestigacion = lineaInvestigacion;
        this.programaEducativo = programaEducativo;
        this.actividad = actividad;
    }

    public int getIdAcademia() {
        return IdAcademia;
    }

    public void setIdAcademia(int idAcademia) {
        IdAcademia = idAcademia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocicmiento) {
        this.areaConocimiento = areaConocicmiento;
    }

    public String getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public void setLineaInvestigacion(String lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }

    public byte[] getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(byte[] programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}



