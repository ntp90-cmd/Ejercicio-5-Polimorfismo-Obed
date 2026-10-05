import java.util.ArrayList;
import java.util.List;

public class ResumenComunicaciones {

    private final int cantidadModulosTierra;
    private final int cantidadActivos;
    private final double capacidadTotalActiva;
    private final double totalHistoricoDescargado;
    private final List<ModuloTierra> mayoresDescargadores;

    public ResumenComunicaciones(
            int cantidadModulosTierra,
            int cantidadActivos,
            double capacidadTotalActiva,
            double totalHistoricoDescargado,
            List<ModuloTierra> mayoresDescargadores) {

        this.cantidadModulosTierra = cantidadModulosTierra;
        this.cantidadActivos = cantidadActivos;
        this.capacidadTotalActiva = capacidadTotalActiva;
        this.totalHistoricoDescargado = totalHistoricoDescargado;
        this.mayoresDescargadores =
                new ArrayList<>(mayoresDescargadores);
    }

    public int getCantidadModulosTierra() {
        return cantidadModulosTierra;
    }

    public int getCantidadActivos() {
        return cantidadActivos;
    }

    public double getCapacidadTotalActiva() {
        return capacidadTotalActiva;
    }

    public double getTotalHistoricoDescargado() {
        return totalHistoricoDescargado;
    }

    public List<ModuloTierra> getMayoresDescargadores() {
        return new ArrayList<>(mayoresDescargadores);
    }
}