package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.DataStructure;
import mx.unam.ciencias.edd.proyecto3.pojo.WordEntry;

/**
 * <p>Se utilizó el patrón de Diseño <b>Factory Pattern</b> (el cual es un patrón de creación) para evitar
 * que se estén creando objetos directamente de las clases, de esta forma la creación de objetos
 * es mas sencilla y solamente se pueden mandar a llamar métodos de la interfaz {@link SVG_Graficable}.</p>
 * <a href="https://en.wikipedia.org/wiki/Factory_method_pattern">Consulte aquí</a>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public class SVG_Factory<T extends Comparable<T>> {

    public SVG_Factory() {}

    public SVG_Graficable getListStackQueue(Lista<T> elements, DataStructure dataStructure) {
        return new SVG_List_Stack_Queue<>(elements, dataStructure);
    }

    public SVG_Graficable getTree(Lista<T> elements, DataStructure dataStructure) {
        return new SVG_Tree<>(elements, dataStructure);
    }

    public SVG_Graficable getGraph(Grafica<T> graph) {
        return new SVG_Graph<>(graph);
    }

    public SVG_Graficable getPieChart(Lista<WordEntry> wordEntryList){
        return new SVG_PieChart(wordEntryList);
    }

}