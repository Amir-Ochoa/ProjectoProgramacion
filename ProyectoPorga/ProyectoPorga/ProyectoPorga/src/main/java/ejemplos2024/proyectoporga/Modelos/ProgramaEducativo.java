package ejemplos2024.proyectoporga.Modelos;

public class ProgramaEducativo {
    private int codigoPE;
    private String descripción;
    private String modalidad;
    private String facultad;

    public ProgramaEducativo() {
    }

    public ProgramaEducativo(int codigoPE, String descripción, String modalidad, String facultad) {
        this.codigoPE = codigoPE;
        this.descripción = descripción;
        this.modalidad = modalidad;
        this.facultad = facultad;
    }

    public int getCodigoPE() {
        return codigoPE;
    }

    public void setCodigoPE(int codigoPE) {
        this.codigoPE = codigoPE;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
