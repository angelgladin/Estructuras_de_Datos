package mx.unam.ciencias.edd;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 *  <li>Todos los vértices son NEGROS o ROJOS.</li>
 *  <li>La raíz es NEGRA.</li>
 *  <li>Todas las hojas (<tt>null</tt>) son NEGRAS (al igual que la raíz).</li>
 *  <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 *  <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 *      mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros son autobalanceados, y por lo tanto las operaciones de
 * inserción, eliminación y búsqueda pueden realizarse en <i>O</i>(log
 * <i>n</i>).
 */
public class ArbolRojinegro<T extends Comparable<T>> extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices de árboles rojinegros. La única
     * diferencia con los vértices de árbol binario, es que tienen un campo para
     * el color del vértice.
     */
    protected class VerticeRojinegro extends ArbolBinario<T>.Vertice {

        /** El color del vértice. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeRojinegro(T elemento) {
            super(elemento);
            color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * @return una representación en cadena del vértice rojinegro.
         */
        public String toString() {
            return String.format("%s{%s}", color == Color.ROJO ? "R" : "N", elemento.toString());
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param o el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object o) {
            if (o == null || raiz == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") VerticeRojinegro vertice = (VerticeRojinegro) o;
            return raiz.get().equals(vertice.get()) && verticeRojinegro(raiz).color == vertice.color
                   && equals(verticeRojinegro(raiz.izquierdo), verticeRojinegro(vertice.izquierdo))
                   && equals(verticeRojinegro(raiz.derecho), verticeRojinegro(vertice.derecho));
        }

        private boolean equals(VerticeRojinegro a, VerticeRojinegro b) {
            //En el caso de que vertices de un nodo y ambos no tengas hijos.
            if (a == null && b == null)
                return true;
            //Si los vertices hijos son diferentes.
            else if (a != null && b == null || a == null && b != null)
                return false;
            //Compara el elemento y despues a sus hijos por izquierda y
            return a.get().equals(b.get()) && verticeRojinegro(a).color == b.color
                   && equals(verticeRojinegro(a.izquierdo), verticeRojinegro(b.izquierdo))
                   && equals(verticeRojinegro(a.derecho), verticeRojinegro(b.derecho));
        }
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del
     *         mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeRojinegro(elemento);
    }

    /**
     * Convierte el vértice (visto como instancia de {@link
     * VerticeArbolBinario}) en vértice (visto como instancia de {@link
     * VerticeRojinegro}). Método auxililar para hacer esta audición en un único
     * lugar.
     * @param vertice el vértice de árbol binario que queremos como vértice
     *                rojinegro.
     * @return el vértice recibido visto como vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeRojinegro}.
     */
    private VerticeRojinegro verticeRojinegro(VerticeArbolBinario<T> vertice) {
        VerticeRojinegro v = (VerticeRojinegro)vertice;
        return v;
    }

    /**
     * Regresa el color del vértice rojinegro.
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
        return verticeRojinegro(vertice).color;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        super.agrega(elemento);
        VerticeRojinegro vertice = verticeRojinegro(ultimoAgregado);
        vertice.color = Color.ROJO;
        rebalancea(vertice);
    }

    private void rebalancea(VerticeRojinegro vertice) {
        VerticeRojinegro padre = verticeRojinegro(vertice.padre), abuelo, tio, aux;
        /** --Caso 1---
        * El padre del vertice es nulo.
        * Coloreamos el vertice de NEGRO y terminamos. */
        if (!vertice.hayPadre()) {
            vertice.color = Color.NEGRO;
            return;
        }
        /** --Caso 2---
         * El color del padre es NEGRO.
         * Terminamos. */
        if (getColor(padre) == Color.NEGRO)
            return;
        //Nunca el abuelo sera nulo en este punto, ya cuando se agrege las primeras
        //veces o se llena por los dos lados, o esta en una linea.
        abuelo = verticeRojinegro(padre.padre);
        /** --Caso 3---
         * El tio es ROJO.
         * Coloreamos al padre y al tio de NEGRO, y al abuelo de ROJO y
         * hacemos recursion sobre el abuelo y terminamos. */
        tio = obtenerTio(padre, abuelo);
        if (tio != null && tio.color == Color.ROJO) {
            padre.color = tio.color = Color.NEGRO;
            abuelo.color = Color.ROJO;
            rebalancea(abuelo);
            return;
        }
        /** --Caso 4---
         * El vertice y su padre estan cruzados.
         * Giramos sobre el padre en su direccion. */
        if (esHijoIzquierdo(vertice) ^ esHijoIzquierdo(padre)) {
            if (esHijoIzquierdo(padre))
                giraIzquierda(padre);
            else
                giraDerecha(padre);
            //Intercambiamos el vertice con el padre, para que el vertice sea el padre.
            aux = vertice;
            vertice = padre;
            padre = aux;
        }
        //---Caso 5---
        //Coloreamos al padre de NEGRO y al abuelo de ROJO, giramos sobre el
        //abuelo en direccion contraria del vertice.
        padre.color = Color.NEGRO;
        abuelo.color = Color.ROJO;
        if (esHijoIzquierdo(vertice))
            giraDerecha(abuelo);
        else
            giraIzquierda(abuelo);
    }

    private VerticeRojinegro obtenerTio(VerticeRojinegro padre, VerticeRojinegro abuelo) {
        return esHijoIzquierdo(padre) ? verticeRojinegro(abuelo.derecho) :
               verticeRojinegro(abuelo.izquierdo);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
    }
}
