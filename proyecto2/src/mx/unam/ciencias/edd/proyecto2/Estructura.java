package mx.unam.ciencias.edd.proyecto2;

public enum Estructura {
	LISTA("Lista"),
	PILA("Pila"),
	COLA("Cola"),
	ARBOL_BINARIO_COMPLETO("ArbolBinarioCompleto"),
	ARBOL_BINARIO_ORDENADO("ArbolBinarioOrdenado"),
	ARBOL_ROJINEGRO("ArbolRojinegro"),
	ARBOL_AVL("ArbolAVL"),
	GRAFICA("Grafica"),
	NINGUNA("");

	private String estr;

	Estructura(String estr) {
		this.estr = estr;
	}

	String obtenerString() {
		return this.estr;
	}

	public static Estructura estaEnEnum(String s) {
		for (Estructura e : Estructura.values())
			if (e.obtenerString().equals(s))
				return e;
		return null;
	}
}