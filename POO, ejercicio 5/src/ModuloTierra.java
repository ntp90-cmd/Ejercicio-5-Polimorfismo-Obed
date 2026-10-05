public class ModuloTierra extends Modulo {

    private final String estacion;
    private final double capacidadDescarga;
    private final double energiaPorDescarga;
    private double totalDescargado;

    public ModuloTierra(String id, String nombre, double salud,
                        boolean activo, double costoConstruccion,
                        String estacion, double capacidadDescarga,
                        double energiaPorDescarga,
                        double totalDescargado) {

        super(id, nombre, salud, activo, costoConstruccion);

        validarCantidad(capacidadDescarga);
        validarCantidad(energiaPorDescarga);
        validarCantidad(totalDescargado);

        this.estacion = estacion;
        this.capacidadDescarga = capacidadDescarga;
        this.energiaPorDescarga = energiaPorDescarga;
        this.totalDescargado = totalDescargado;
    }

    @Override
    public String procesarCiclo(Mision mision) {
        if (!isActivo()) {
            return identificar() + ": está inactivo.";
        }

        if (mision.getDatosEnOrbita() <= 0) {
            return identificar()
                    + ": no hay datos pendientes en órbita.";
        }

        if (capacidadDescarga == 0) {
            return identificar()
                    + ": su capacidad de descarga es cero.";
        }

        if (!mision.consumirEnergia(energiaPorDescarga)) {
            return identificar()
                    + ": no descargó por energía insuficiente.";
        }

        double descargado =
                mision.descargarDatos(capacidadDescarga);

        totalDescargado += descargado;

        return identificar()
                + ": descargó " + descargado + " MB"
                + " y consumió " + energiaPorDescarga
                + " unidades de energía.";
    }

    public String getEstacion() {
        return estacion;
    }

    public double getCapacidadDescarga() {
        return capacidadDescarga;
    }

    public double getEnergiaPorDescarga() {
        return energiaPorDescarga;
    }

    public double getTotalDescargado() {
        return totalDescargado;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nTipo: Tierra"
                + "\nEstación: " + estacion
                + "\nCapacidad por ciclo: " + capacidadDescarga
                + " MB"
                + "\nEnergía por descarga: " + energiaPorDescarga
                + "\nTotal descargado: " + totalDescargado + " MB";
    }
}