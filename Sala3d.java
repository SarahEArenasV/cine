package co.edu.uniquindio.poo;
import java.util.ArrayList;
import java.util.List;

public class Sala3d extends Sala {
    private String tipoGafas; // Ejemplo polarizadas y activas
    private List<Asiento> filaVip;

    public Sala3d(String id, int filas, int columnas, String tecnologiaProyeccion, String tipoGafas) {
        super(id, filas, columnas, tecnologiaProyeccion);
        this.tipoGafas = tipoGafas;
        this.filaVip = new ArrayList<>();
        inicializarFilaVip(columnas);
    }

    private void inicializarFilaVip(int columnas) {
        int filaVipNumero = 1; // la rpimera fiila es vip
        for (int j = 1; j <= columnas; j++) {
            String idAsientoVip = "F" + filaVipNumero + "C" + j;
            filaVip.add(new Asiento(idAsientoVip, filaVipNumero, j));
        }
    }

    public String getTipoGafas() {
        return tipoGafas;
    }

    public void setTipoGafas(String tipoGafas) {
        this.tipoGafas = tipoGafas;
    }

    public List<Asiento> getFilaVip() {
        return filaVip;
    }

    public void setFilaVip(List<Asiento> filaVip) {
        this.filaVip = filaVip;
    }
    
}