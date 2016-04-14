package mx.unam.ciencias.edd.proyecto2.excepciones;


public class FormatoInvalidoException extends IllegalArgumentException{

	static final String FORMATO_INVALIDO = "El formato es invalido :(";

	public FormatoInvalidoException() {
		super(FORMATO_INVALIDO);
	}

}