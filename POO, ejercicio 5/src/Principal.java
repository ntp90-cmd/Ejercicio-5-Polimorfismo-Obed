public class Principal {

    public static void main(String[] args) {
        Mision mision = new Mision();
        mision.cargarInicial();

        VistaConsola vista = new VistaConsola();

        Controlador controlador =
                new Controlador(mision, vista);

        controlador.iniciar();
    }
}