import java.util.List;
import java.util.Scanner;

public class VistaConsola {

    private final Scanner entrada;

    public VistaConsola() {
        entrada = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\nDefensa de QTZ2");
        System.out.println("1. Listar módulos");
        System.out.println("2. Buscar un módulo");
        System.out.println("3. Consultar catálogo por costo");
        System.out.println("4. Avanzar un ciclo");
        System.out.println("5. Resumen de comunicaciones");
        System.out.println("0. Salir");
    }

    public int leerOpcion() {
        while (true) {
            String texto = leerTexto("Opción: ");

            if (texto == null) {
                return 0;
            }

            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException excepcion) {
                mostrarMensaje("Ingresá una opción numérica.");
            }
        }
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);

        if (!entrada.hasNextLine()) {
            return null;
        }

        return entrada.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarModulo(Modulo modulo) {
        if (modulo == null) {
            mostrarMensaje("No se encontró el módulo.");
        } else {
            mostrarMensaje("\n" + modulo);
        }
    }

    public void mostrarModulos(List<Modulo> modulos) {
        if (modulos.isEmpty()) {
            mostrarMensaje("No se encontraron módulos.");
            return;
        }

        for (Modulo modulo : modulos) {
            mostrarModulo(modulo);
        }
    }

    public void mostrarResultados(List<String> resultados) {
        for (String resultado : resultados) {
            mostrarMensaje(resultado);
        }
    }

    public void mostrarRecursos(double energia,
                                double datosEnOrbita,
                                double descargados) {

        mostrarMensaje("\nRecursos de la misión");
        mostrarMensaje("Energía disponible: " + energia);
        mostrarMensaje("Datos en órbita: " + datosEnOrbita + " MB");
        mostrarMensaje("Datos descargados: " + descargados + " MB");
    }

    public void mostrarResumen(ResumenComunicaciones resumen) {
        mostrarMensaje("\nResumen de comunicaciones");

        mostrarMensaje("Módulos de tierra: "
                + resumen.getCantidadModulosTierra());

        mostrarMensaje("Módulos activos: "
                + resumen.getCantidadActivos());

        mostrarMensaje("Capacidad total activa por ciclo: "
                + resumen.getCapacidadTotalActiva() + " MB");

        mostrarMensaje("Total histórico descargado: "
                + resumen.getTotalHistoricoDescargado() + " MB");

        List<ModuloTierra> mejores =
                resumen.getMayoresDescargadores();

        if (mejores.isEmpty()) {
            mostrarMensaje("No hay módulos de tierra.");
            return;
        }

        mostrarMensaje("\nMódulos con mayor descarga histórica");

        for (ModuloTierra modulo : mejores) {
            mostrarMensaje(
                    "Nombre: " + modulo.getNombre()
                    + " | ID: " + modulo.getId()
                    + " | Estación: " + modulo.getEstacion()
                    + " | Descargado: "
                    + modulo.getTotalDescargado() + " MB");
        }
    }
}