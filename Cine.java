package co.edu.uniquindio.poo;

import java.util.List;

public class Cine {
    private List<Sala> salas;

    public Cine(List<Sala> salas) {
        this.salas = salas;
    }

    public boolean comprarTicket(String idFuncion, String idSilla, String idEspectador, String nombreEspectador) {
        for (Sala sala : salas) {
            for (Funcion funcion : sala.getListaFunciones()) {
                if (funcion.getId().equals(idFuncion)) {
                    Asiento asiento = sala.getListaAsientos().stream()
                        .filter(a -> a.getId().equals(idSilla))
                        .findFirst()
                        .orElse(null);

                    if (asiento == null || asiento.isOcupado()) {
                        return false; 
                    }

                    Espectador espectador = new Espectador(idEspectador, nombreEspectador);
                    asiento.setEspectador(espectador);
                    asiento.setOcupado(true);
                    return true; 
                }
            }
        }
        return false;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    
}
