package ejemplos2024.proyectoporga.Modelos;

public class ExperienciaEducativa {
    private int nrc;
    private String nombre;
    private String modalidad;
    private int creditos;
    private String area;
    private int bloque;
    private String descripción;
    private int horas;

    public ExperienciaEducativa() {

    }

    public ExperienciaEducativa(int nrc, String nombre, String modalidad, int creditos, String area, int bloque, String descripción, int horas) {
        this.nrc = nrc;
        this.nombre = nombre;
        this.modalidad = modalidad;
        this.creditos = creditos;
        this.area = area;
        this.bloque = bloque;
        this.descripción = descripción;
        this.horas = horas;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
