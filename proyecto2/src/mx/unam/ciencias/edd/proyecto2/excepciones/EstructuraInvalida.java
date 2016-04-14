package mx.unam.ciencias.edd.proyecto2.excepciones;


public class EstructuraInvalida extends IllegalArgumentException{
	
	static final String ESTRUCTURA_INVALIDA = "La estructura es invalida :(";

	public EstructuraInvalida() {
		super(ESTRUCTURA_INVALIDA);
	}

}