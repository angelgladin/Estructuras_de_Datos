package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.DataStructure;
import mx.unam.ciencias.edd.proyecto3.pojo.WordEntry;

/**
 * <p>Se utilizó el patrón de Diseño <b>Factory Pattern</b> (el cual es un patrón de creación) para evitar
 * que se estén creando objetos directamente de las clases, de esta forma la creación de objetos
 * es mas sencilla y solamente se pueden mandar a llamar métodos de la interfaz {@link SvgGraphicable}.</p>
 * <a href="https://en.wikipedia.org/wiki/Factory_method_pattern">Consulte aquí</a>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class SvgFactory {
    /**
     * Método que regresa el código SVG de una lista, pila o cola.
     *
     * @param elements      Una lista de elementos.
     * @param dataStructure Una estructura de datos para indicar que estructura es.
     * @param <T>           El genérico que se usará.
     * @return Un codigo svg de una lista, pila o cola.
     */
    public static <T> SvgGraphicable getListStackQueue(Lista<T> elements, DataStructure dataStructure) {
        return new SvgListStackQueue<>(elements, dataStructure);
    }

    /**
     * Método que regresa el código SVG de un árbol.
     *
     * @param elements      Una lista de elementos.
     * @param dataStructure Una estructura de datos para indicar que estructura es.
     * @param <T>           El genérico que se usará acotado por la interfaz {@link Comparable}.
     * @return Un codigo svg que representa un árbol.
     */
    public static <T extends Comparable<T>>
        SvgGraphicable getTree(Lista<T> elements, DataStructure dataStructure) {
        return new SvgTree<>(elements, dataStructure);
    }

    /**
     * Método que regresa el código SVG de una gráfica.
     *
     * @param graph Grafica de los elementos.
     * @param <T>   El genérico que se usará.
     * @return Un codigo svg que representa una gráfica.
     */
    public static <T> SvgGraphicable getGraph(Grafica<T> graph) {
        return new SvgGraph<>(graph);
    }

    /**
     * Método que regresa el código SVG de una gráfica de pastel.
     *
     * @param entryList Una lista de {@link WordEntry}.
     * @param total     El número total de elementos.
     * @return Un codigo svg que representa una gráfica de pastel.
     */
    public static SvgGraphicable getPieChart(Lista<WordEntry> entryList, int total) {
        return new SvgPieChart(entryList, total);
    }

}
