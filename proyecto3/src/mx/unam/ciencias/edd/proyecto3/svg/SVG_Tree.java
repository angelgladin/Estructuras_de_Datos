package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.DataStructure;

/**
 * <p>Clase que implementa {@link SVG_Graficable} la cual dada una lista de elementos hace las operaciones necesarias para
 * que el en método {@link SVG_Graficable#drawSVG} devuelva un código SVG de los diferentes tipos de árboles.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
class SVG_Tree<T extends Comparable<T>> implements SVG_Graficable {

    private ArbolBinario<T> binaryTree;
    private DataStructure dataStructure;

    private int width;
    private int height;
    private int depth;

    SVG_Tree(Lista<T> elementos, DataStructure dataStructure) {
        this.dataStructure = dataStructure;
        switch (dataStructure) {
            case COMPLETE_BINARY_TREE:
            case MIN_HEAP:
                binaryTree = new ArbolBinarioCompleto<>();
                break;
            case SORTED_BINARY_TREE:
                binaryTree = new ArbolBinarioOrdenado<>();
                break;
            case RED_BLACK_TREE:
                binaryTree = new ArbolRojinegro<>();
                break;
            case AVL_TREE:
                binaryTree = new ArbolAVL<>();
                break;
        }
        if (dataStructure == DataStructure.MIN_HEAP) {
            //Se ve un poco feo esto, ya que se iteró dos veces el tamaño de los elementos, se hizo para lidiar
            //con los Indexables ya que para un montículo deben ser indexables y con un valor.
            Lista<Indexable<T>> le = new Lista<>();
            elementos.forEach(e -> le.agrega(new Indexable<T>(e, Integer.parseInt(e.toString()))));
            MonticuloMinimo<Indexable<T>> minHeap = new MonticuloMinimo<>(le);
            minHeap.forEach(e -> binaryTree.agrega(e.getElemento()));
        } else {
            elementos.forEach(binaryTree::agrega);
        }
    }

    @Override public String drawSVG() {
        depth = binaryTree.profundidad() == 0 ? 1 : binaryTree.profundidad();
        height = 100 + depth * 100;
        width = (int) (Math.pow(2, depth) * 60);

        StringBuilder builder = new StringBuilder();
        builder.append(SVG_Util.XML_PROLOG);
        builder.append(SVG_Util.startSVGAndPutHeightWidth(height, width));
        builder.append(SVG_Util.OPEN_G_TAG);
        if (!binaryTree.esVacio()) {
            builder.append(drawLines(binaryTree.raiz(), (width / 2) / 2, width / 2, 50));
            builder.append(drawVertices(binaryTree.raiz(), (width / 2) / 2, width / 2, 50));
        }
        builder.append(SVG_Util.CLOSE_G_TAG);
        builder.append(SVG_Util.closeSVG());
        return builder.toString();
    }

    /**
     * Método que devulve un codigo SVG con los vértices del árbol.
     *
     * @param vertex           Vertice de un árbol.
     * @param decrement        Variable que se irá diviendo en cada llamada recursiva para tener el valor de "la nueva mitad".
     * @param fatherCoordinate Variable que indica la coordenada <b><i>x</i></b> del padre.
     * @param y                La coordenada <b><i>y</i></b>, en cada llamada recusiva aumentará un tamaño constante.
     * @return Un código SVG con las vértices de arbol.
     */
    private String drawVertices(VerticeArbolBinario vertex, double decrement, double fatherCoordinate, double y) {
        String s = "";
        if (vertex != null) {
            String balance = getHeightAndBalance(vertex.toString());
            s += SVG_Util.drawTreeVertex(fatherCoordinate, y, vertex.get().toString(), getVertexColor(vertex), balance != null, balance);
            if (vertex.hayIzquierdo() && vertex.hayDerecho())
                s += drawVertices(vertex.getIzquierdo(), decrement / 2, fatherCoordinate - decrement, y + 100) +
                        drawVertices(vertex.getDerecho(), decrement / 2, fatherCoordinate + decrement, y + 100);
            else if (vertex.hayIzquierdo())
                s += drawVertices(vertex.getIzquierdo(), decrement / 2, fatherCoordinate - decrement, y + 100);
            else if (vertex.hayDerecho())
                s += drawVertices(vertex.getDerecho(), decrement / 2, fatherCoordinate + decrement, y + 100);
        }
        return s;
    }

    /**
     * Método que devulve un codigo SVG con las aristas del árbol.
     *
     * @param vertex           Vertice de un árbol.
     * @param decrement        Variable que se irá diviendo en cada llamada recursiva para tener el valor de "la nueva mitad".
     * @param fatherCoordinate Variable que indica la coordenada <b><i>x</i></b> del padre.
     * @param y                La coordenada <b><i>y</i></b>, en cada llamada recusiva aumentará un tamaño constante.
     * @return Un código SVG con las aristas de arbol.
     */
    private String drawLines(VerticeArbolBinario vertex, double decrement, double fatherCoordinate, double y) {
        String s = "";
        if (vertex.hayIzquierdo() && vertex.hayDerecho())
            s += SVG_Util.drawLine(fatherCoordinate, y, fatherCoordinate - decrement, y + 100) +
                    SVG_Util.drawLine(fatherCoordinate, y, fatherCoordinate + decrement, y + 100) +
                    drawLines(vertex.getIzquierdo(), decrement / 2, fatherCoordinate - decrement, y + 100) +
                    drawLines(vertex.getDerecho(), decrement / 2, fatherCoordinate + decrement, y + 100);
        else if (vertex.hayIzquierdo())
            s += SVG_Util.drawLine(fatherCoordinate, y, fatherCoordinate - decrement, y + 100) +
                    drawLines(vertex.getIzquierdo(), decrement / 2, fatherCoordinate - decrement, y + 100);
        else if (vertex.hayDerecho())
            s += SVG_Util.drawLine(fatherCoordinate, y, fatherCoordinate + decrement, y + 100) +
                    drawLines(vertex.getDerecho(), decrement / 2, fatherCoordinate + decrement, y + 100);
        return s;
    }

    /**
     * Método que devuelve el color de un vértice.
     *
     * @param vertex El método {@link VerticeArbolBinario#toString()} que devuelve el toString() del vértice.
     * @return Color {@link SVG_Util#COLOR_RED} o {@link SVG_Util#COLOR_BLACK} en caso de ser un vertice de un
     * <b>Arbol rojinegro</b>, {@link SVG_Util#COLOR_WHITE} en otro caso.
     */
    private String getVertexColor(VerticeArbolBinario vertex) {
        String vertexColor;
        if (dataStructure == DataStructure.RED_BLACK_TREE)
            if (vertex.toString().substring(0, 1).equals("N"))
                vertexColor = SVG_Util.COLOR_BLACK;
            else
                vertexColor = SVG_Util.COLOR_RED;
        else
            vertexColor = SVG_Util.COLOR_WHITE;
        return vertexColor;
    }

    /**
     * Método que devuelve el balance y altura en caso de ser un <b>Arbol AVL</b>.
     *
     * @param s El método {@link VerticeArbolBinario#toString()} que devuelve el toString() del vértice.
     * @return El balance y altura.
     */
    private String getHeightAndBalance(String s) {
        String balance = null;
        if (dataStructure == DataStructure.AVL_TREE) {
            StringBuilder h = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--)
                if (s.charAt(i) == ' ')
                    break;
                else
                    h.append(s.charAt(i));
            balance = h.reverse().toString();
        }
        return balance;
    }

}