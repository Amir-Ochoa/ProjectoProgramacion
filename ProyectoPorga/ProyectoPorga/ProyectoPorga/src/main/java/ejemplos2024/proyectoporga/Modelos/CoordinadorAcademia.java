package ejemplos2024.proyectoporga.Modelos;

public class CoordinadorAcademia {
    private int ID;
    private String correo;
    private String nombre;
    private String rol;
    private String areaCoordinación;
    private boolean estado;
    private int academiaID;
    private int carrera;

    public CoordinadorAcademia() {

    }

    public CoordinadorAcademia(int ID, String correo, String nombre, String rol, String areaCoordinación, boolean estado, int academiaID, int carrera) {
        this.ID = ID;
        this.correo = correo;
        this.nombre = nombre;
        this.rol = rol;
        this.areaCoordinación = areaCoordinación;
        this.estado = estado;
        this.academiaID = academiaID;
        this.carrera = carrera;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getAreaCoordinación() {
        return areaCoordinación;
    }

    public void setAreaCoordinación(String areaCoordinación) {
        this.areaCoordinación = areaCoordinación;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getAcademiaID() {
        return academiaID;
    }

    public void setAcademiaID(int academiaID) {
        this.academiaID = academiaID;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }
}
