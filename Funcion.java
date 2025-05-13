package co.edu.uniquindio.poo;

import java.time.LocalTime;

public class Funcion {
    private String id;
    private LocalTime horario;
    private Pelicula pelicula;

    public Funcion(String id, LocalTime horario, Pelicula pelicula) {
        this.id = id;
        this.horario = horario;
        this.pelicula = pelicula;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
