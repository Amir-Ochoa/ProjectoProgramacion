package ejemplos2024.proyectoporga.Modelos;

public class Docente {
    private int ID;
    private String nombre;
    private String rol;
    private boolean estado;
    private int academiaID;
    private int carrera;
    private int ee;

    public Docente() {

    }

    public Docente(int ID, String nombre, String rol, boolean estado, int academiaID, int carrera, int ee) {
        this.ID = ID;
        this.nombre = nombre;
        this.rol = rol;
        this.estado = estado;
        this.academiaID = academiaID;
        this.carrera = carrera;
        this.ee = ee;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getEe() {
        return ee;
    }

    public void setEe(int ee) {
        this.ee = ee;
    }
}
