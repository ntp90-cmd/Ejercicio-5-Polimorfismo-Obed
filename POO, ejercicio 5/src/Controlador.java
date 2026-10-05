import java.util.List;

public class Controlador {

    private final Mision modelo;
    private final VistaConsola vista;

    public Controlador(Mision modelo, VistaConsola vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMensaje(
                "Misión cargada. Ciclos transcurridos: "
                        + modelo.getCicloActual());

        vista.mostrarRecursos(
                modelo.getEnergiaDisponible(),
                modelo.getDatosEnOrbita(),
                modelo.getTotalDescargado());

        boolean continuar = true;

        while (continuar) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            switch (opcion) {
                case 1:
                    vista.mostrarModulos(modelo.listarModulos());
                    break;

                case 2:
                    gestionarBusqueda();
                    break;

                case 3:
                    vista.mostrarMensaje(
                            "\nCatálogo de menor a mayor costo");

                    vista.mostrarModulos(
                            modelo.obtenerCatalogoOrdenado());
                    break;

                case 4:
                    gestionarCiclo();
                    break;

                case 5:
                    vista.mostrarResumen(
                            modelo.generarResumenComunicaciones());
                    break;

                case 0:
                    continuar = false;
                    vista.mostrarMensaje("Simulación finalizada.");
                    break;

                default:
                    vista.mostrarMensaje(
                            "Opción inválida. Elegí entre 0 y 5.");
            }
        }
    }

    private void gestionarBusqueda() {
        vista.mostrarMensaje("\n1. Buscar por ID");
        vista.mostrarMensaje("2. Buscar por nombre completo");
        vista.mostrarMensaje("0. Volver");

        int tipo = vista.leerOpcion();

        if (tipo == 0) {
            return;
        }

        if (tipo != 1 && tipo != 2) {
            vista.mostrarMensaje("Tipo de búsqueda inválido.");
            return;
        }

        String criterio = vista.leerTexto(
                tipo == 1 ? "ID: " : "Nombre completo: ");

        if (criterio == null) {
            return;
        }

        if (criterio.isEmpty()) {
            vista.mostrarMensaje("Ingresá un criterio de búsqueda.");
            return;
        }

        if (tipo == 1) {
            vista.mostrarModulo(modelo.buscarPorId(criterio));
        } else {
            vista.mostrarModulos(modelo.buscarPorNombre(criterio));
        }
    }

    private void gestionarCiclo() {
        List<String> resultados = modelo.avanzarCiclo();

        vista.mostrarMensaje(
                "\nCiclo " + modelo.getCicloActual());

        vista.mostrarResultados(resultados);

        vista.mostrarRecursos(
                modelo.getEnergiaDisponible(),
                modelo.getDatosEnOrbita(),
                modelo.getTotalDescargado());
    }
}