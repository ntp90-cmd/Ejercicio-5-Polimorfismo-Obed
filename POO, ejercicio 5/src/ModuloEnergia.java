public class ModuloEnergia extends Modulo {

    private final double energiaPorCiclo;
    private int ciclosAcumulados;

    public ModuloEnergia(String id, String nombre, double salud,
                         boolean activo, double costoConstruccion,
                         double energiaPorCiclo,
                         int ciclosAcumulados) {

        super(id, nombre, salud, activo, costoConstruccion);

        validarCantidad(energiaPorCiclo);
        validarCiclos(ciclosAcumulados);

        this.energiaPorCiclo = energiaPorCiclo;
        this.ciclosAcumulados = ciclosAcumulados;
    }

    @Override
    public String procesarCiclo(Mision mision) {
        if (!isActivo()) {
            return identificar() + ": está inactivo.";
        }

        mision.agregarEnergia(energiaPorCiclo);
        ciclosAcumulados++;

        return identificar()
                + ": generó " + energiaPorCiclo
                + " unidades de energía.";
    }

    public double getEnergiaPorCiclo() {
        return energiaPorCiclo;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public double getTotalGenerado() {
        return energiaPorCiclo * ciclosAcumulados;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nTipo: Energía"
                + "\nEnergía por ciclo: " + energiaPorCiclo
                + "\nCiclos efectivos: " + ciclosAcumulados
                + "\nTotal generado: " + getTotalGenerado();
    }
}