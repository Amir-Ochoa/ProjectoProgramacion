package ejemplos2024.proyectoporga.Modelos;

public class JefeCarrera {
    private int ID;
    private String nombre;
    private String rol;
    private boolean estado;
    private int carrera;

    public JefeCarrera() {

    }

    public JefeCarrera(int ID, String nombre, String rol, boolean estado, int carrera) {
        this.ID = ID;
        this.nombre = nombre;
        this.rol = rol;
        this.estado = estado;
        this.carrera = carrera;
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

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }
}
