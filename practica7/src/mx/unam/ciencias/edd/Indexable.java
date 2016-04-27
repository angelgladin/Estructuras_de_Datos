package mx.unam.ciencias.edd;

/**
 * Clase para poder utilizar comparables indexables.
 */
public class Indexable<T> implements ComparableIndexable<Indexable<T>> {

    /* El elemento */
    private T elemento;
    /* Su valor */
    private double valor;
    /* Su índice. */
    private int indice;

    /**
     * Crea un nuevo indexable con el elemento y valor dados.
     * @param elemento el elemento.
     * @param valor su valor.
     */
    public Indexable(T elemento, double valor) {
        // Aquí va su código.
    }

    /**
     * Regresa el elemento del indexable.
     * @return el elemento del indexable.
     */
    public T getElemento() {
        // Aquí va su código.
    }

    /**
     * Compara el indexable con otro indexable.
     * @param indexable el indexable.
     * @return un valor menor que cero si el indexable que llama el método es
     *         menor que el parámetro; cero si son iguales; o mayor que cero si
     *         es mayor.
     */
    @Override public int compareTo(Indexable<T> indexable) {
        // Aquí va su código.
    }

    /**
     * Define el índice del indexable.
     * @param indice el nuevo índice.
     */
    @Override public void setIndice(int indice) {
        // Aquí va su código.
    }

    /**
     * Regresa el índice del indexable.
     * @return el índice del indexable.
     */
    @Override public int getIndice() {
        // Aquí va su código.
    }

    /**
     * Define el valor del indexable.
     * @param valor el nuevo valor.
     */
    public void setValor(double valor) {
        // Aquí va su código.
    }

    /**
     * Regresa el valor del indexable.
     * @return el valor del indexable.
     */
    public double getValor() {
        // Aquí va su código.
    }
}
