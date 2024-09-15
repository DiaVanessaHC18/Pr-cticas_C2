package pe.edu.upeu.Tres_raya.service.servicio;
import org.springframework.stereotype.Service;
import pe.edu.upeu.Tres_raya.modelo.TERmodelo;
import java.util.ArrayList;
import java.util.List;
@Service
public class TERservicioImp implements Interface_juego {
    public List<TERmodelo> lismo=new ArrayList<>();
    @Override
    public void guardarResultados(TERmodelo to) {
        lismo.add(to);
    }

    @Override
    public List<TERmodelo> obtenerResultados() {
        return lismo;
    }

    @Override
    public void actualizarResultados(TERmodelo to, int index) {
        lismo.set(index ,to);
    }

    @Override
    public void eliminarResultados(int index) {
        lismo.remove(index);
    }
}
