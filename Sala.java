package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String id;
    private int capacidadTotal;
    private String tecnologiaProyeccion;
    private List<Asiento> listaAsientos;
    private List<Funcion> listaFunciones;

    public Sala(String id, int filas, int columnas, String tecnologiaProyeccion) {
        validarFilasYColumnas(filas, columnas); 
        this.id = id;
        this.capacidadTotal = filas * columnas;
        this.tecnologiaProyeccion = tecnologiaProyeccion;
        this.listaAsientos = new ArrayList<>();
        this.listaFunciones = new ArrayList<>();
        inicializarAsientos(filas, columnas);
    }

    // metodo par validar filas y columnas y calcular su total
        public static void validarFilasYColumnas(int filas, int columnas) {
        if (filas < 5) {
            throw new IllegalArgumentException("El número de filas debe ser al menos 5.");
        }
        if (columnas < 8 || columnas > 20) {
            throw new IllegalArgumentException("El número de columnas debe estar entre 8 y 20.");
        }
    }

    //punto 2  parcial inicializar asientos

    private void inicializarAsientos(int filas, int columnas) {
        for (int i = 1; i <= filas; i++) {
            for (int j = 1; j <= columnas; j++) {
                String idAsiento = "F" + i + "C" + j;
                listaAsientos.add(new Asiento(idAsiento, i, j));
            }
        }
    }


    // punto 3 parcial metodo para comprar un ticket 

public boolean comprarTicket(String idFuncion, String idAsiento, String idEspectador, String nombreEspectador) {
    // Buscar la función por ID
    Funcion funcion = null;
    for (Funcion f : listaFunciones) {
        if (f.getId().equals(idFuncion)) {
            funcion = f;
            break;
        }
    }

    if (funcion == null) {
        System.out.println("Función no encontrada.");
        return false; 
    }

    // Buscar el asiento por su id
    Asiento asiento = null;
    for (Asiento a : listaAsientos) {
        if (a.getId().equals(idAsiento)) {
            asiento = a;
            break;
        }
    }

    if (asiento == null) {
        System.out.println("Asiento no encontrado.");
        return false; 
    }

    if (asiento.isOcupado()) {
        System.out.println("El asiento ya está ocupado.");
        return false; 
    }

    // Asignar el espectador al asiento
    Espectador espectador = new Espectador(idEspectador, nombreEspectador);
    asiento.setEspectador(espectador);
    asiento.setOcupado(true);
    System.out.println("Ticket comprado exitosamente para el asiento " + idAsiento);
    return true; 
}


// correcion parcial : metodo para asiento disponible/ocupado
    public void mostrarAsientos() {
        System.out.println("Asientos de la Sala " + id + ":");
        for (Asiento asiento : listaAsientos) {
            String estado = asiento.isOcupado() ? "Ocupado" : "Disponible";
            System.out.println("Asiento " + asiento.getId() + " - " + estado);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public String getTecnologiaProyeccion() {
        return tecnologiaProyeccion;
    }

    public void setTecnologiaProyeccion(String tecnologiaProyeccion) {
        this.tecnologiaProyeccion = tecnologiaProyeccion;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<Asiento> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }

    public List<Funcion> getListaFunciones() {
        return listaFunciones;
    }

    public void setListaFunciones(List<Funcion> listaFunciones) {
        this.listaFunciones = listaFunciones;
    }

}
