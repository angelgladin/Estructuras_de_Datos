package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.exception.InvalidDataStructureException;
import mx.unam.ciencias.edd.proyecto2.exception.InvalidFormatException;
import mx.unam.ciencias.edd.proyecto2.exception.InvalidRelationException;
import mx.unam.ciencias.edd.proyecto2.svg.SVG_Factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * <p><b>Proyecto 2: Graficador de estructuras de datos.</b></p>
 * <p>Graficador de diferentes estructuras de datos a SVG</p>
 * <p>El programa consiste en dada la entrada estandar o un archivo a leer, se agregaran los elementos a una estructura
 * de datos, en caso de ser gráfica también se harán relaciones, para después mostrar el resultado en la salida estándar.</p>
 * <p>Las estructuras de datos que se pueden graficar son las siguientes:</p>
 * <ul>
 * <li>Lista</li>
 * <li>Pila</li>
 * <li>Cola</li>
 * <li>Árbol binario completo</li>
 * <li>Árbol binario ordenado</li>
 * <li>Árbol Rojinegro</li>
 * <li>Árbol AVL</li>
 * <li>Gráfica</li>
 * <li>Montículo mínimo</li>
 * </ul>
 * <p><b>Todas la estructuras de datos anteriores ya fueron enseñadas.</b></p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public class Proyecto2 {
    /** Lista a la cual la cual tendrá los elementos de cada estructura. */
    static Lista<Integer> elements = new Lista<>();
    /** Grafica a la que le serán agregadas elementos y relaciones. */
    static Grafica<Integer> graph = new Grafica<>();
    /** Enumerador para identificar las diferentes estructuras de datos. */
    static DataStructure dataStructure = DataStructure.NONE;

    public static void main(String[] args) {
        try {
            readStructureAndAddElementsAndRelations(args, args.length == 0);
        } catch (IOException | InvalidFormatException | InvalidDataStructureException | InvalidRelationException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        System.out.print(getSVG(dataStructure, elements, graph));
    }

    /**
     * Método que se encargará de saber que estructura es, meter los elementos a una lista o gráfica, para después
     * poder trabajar con ellos. Se trabajará con puras variables de clase estáticas.
     *
     * @param args          El archivo el cual se leerá. Se tomará <code>args[0]</code>.
     * @param standardInput Si no existen argumentos se tomara la entrada estandar, en otro caso, se tomará el primer
     *                      argumento el cual será el archivo a leer.
     * @throws IOException                   En caso de que haya un error en la entrada, un ejemplo sería cuando se
     *                                       da un archivo invalido o corrupto.
     * @throws InvalidFormatException        Cuando no se indique la estructura de datos con el formato correcto.
     * @throws InvalidDataStructureException Cuando se de una estructura que no existe para graficar.
     * @throws InvalidRelationException      Cuando sabemos que habrá una gráfica, de antemano se sabe que solo
     *                                       dos elementos pueden estar relacionados, será lanzada cuando
     *                                       haya 1 o mayor estricto que 2 elemento(s) a relacionar.
     * @throws IllegalArgumentException      Cuando se den relaciones simetricas o reflexivas en la gráfica.
     */
    private static void readStructureAndAddElementsAndRelations(String[] args, boolean standardInput)
            throws IOException, InvalidFormatException, InvalidDataStructureException,
                    InvalidRelationException, IllegalArgumentException {
        BufferedReader br;
        String input;
        int c = 0;

        if (standardInput)
            br = new BufferedReader(new InputStreamReader(System.in));
        else
            br = new BufferedReader(new FileReader(args[0]));

        while ((input = br.readLine()) != null) {
            if (c == 0) {
                if (input.isEmpty() || input.charAt(0) != '#')
                    throw new InvalidFormatException();
                dataStructure = DataStructure.getEnum(input.substring(2).trim());
                if (dataStructure == DataStructure.NONE)
                    throw new InvalidDataStructureException();
            }
            if (c == 1) {
                input = input + " ";
                StringBuilder a = new StringBuilder();
                for (int i = 0; i < input.length() - 1; i++) {
                    if (Character.isDigit(input.charAt(i))) {
                        a.append(input.charAt(i));
                        if (!Character.isDigit(input.charAt(i + 1))) {
                            if (dataStructure == DataStructure.GRAPH)
                                graph.agrega(Integer.parseInt(a.toString()));
                            else
                                elements.agrega(Integer.parseInt(a.toString()));
                            a = new StringBuilder();
                        }
                    }
                }
                if (dataStructure != DataStructure.GRAPH)
                    break;
            }
            if (c == 2 && dataStructure == DataStructure.GRAPH) {
                Integer a = null, b = null;
                StringBuilder str = new StringBuilder();
                int relations = 0;
                for (int i = 0; i < input.length(); i++) {
                    if (Character.isDigit(input.charAt(i))) {
                        str.append(input.charAt(i));
                    } else if (input.charAt(i) == ',') {
                        if (a == null) {
                            a = Integer.parseInt(str.toString());
                            str = new StringBuilder();
                            relations++;
                        } else {
                            throw new InvalidRelationException();
                        }
                    }
                    if (input.charAt(i) == ';' || i == input.length() - 1) {
                        relations++;
                        if (relations++ != 2)
                            throw new InvalidRelationException();
                        b = Integer.parseInt(str.toString());
                        graph.conecta(a, b);
                        a = b = null;
                        str = new StringBuilder();
                        relations = 0;
                    }
                }
                break;
            }
            c++;
        }
    }

    /**
     * Método que dada un enumerador, lista de elementos o una gráfica devulve una cadena el cual es es el código SVG,
     * el cual será mostrado en la salida estándar
     *
     * @param dataStructure Un enumerador que indicará que estructura de datos es.
     * @param elements      Los elementos que serán agregados a una estructura.
     * @param graph         La gráfica que incluye los elementos y relaciones.
     * @return Una cadena que tiene el código SVG.
     */
    private static String getSVG(DataStructure dataStructure, Lista<Integer> elements, Grafica<Integer> graph) {
        SVG_Factory<Integer> svgFactory = new SVG_Factory<>();
        switch (dataStructure) {
            case LIST:
            case QUEUE:
            case STACK:
                return svgFactory.getListStackQueue(elements, dataStructure).drawSVG();
            case COMPLETE_BINARY_TREE:
            case SORTED_BINARY_TREE:
            case RED_BLACK_TREE:
            case AVL_TREE:
            case MIN_HEAP:
                return svgFactory.getTree(elements, dataStructure).drawSVG();
            case GRAPH:
                return svgFactory.getGraph(graph).drawSVG();
            default:
                throw new IllegalArgumentException();
        }
    }

}