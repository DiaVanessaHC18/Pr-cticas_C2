package pe.edu.upeu.Tres_raya.service.servicio;
import pe.edu.upeu.Tres_raya.modelo.TERmodelo;
import java.util.List;

public interface Interface_juego {
    public void guardarResultados(TERmodelo to);//C
    public List<TERmodelo> obtenerResultados();//R
    public void actualizarResultados(TERmodelo to, int index);//U
    public void eliminarResultados(int index);//D



}
