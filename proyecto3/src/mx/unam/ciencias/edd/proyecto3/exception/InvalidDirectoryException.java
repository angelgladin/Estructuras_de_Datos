package mx.unam.ciencias.edd.proyecto3.exception;

import java.io.IOException;

/**
 * Excepción que será lanzada cuando haya algo extraño relacionado con el directorio.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class InvalidDirectoryException extends IOException {
    static final String INVALID_DIRECTORY_MSG = "No se recibieron argumentos al ejecutar el prograna :(";

    public InvalidDirectoryException() {
        super(INVALID_DIRECTORY_MSG);
    }
}
