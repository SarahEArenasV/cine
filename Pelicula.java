package co.edu.uniquindio.poo;

public class Pelicula {
    private String id;
    private String nombre;
    private int minutos;
    private String director;

    public Pelicula(String id, String nombre, int minutos, String director) {
        this.id = id;
        this.nombre = nombre;
        this.minutos = minutos;
        this.director = director;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
