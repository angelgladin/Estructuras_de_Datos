package mx.unam.ciencias.edd.proyecto3.exception;

/**
 * Excepción que será lanzada cuando no haya argumentos a la hora de ejecutar el programa.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class NoArgumentsGivenException extends IllegalArgumentException {
    static final String NO_ARGUMENTS_GIVEN_MSG = "No se recibieron argumentos al ejecutar el prograna :(";

    public NoArgumentsGivenException() {
        super(NO_ARGUMENTS_GIVEN_MSG);
    }
}
