package mx.unam.ciencias.edd.proyecto2.exception;

/**
 * Clase para excepciones de relaciones inválidas en gráficas.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public class InvalidRelationException extends RuntimeException{
	
	static final String INVALID_RELATION_MSG = "La relación de la grafica es inválida :(";

	public InvalidRelationException() {
		super(INVALID_RELATION_MSG);
	}

}