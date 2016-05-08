package mx.unam.ciencias.edd.proyecto2;

/**
 * <p>Enumeracion de diferentes tipos de estructuras de datos.</p>
 * <p><strong>Ademas Canek dice que cuando se usan enumeradores hace que tú y tu codigo se vean cool.</strong></p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public enum DataStructure {
    LIST("Lista"),
    STACK("Pila"),
    QUEUE("Cola"),
    COMPLETE_BINARY_TREE("ArbolBinarioCompleto"),
    SORTED_BINARY_TREE("ArbolBinarioOrdenado"),
    RED_BLACK_TREE("ArbolRojinegro"),
    AVL_TREE("ArbolAVL"),
    GRAPH("Grafica"),
    MIN_HEAP("MonticuloMinimo"),
    NONE("");

    private String ds;

    DataStructure(String ds) {
        this.ds = ds;
    }

    String getString() {
        return this.ds;
    }

    /**
     * Metodo que dado un String, busca si este esta en el enumerdor y devuelve un enumerador con una estructura de datos.
     *
     * @param s Cadena a comprobar.
     * @return Un enumerador que tiene la estructura de datos.
     */
    public static DataStructure getEnum(String s) {
        for (DataStructure e : DataStructure.values())
            if (e.getString().equals(s))
                return e;
        return NONE;
    }
}