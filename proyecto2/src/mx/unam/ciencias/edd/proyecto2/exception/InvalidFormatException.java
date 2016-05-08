package mx.unam.ciencias.edd.proyecto2.exception;

/**
 * Clase para excepciones de formato invalido.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public class InvalidFormatException extends RuntimeException {

    static final String INVALID_FORMAT_MSG = "El formato es inválido :(";

    public InvalidFormatException() {
        super(INVALID_FORMAT_MSG);
    }

}