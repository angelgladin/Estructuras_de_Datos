package mx.unam.ciencias.edd.proyecto3.exception;

/**
 * @author Angel Gladin
 * @version 1.0
 * @since 19/05/2016.
 */
public class NoArgumentsGivenException extends IllegalArgumentException {
    static final String NO_ARGUMENTS_GIVEN_MSG = "No se recibieron argumentos al ejecutar el prograna :(";

    public NoArgumentsGivenException() {
        super(NO_ARGUMENTS_GIVEN_MSG);
    }
}
