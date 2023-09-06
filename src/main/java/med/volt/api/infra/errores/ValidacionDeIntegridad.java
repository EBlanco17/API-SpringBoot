package med.volt.api.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String msg) {
        super(msg);
    }
}
