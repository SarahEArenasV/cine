package co.edu.uniquindio.poo;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class AppCine {
    private static Cine cine;

    public static void main(String[] args) {
        cine = new Cine(new ArrayList<>());

        int opcion;
        do {
            String menu = """
                   Gestión del Cine
                    1. Registrar Sala
                    2. Registrar Función
                    3. Comprar Ticket
                    4. Mostrar Información de Salas
                    5. Mostrar Asientos de una Sala
                    6. Salir
                    """;
            String input = JOptionPane.showInputDialog(menu + "\nSeleccione una opción:");
            if (input == null) break; // Salir si se cancela
            try {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1 -> registrarSala();
                    case 2 -> registrarFuncion();
                    case 3 -> comprarTicket();
                    case 4 -> mostrarInformacionSalas();
                    case 5 -> mostrarAsientosDeSala();
                    case 6 -> {
                        JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                        return;
                     } 
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido para seleccionar una opción.");
            }
        } while (true);
    }

    private static void registrarSala() {
        String[] opciones = {"Sala 2D", "Sala 3D"};
        int tipoSala = JOptionPane.showOptionDialog(null, "Seleccione el tipo de sala a registrar:",
                "Registrar Sala", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        if (tipoSala == 0) {
            registrarSala2D();
        } else if (tipoSala == 1) {
            registrarSala3D();
        }
    }

    private static void registrarSala2D() {
        while (true) {
            try {
                String id = JOptionPane.showInputDialog("Ingrese el ID de la sala:");
                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El ID de la sala no puede estar vacío.");
                    return;
                }

                int filas = leerEntero("Ingrese el número de filas (mínimo 5):");
                int columnas = leerEntero("Ingrese el número de columnas (entre 8 y 20):");
                String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnología de proyección (2D):");
                String tipoSonido = JOptionPane.showInputDialog("Ingrese el tipo de sonido (estéreo/mono):");

                Sala2d sala2d = new Sala2d(id, filas, columnas, tecnologia, tipoSonido);
                cine.getSalas().add(sala2d);
                JOptionPane.showMessageDialog(null, "Sala 2D registrada exitosamente.");
                break; 
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private static void registrarSala3D() {
        while (true) {
            try {
                String id = JOptionPane.showInputDialog("Ingrese el ID de la sala:");
                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El ID de la sala no puede estar vacío.");
                    return;
                }

                int filas = leerEntero("Ingrese el número de filas (mínimo 5):");
                int columnas = leerEntero("Ingrese el número de columnas (entre 8 y 20):");
                String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnología de proyección (3D):");
                String tipoGafas = JOptionPane.showInputDialog("Ingrese el tipo de gafas (polarizadas/activas):");

                Sala3d sala3d = new Sala3d(id, filas, columnas, tecnologia, tipoGafas);
                cine.getSalas().add(sala3d);
                JOptionPane.showMessageDialog(null, "Sala 3D registrada exitosamente.");
                break; 
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private static void registrarFuncion() {
        String idSala = JOptionPane.showInputDialog("Ingrese el ID de la sala:");
        Sala sala = buscarSalaPorId(idSala);

        if (sala == null) {
            JOptionPane.showMessageDialog(null, "Sala no encontrada.");
            return;
        }

        try {
            String idFuncion = JOptionPane.showInputDialog("Ingrese el ID de la función:");
            String horario = JOptionPane.showInputDialog("Ingrese el horario de la función (HH:MM):");
            String idPelicula = JOptionPane.showInputDialog("Ingrese el ID de la película:");
            String nombrePelicula = JOptionPane.showInputDialog("Ingrese el nombre de la película:");
            int minutos = leerEntero("Ingrese la duración de la película (en minutos):");
            String director = JOptionPane.showInputDialog("Ingrese el director de la película:");

            Pelicula pelicula = new Pelicula(idPelicula, nombrePelicula, minutos, director);
            Funcion funcion = new Funcion(idFuncion, LocalTime.parse(horario), pelicula);
            sala.getListaFunciones().add(funcion);
            JOptionPane.showMessageDialog(null, "Función registrada exitosamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El horario debe estar en formato HH:MM.");
        }
    }

    private static void comprarTicket() {
        String idSala = JOptionPane.showInputDialog("Ingrese el ID de la sala:");
        Sala sala = buscarSalaPorId(idSala);

        if (sala == null) {
            JOptionPane.showMessageDialog(null, "Sala no encontrada.");
            return;
        }

        String idFuncion = JOptionPane.showInputDialog("Ingrese el ID de la función:");
        String idAsiento = JOptionPane.showInputDialog("Ingrese el ID del asiento (F#C#):");
        String idEspectador = JOptionPane.showInputDialog("Ingrese el ID del espectador:");
        String nombreEspectador = JOptionPane.showInputDialog("Ingrese el nombre del espectador:");

        if (idFuncion == null || idAsiento == null || idEspectador == null || nombreEspectador == null ||
                idFuncion.trim().isEmpty() || idAsiento.trim().isEmpty() || idEspectador.trim().isEmpty() || nombreEspectador.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return;
        }

        boolean resultado = sala.comprarTicket(idFuncion, idAsiento, idEspectador, nombreEspectador);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Ticket comprado exitosamente para el asiento " + idAsiento + ".");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo comprar el ticket. Verifique los datos.");
        }
    }

    private static void mostrarInformacionSalas() {
        StringBuilder informacion = new StringBuilder();
        for (Sala sala : cine.getSalas()) {
            informacion.append("\nSala ID: ").append(sala.getId())
                    .append("\nCapacidad Total: ").append(sala.getCapacidadTotal())
                    .append("\nTecnología de Proyección: ").append(sala.getTecnologiaProyeccion())
                    .append("\nFunciones:\n");
            for (Funcion funcion : sala.getListaFunciones()) {
                informacion.append("  - Función ID: ").append(funcion.getId())
                        .append("\n    Película: ").append(funcion.getPelicula().getNombre())
                        .append("\n    Horario: ").append(funcion.getHorario()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, informacion.toString());
    }

    private static void mostrarAsientosDeSala() {
        String idSala = JOptionPane.showInputDialog("Ingrese el ID de la sala:");
        Sala sala = buscarSalaPorId(idSala);

        if (sala == null) {
            JOptionPane.showMessageDialog(null, "Sala no encontrada.");
            return;
        }

        StringBuilder asientos = new StringBuilder("Asientos de la Sala " + sala.getId() + ":\n");
        for (Asiento asiento : sala.getListaAsientos()) {
            String estado = asiento.isOcupado() ? "Ocupado" : "Disponible";
            asientos.append("Asiento ").append(asiento.getId()).append(" - ").append(estado).append("\n");
        }
        JOptionPane.showMessageDialog(null, asientos.toString());
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                if (input == null) {
                    throw new IllegalArgumentException("Operación cancelada por el usuario.");
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                throw e; // Salir del método si el usuario cancela
            }
        }
    }

    private static Sala buscarSalaPorId(String idSala) {
        if (idSala == null || idSala.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El ID de la sala no puede estar vacío.");
            return null;
        }
        for (Sala sala : cine.getSalas()) {
            if (sala.getId().equals(idSala)) {
                return sala;
            }
        }
        return null;
    }
}