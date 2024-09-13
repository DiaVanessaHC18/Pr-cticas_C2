package org.example.juego.modelo;

public class modeloJuego {
    private String nombrePartida;
    private String nombreJugador1;
    private String nombreJugador2;
    private String ganador;
    private int punto;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2 = nombreJugador2;
    }

    public String getNombreJugador1() {
        return nombreJugador1;
    }

    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1 = nombreJugador1;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    @Override
    public String toString() {
        return "modeloJuego{" +
                "nombrePartida='" + nombrePartida + '\'' +
                ", nombreJugador1='" + nombreJugador1 + '\'' +
                ", nombreJugador2='" + nombreJugador2 + '\'' +
                ", ganador='" + ganador + '\'' +
                ", punto=" + punto +
                ", estado='" + estado + '\'' +
                '}';
    }

    public void setPuntuacion(int x) {
    }
}
