import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mision {

    private final List<Modulo> modulos;
    private double energiaDisponible;
    private double datosEnOrbita;
    private double totalDescargado;
    private int cicloActual;

    public Mision() {
        modulos = new ArrayList<>();
    }

    public void cargarInicial() {
        modulos.clear();

        modulos.add(new ModuloEnergia(
                "E01", "Panel principal", 100, true,
                800, 40, 5));

        modulos.add(new ModuloVuelo(
                "V01", "Cámara terrestre", 95, true,
                1500, "Cámara", 15, 8, 4));

        modulos.add(new ModuloTierra(
                "T01", "Antena central", 100, true,
                1000, "Campus Central", 10, 5, 40));

        modulos.add(new ModuloEnergia(
                "E02", "Panel auxiliar", 90, true,
                600, 25, 4));

        modulos.add(new ModuloVuelo(
                "V02", "Sensor térmico", 90, true,
                1200, "Sensor", 10, 6, 4));

        modulos.add(new ModuloTierra(
                "T02", "Antena norte", 95, true,
                900, "Campus Norte", 15, 4, 30));

        modulos.add(new ModuloVuelo(
                "V03", "Cámara de respaldo", 70, false,
                1400, "Cámara", 20, 10, 4));

        modulos.add(new ModuloEnergia(
                "E03", "Panel de respaldo", 75, false,
                500, 15, 4));

        modulos.add(new ModuloTierra(
                "T03", "Antena de respaldo", 80, false,
                700, "Campus Central", 10, 3, 20));

        modulos.add(new ModuloTierra(
                "T04", "Antena sur", 98, true,
                950, "Campus Sur", 12, 4, 30));

        cicloActual = 5;
        energiaDisponible = 214;

        totalDescargado = generarResumenComunicaciones()
                .getTotalHistoricoDescargado();

        double totalRecolectado = 0;

        for (Modulo modulo : modulos) {
            if (modulo instanceof ModuloVuelo) {
                ModuloVuelo vuelo = (ModuloVuelo) modulo;
                totalRecolectado += vuelo.getTotalRecolectado();
            }
        }

        datosEnOrbita = totalRecolectado - totalDescargado;
    }

    public List<Modulo> listarModulos() {
        return new ArrayList<>(modulos);
    }

    public Modulo buscarPorId(String id) {
        for (Modulo modulo : modulos) {
            if (modulo.getId().equalsIgnoreCase(id.trim())) {
                return modulo;
            }
        }

        return null;
    }

    public List<Modulo> buscarPorNombre(String nombre) {
        List<Modulo> encontrados = new ArrayList<>();

        for (Modulo modulo : modulos) {
            if (modulo.getNombre()
                    .equalsIgnoreCase(nombre.trim())) {
                encontrados.add(modulo);
            }
        }

        return encontrados;
    }

    public List<Modulo> obtenerCatalogoOrdenado() {
        List<Modulo> copia = new ArrayList<>(modulos);

        copia.sort(
                Comparator.comparingDouble(
                        Modulo::getCostoConstruccion));

        return copia;
    }

    public List<String> avanzarCiclo() {
        cicloActual++;

        List<String> resultados = new ArrayList<>();

        for (Modulo modulo : modulos) {
            resultados.add(modulo.procesarCiclo(this));
        }

        return resultados;
    }

    public ResumenComunicaciones generarResumenComunicaciones() {
        int cantidad = 0;
        int activos = 0;

        double capacidad = 0;
        double historico = 0;
        double mayor = -1;

        List<ModuloTierra> mejores = new ArrayList<>();

        for (Modulo modulo : modulos) {
            if (modulo instanceof ModuloTierra) {
                ModuloTierra tierra = (ModuloTierra) modulo;

                cantidad++;

                if (tierra.isActivo()) {
                    activos++;
                    capacidad += tierra.getCapacidadDescarga();
                }

                double descargado = tierra.getTotalDescargado();
                historico += descargado;

                if (descargado > mayor) {
                    mayor = descargado;
                    mejores.clear();
                    mejores.add(tierra);
                } else if (Double.compare(descargado, mayor) == 0) {
                    mejores.add(tierra);
                }
            }
        }

        return new ResumenComunicaciones(
                cantidad,
                activos,
                capacidad,
                historico,
                mejores);
    }

    public boolean consumirEnergia(double cantidad) {
        Modulo.validarCantidad(cantidad);

        if (energiaDisponible < cantidad) {
            return false;
        }

        energiaDisponible -= cantidad;
        return true;
    }

    public void agregarEnergia(double cantidad) {
        Modulo.validarCantidad(cantidad);
        energiaDisponible += cantidad;
    }

    public void agregarDatos(double cantidad) {
        Modulo.validarCantidad(cantidad);
        datosEnOrbita += cantidad;
    }

    public double descargarDatos(double capacidad) {
        Modulo.validarCantidad(capacidad);

        double cantidad = Math.min(capacidad, datosEnOrbita);

        datosEnOrbita -= cantidad;
        totalDescargado += cantidad;

        return cantidad;
    }

    public double getEnergiaDisponible() {
        return energiaDisponible;
    }

    public double getDatosEnOrbita() {
        return datosEnOrbita;
    }

    public double getTotalDescargado() {
        return totalDescargado;
    }

    public int getCicloActual() {
        return cicloActual;
    }
}