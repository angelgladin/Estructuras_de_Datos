package mx.unam.ciencias.edd.proyecto2.excepciones;


public class RelacionInvalida extends IllegalArgumentException{
	
	static final String RELACION_INVALIDA = "La relacion de la grafica es invalida :(";

	public RelacionInvalida() {
		super(RELACION_INVALIDA);
	}

}