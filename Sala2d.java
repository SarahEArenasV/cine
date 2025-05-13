package co.edu.uniquindio.poo;


public class Sala2d extends Sala {
    private String tipoSonido; 

    public Sala2d(String id, int filas, int columnas, String tecnologiaProyeccion, String tipoSonido) {
        super(id, filas, columnas, tecnologiaProyeccion);
        this.tipoSonido = tipoSonido;
    }

    public String getTipoSonido() {
        return tipoSonido;
    }

    public void setTipoSonido(String tipoSonido) {
        this.tipoSonido = tipoSonido;
    }
}
