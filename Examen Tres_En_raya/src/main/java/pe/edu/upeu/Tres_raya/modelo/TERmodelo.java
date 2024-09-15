package pe.edu.upeu.Tres_raya.modelo;

public class TERmodelo {
    public String nombrePartida;
    private String nombreJugador1;
    private String nombreJugador2;
    private String ganador;
    private int puntuacion;
    private String estado;

    public TERmodelo(String nombrePartida, String nombreJugador1, String nombreJugador2, String ganador, int puntuacion, String estado) {
        this.nombrePartida = nombrePartida;
        this.nombreJugador1 = nombreJugador1;
        this.nombreJugador2 = nombreJugador2;
        this.ganador = ganador;
        this.puntuacion = puntuacion;
        this.estado = estado;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNumeroPartida(String numeroPartida) {
        this.nombrePartida = nombrePartida;
    }

    public String getNombreJugador1() {
        return nombreJugador1;
    }

    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1 = nombreJugador1;
    }

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2 = nombreJugador2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "TERmodelo{" +
                "nombrePartida='" + nombrePartida + '\'' +
                ", nombreJugador1='" + nombreJugador1 + '\'' +
                ", nombreJugador2='" + nombreJugador2 + '\'' +
                ", ganador='" + ganador + '\'' +
                ", puntuacion=" + puntuacion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
