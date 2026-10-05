public class ModuloVuelo extends Modulo {

    private final String tipoInstrumento;
    private final double datosPorCiclo;
    private final double consumoEnergia;
    private int ciclosAcumulados;

    public ModuloVuelo(String id, String nombre, double salud,
                       boolean activo, double costoConstruccion,
                       String tipoInstrumento, double datosPorCiclo,
                       double consumoEnergia, int ciclosAcumulados) {

        super(id, nombre, salud, activo, costoConstruccion);

        if (!"Cámara".equalsIgnoreCase(tipoInstrumento)
                && !"Camara".equalsIgnoreCase(tipoInstrumento)
                && !"Sensor".equalsIgnoreCase(tipoInstrumento)) {
            throw new IllegalArgumentException(
                    "El instrumento debe ser Cámara o Sensor.");
        }

        validarCantidad(datosPorCiclo);
        validarCantidad(consumoEnergia);
        validarCiclos(ciclosAcumulados);

        this.tipoInstrumento = tipoInstrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.consumoEnergia = consumoEnergia;
        this.ciclosAcumulados = ciclosAcumulados;
    }

    @Override
    public String procesarCiclo(Mision mision) {
        if (!isActivo()) {
            return identificar() + ": está inactivo.";
        }

        if (!mision.consumirEnergia(consumoEnergia)) {
            return identificar()
                    + ": no recolectó por energía insuficiente.";
        }

        mision.agregarDatos(datosPorCiclo);
        ciclosAcumulados++;

        return identificar()
                + ": recolectó " + datosPorCiclo + " MB"
                + " y consumió " + consumoEnergia
                + " unidades de energía.";
    }

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public double getDatosPorCiclo() {
        return datosPorCiclo;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public double getTotalRecolectado() {
        return datosPorCiclo * ciclosAcumulados;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nTipo: Vuelo"
                + "\nInstrumento: " + tipoInstrumento
                + "\nDatos por ciclo: " + datosPorCiclo + " MB"
                + "\nConsumo por ciclo: " + consumoEnergia
                + "\nCiclos efectivos: " + ciclosAcumulados
                + "\nTotal recolectado: " + getTotalRecolectado()
                + " MB";
    }
}