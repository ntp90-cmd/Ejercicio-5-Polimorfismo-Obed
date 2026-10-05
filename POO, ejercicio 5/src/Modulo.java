public abstract class Modulo {

    private final String id;
    private final String nombre;
    private double salud;
    private boolean activo;
    private final double costoConstruccion;

    public Modulo(String id, String nombre, double salud,
                  boolean activo, double costoConstruccion) {
        this.id = id;
        this.nombre = nombre;
        setSalud(salud);
        this.activo = activo;
        validarCantidad(costoConstruccion);
        this.costoConstruccion = costoConstruccion;
    }

    public abstract String procesarCiclo(Mision mision);

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalud() {
        return salud;
    }

    public boolean isActivo() {
        return activo;
    }

    public double getCostoConstruccion() {
        return costoConstruccion;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setSalud(double salud) {
        validarCantidad(salud);

        if (salud > 100) {
            throw new IllegalArgumentException(
                    "La salud debe estar entre 0 y 100.");
        }

        this.salud = salud;
    }

    protected static void validarCantidad(double cantidad) {
        if (!Double.isFinite(cantidad) || cantidad < 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser finita y no negativa.");
        }
    }

    protected static void validarCiclos(int ciclos) {
        if (ciclos < 0) {
            throw new IllegalArgumentException(
                    "Los ciclos no pueden ser negativos.");
        }
    }

    protected String identificar() {
        return "[" + id + "] " + nombre;
    }

    @Override
    public String toString() {
        return identificar()
                + "\nSalud: " + salud
                + "\nActivo: " + (activo ? "Sí" : "No")
                + "\nCosto de construcción: " + costoConstruccion;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Modulo)) {
            return false;
        }

        Modulo otro = (Modulo) objeto;
        return id.equals(otro.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
