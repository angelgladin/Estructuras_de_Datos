package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para diccionarios (<em>hash tables</em>). Un diccionario generaliza el
 * concepto de arreglo, permitiendo (en general, dependiendo de qué tan bueno
 * sea su método para generar picadillos) agregar, eliminar, y buscar valores en
 * tiempo <i>O</i>(1) (amortizado) en cada uno de estos casos.
 */
public class Diccionario<K, V> implements Iterable<V> {

    /** Máxima carga permitida por el diccionario. */
    public static final double MAXIMA_CARGA = 0.72;

    /* Clase para las entradas del diccionario. */
    private class Entrada {

        /* La llave. */
        public K llave;
        /* El valor. */
        public V valor;

        /* Construye una nueva entrada. */
        public Entrada(K llave, V valor) {
            // Aquí va su código.
        }
    }

    /* Clase privada para iteradores de diccionarios. */
    private class Iterador implements Iterator<V> {

        /* En qué lista estamos. */
        private int indice;
        /* Iterador auxiliar. */
        private Iterator<Diccionario<K,V>.Entrada> iterador;

        /* Construye un nuevo iterador, auxiliándose de las listas del
         * diccionario. */
        public Iterador() {
            // Aquí va su código.
        }

        /* Nos dice si hay un siguiente elemento. */
        public boolean hasNext() {
            // Aquí va su código.
        }

        /* Regresa el siguiente elemento. */
        public V next() {
            // Aquí va su código.
        }

        /* No lo implementamos: siempre lanza una excepción. */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* Tamaño mínimo; decidido arbitrariamente a 2^6. */
    private static final int MIN_N = 64;

    /* Máscara para no usar módulo. */
    private int mascara;
    /* Picadillo. */
    private Picadillo<K> picadillo;
    /* Nuestro diccionario. */
    private Lista<Entrada>[] entradas;
    /* Número de valores*/
    private int elementos;

    /* Truco para crear un arreglo genérico. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private Lista<Entrada>[] nuevoArreglo(int n) {
        Lista[] arreglo = new Lista[n];
        return (Lista<Entrada>[])arreglo;
    }

    /**
     * Construye un diccionario con un tamaño inicial y picadillo
     * predeterminados.
     */
    public Diccionario() {
        // Aquí va su código.
    }

    /**
     * Construye un diccionario con un tamaño inicial definido por el usuario, y
     * un picadillo predeterminado.
     * @param tam el tamaño a utilizar.
     */
    public Diccionario(int tam) {
        // Aquí va su código.
    }

    /**
     * Construye un diccionario con un tamaño inicial predeterminado, y un
     * picadillo definido por el usuario.
     * @param picadillo el picadillo a utilizar.
     */
    public Diccionario(Picadillo<K> picadillo) {
        // Aquí va su código.
    }

    /**
     * Construye un diccionario con un tamaño inicial, y un método de picadillo
     * definidos por el usuario.
     * @param tam el tamaño del diccionario.
     * @param picadillo el picadillo a utilizar.
     */
    public Diccionario(int tam, Picadillo<K> picadillo) {
        // Aquí va su código.
    }

    /**
     * Agrega un nuevo valor al diccionario, usando la llave proporcionada. Si
     * la llave ya había sido utilizada antes para agregar un valor, el
     * diccionario reemplaza ese valor con el recibido aquí.
     * @param llave la llave para agregar el valor.
     * @param valor el valor a agregar.
     * @throws IllegalArgumentException si la llave o el valor son nulos.
     */
    public void agrega(K llave, V valor) {
        // Aquí va su código.
    }

    /**
     * Regresa el valor del diccionario asociado a la llave proporcionada.
     * @param llave la llave para buscar el valor.
     * @return el valor correspondiente a la llave.
     * @throws NoSuchElementException si la llave no está en el diccionario.
     */
    public V get(K llave) {
        // Aquí va su código.
    }

    /**
     * Nos dice si una llave se encuentra en el diccionario.
     * @param llave la llave que queremos ver si está en el diccionario.
     * @return <tt>true</tt> si la llave está en el diccionario,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(K llave) {
        // Aquí va su código.
    }

    /**
     * Elimina el valor del diccionario asociado a la llave proporcionada.
     * @param llave la llave para buscar el valor a eliminar.
     * @throws NoSuchElementException si la llave no se encuentra en
     *         el diccionario.
     */
    public void elimina(K llave) {
        // Aquí va su código.
    }

    /**
     * Regresa una lista con todas las llaves con valores asociados en el
     * diccionario. La lista no tiene ningún tipo de orden.
     * @return una lista con todas las llaves.
     */
    public Lista<K> llaves() {
        // Aquí va su código.
    }

    /**
     * Regresa una lista con todos los valores en el diccionario. La lista no
     * tiene ningún tipo de orden.
     * @return una lista con todos los valores.
     */
    public Lista<V> valores() {
        // Aquí va su código.
    }

    /**
     * Nos dice cuántas colisiones hay en el diccionario.
     * @return cuántas colisiones hay en el diccionario.
     */
    public int colisiones() {
        // Aquí va su código.
    }

    /**
     * Nos dice el máximo número de colisiones para una misma llave que tenemos
     * en el diccionario.
     * @return el máximo número de colisiones para una misma llave.
     */
    public int colisionMaxima() {
        // Aquí va su código.
    }

    /**
     * Nos dice la carga del diccionario.
     * @return la carga del diccionario.
     */
    public double carga() {
        // Aquí va su código.
    }

    /**
     * Regresa el número de entradas en el diccionario.
     * @return el número de entradas en el diccionario.
     */
    public int getElementos() {
        // Aquí va su código.
    }

    /**
     * Nos dice si el diccionario es vacío.
     * @return <code>true</code> si el diccionario es vacío, <code>false</code>
     *         en otro caso.
     */
    public boolean esVacio() {
        return elementos == 0;
    }

    /**
     * Nos dice si el diccionario es igual al objeto recibido.
     * @param o el objeto que queremos saber si es igual al diccionario.
     * @return <code>true</code> si el objeto recibido es instancia de
     *         Diccionario, y tiene las mismas llaves asociadas a los mismos
     *         valores.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Diccionario))
            return false;
        @SuppressWarnings("unchecked") Diccionario<K, V> d = (Diccionario<K, V>)o;
        // Aquí va su código.
    }

    /**
     * Regresa un iterador para iterar los valores del diccionario. El
     * diccionario se itera sin ningún orden específico.
     * @return un iterador para iterar el diccionario.
     */
    @Override public Iterator<V> iterator() {
        // Aquí va su código.
    }
}
