package mx.unam.ciencias.edd.proyecto2.exception;

/**
 * Clase para excepciones de estructuras de datos invalidas.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public class InvalidDataStructureException extends RuntimeException {

    static final String INVALID_DATA_STRUCTURE_MSG = "La estructura de datos es inválida :(";

    public InvalidDataStructureException() {
        super(INVALID_DATA_STRUCTURE_MSG);
    }

}