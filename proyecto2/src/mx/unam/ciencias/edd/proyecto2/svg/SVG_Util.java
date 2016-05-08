package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.proyecto2.DataStructure;

/**
 * <p>Clase que tiene solamente constantes y métodos estáticos necesarios para generar los SVG.</p>
 * <p>Dentro de este existen metodos y constantes estaticas por lo que no es necesario instanciar la clase.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public class SVG_Util {

    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";
    public static final String DOUBLE_TAB = "\t\t";
    public static final String TRIPLE_TAB = "\t\t\t";
    public static final String DOUBLE_QUOTE = "\"";

    public static final String XML_PROLOG = "<?xml version='1.0' encoding='UTF-8' ?>";

    public static final String OPEN_G_TAG = String.format("%s%s<g>", NEW_LINE, TAB);
    public static final String CLOSE_G_TAG = String.format("%s</g>", TAB);

    public static final String OPEN_DEFS_TAG = "<defs>";
    public static final String CLOSE_DEFS_TAG = "</defs>";

    public static final String ID_DEF_ARROW = "arrow";

    public static final String FONT_SANS_SERIF = "sans-serif";
    public static final String FONT_TEXT_ANCHOR = "middle";
    public static final int FONT_SIZE_TEXT = 16;

    public static final int FONT_SIZE_ARROW = 21;
    public static final String TEXT_LEFT_RIGHT_ARROW = "↔";
    public static final String TEXT_RIGHTWARD_ARROW = "→";

    public static final int VERTEX_RADIUS = 25;
    public static final int VERTEX_FONT_SIZE = 25;

    public static final int GRAPH_WIDTH_HEIGHT = 1000;

    public static final int FONT_BALANCE_SIZE_TEXT = 20;

    public static final int STOKE_WIDTH = 2;
    public static final int STOKE_LINE_WIDTH = 2;

    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";
    public static final String COLOR_RED = "red";
    public static final String COLOR_BLUE = "blue";

    public static String startSVGAndPutHeightWidth(int height, int width) {
        return String.format("<svg height=%s%d%s width=%s%d%s>%s",
                DOUBLE_QUOTE, height, DOUBLE_QUOTE,
                DOUBLE_QUOTE, width, DOUBLE_QUOTE,
                NEW_LINE);
    }

    public static String closeSVG() {
        return "</svg>";
    }

    public static String openG_TagWithId(String id) {
        return String.format("<g id=%s%s%s>%s",
                DOUBLE_QUOTE, id, DOUBLE_QUOTE,
                NEW_LINE);
    }

    public static String drawArrow(String arrow) {
        return String.format("<text " +
                        "x=%s%d%s " +
                        "y=%s%d%s " +
                        "fill=%s%s%s " +
                        "font-family=%s%s%s " +
                        "font-size=%s%d%s " +
                        "text-anchor=%s%s%s" +
                        ">" +
                        "%s" +
                        "</text>",
                DOUBLE_QUOTE, 0, DOUBLE_QUOTE,
                DOUBLE_QUOTE, 0, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_BLACK, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_SANS_SERIF, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_SIZE_ARROW, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_TEXT_ANCHOR, DOUBLE_QUOTE,
                arrow);
    }

    public static String drawLine(double x1, double y1, double x2, double y2) {
        return String.format("<line " +
                        "stroke=%s%s%s " +
                        "stroke-width=%s%d%s " +
                        "x1=%s%f%s " +
                        "y1=%s%f%s " +
                        "x2=%s%f%s " +
                        "y2=%s%f%s " +
                        "/>",
                DOUBLE_QUOTE, COLOR_BLACK, DOUBLE_QUOTE,
                DOUBLE_QUOTE, STOKE_LINE_WIDTH, DOUBLE_QUOTE,
                DOUBLE_QUOTE, x1, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y1, DOUBLE_QUOTE,
                DOUBLE_QUOTE, x2, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y2, DOUBLE_QUOTE);
    }

    public static String drawGraphVertex(double x, double y, String text) {
        final String CIRCLE_TAG = String.format("%s%s<circle " +
                        "cx=%s%f%s " +
                        "cy=%s%f%s " +
                        "r=%s%d%s " +
                        "fill=%s%s%s " +
                        "stroke=%s%s%s " +
                        "stroke-width=%s%d%s" +
                        "/>",
                NEW_LINE, DOUBLE_TAB,
                DOUBLE_QUOTE, x, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y, DOUBLE_QUOTE,
                DOUBLE_QUOTE, VERTEX_RADIUS, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_WHITE, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_BLACK, DOUBLE_QUOTE,
                DOUBLE_QUOTE, STOKE_WIDTH, DOUBLE_QUOTE);

        final String TEXT_TAG = String.format("%s%s<text " +
                        "x=%s%f%s " +
                        "y=%s%f%s " +
                        "fill=%s%s%s " +
                        "font-family=%s%s%s " +
                        "font-size=%s%d%s " +
                        "text-anchor=%s%s%s" +
                        ">" +
                        "%s" +
                        "</text>",
                NEW_LINE, DOUBLE_TAB,
                DOUBLE_QUOTE, x, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y + 8, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_BLACK, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_SANS_SERIF, DOUBLE_QUOTE,
                DOUBLE_QUOTE, VERTEX_FONT_SIZE, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_TEXT_ANCHOR, DOUBLE_QUOTE,
                text);

        return CIRCLE_TAG + TEXT_TAG;
    }

    public static String drawTreeVertex(double x, double y, String text, String vertexColor, boolean avlTree, String balanceHeight) {
        final String CIRCLE_TAG = String.format("%s%s<circle " +
                        "cx=%s%f%s " +
                        "cy=%s%f%s " +
                        "r=%s%d%s " +
                        "fill=%s%s%s " +
                        "stroke=%s%s%s " +
                        "stroke-width=%s%d%s" +
                        "/>",
                NEW_LINE, DOUBLE_TAB,
                DOUBLE_QUOTE, x, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y, DOUBLE_QUOTE,
                DOUBLE_QUOTE, VERTEX_RADIUS, DOUBLE_QUOTE,
                DOUBLE_QUOTE, vertexColor, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_BLACK, DOUBLE_QUOTE,
                DOUBLE_QUOTE, STOKE_WIDTH, DOUBLE_QUOTE);

        final String TEXT_TAG = String.format("%s%s<text " +
                        "x=%s%f%s " +
                        "y=%s%f%s " +
                        "fill=%s%s%s " +
                        "font-family=%s%s%s " +
                        "font-size=%s%d%s " +
                        "text-anchor=%s%s%s" +
                        ">" +
                        "%s" +
                        "</text>",
                NEW_LINE, DOUBLE_TAB,
                DOUBLE_QUOTE, x, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y + 8, DOUBLE_QUOTE,
                DOUBLE_QUOTE, vertexColor.equals(COLOR_WHITE) ? COLOR_BLACK : COLOR_WHITE, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_SANS_SERIF, DOUBLE_QUOTE,
                DOUBLE_QUOTE, VERTEX_FONT_SIZE, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_TEXT_ANCHOR, DOUBLE_QUOTE,
                text);

        final String BALANCE_AND_DEPTH_TEXT_TAG = String.format("%s%s<text " +
                        "x=%s%f%s " +
                        "y=%s%f%s " +
                        "fill=%s%s%s " +
                        "font-family=%s%s%s " +
                        "font-size=%s%d%s " +
                        "text-anchor=%s%s%s" +
                        ">" +
                        "%s" +
                        "</text>",
                NEW_LINE, DOUBLE_TAB,
                DOUBLE_QUOTE, x + 35, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y - 15, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_BLUE, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_SANS_SERIF, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_BALANCE_SIZE_TEXT, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_TEXT_ANCHOR, DOUBLE_QUOTE,
                balanceHeight);

        return CIRCLE_TAG + TEXT_TAG + (avlTree ? BALANCE_AND_DEPTH_TEXT_TAG : "") + NEW_LINE;
    }

    public static String drawSquare(int x, int y, int height, int width, String text, DataStructure dataStructure) {
        final String RECT_TAG = String.format("%s%s<rect " +
                        "x=%s%d%s " +
                        "y=%s%d%s " +
                        "height=%s%d%s " +
                        "width=%s%d%s " +
                        "fill=%s%s%s " +
                        "stroke=%s%s%s " +
                        "stroke-width=%s%d%s" +
                        "/>",
                NEW_LINE, DOUBLE_TAB,
                DOUBLE_QUOTE, x, DOUBLE_QUOTE,
                DOUBLE_QUOTE, y, DOUBLE_QUOTE,
                DOUBLE_QUOTE, height, DOUBLE_QUOTE,
                DOUBLE_QUOTE, width, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_WHITE, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_BLACK, DOUBLE_QUOTE,
                DOUBLE_QUOTE, STOKE_WIDTH, DOUBLE_QUOTE);

        final String TEXT_TAG = String.format("%s%s<text " +
                        "x=%s%d%s " +
                        "y=%s%d%s " +
                        "fill=%s%s%s " +
                        "font-family=%s%s%s " +
                        "font-size=%s%d%s " +
                        "text-anchor=%s%s%s" +
                        ">" +
                        "%s" +
                        "</text>",
                NEW_LINE, DOUBLE_TAB,
                DOUBLE_QUOTE, dataStructure != DataStructure.STACK ? x + 30 : 40, DOUBLE_QUOTE,
                DOUBLE_QUOTE, dataStructure != DataStructure.STACK ? 35 : y + 25, DOUBLE_QUOTE,
                DOUBLE_QUOTE, COLOR_BLACK, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_SANS_SERIF, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_SIZE_TEXT, DOUBLE_QUOTE,
                DOUBLE_QUOTE, FONT_TEXT_ANCHOR, DOUBLE_QUOTE,
                text);

        return OPEN_G_TAG + RECT_TAG + TEXT_TAG + NEW_LINE + CLOSE_G_TAG;
    }

    public static class Defs {
        public static String createDefs(String id, String inside) {
            return String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s",
                    TAB, OPEN_DEFS_TAG,
                    NEW_LINE, DOUBLE_TAB, openG_TagWithId(id),
                    TRIPLE_TAB,
                    inside,
                    NEW_LINE, TAB, CLOSE_G_TAG,
                    NEW_LINE,
                    TAB,
                    CLOSE_DEFS_TAG);
        }

        public static String createUseTag(String id, int x, int y) {
            return String.format("%s<use xlink:href=%s#%s%s " +
                            "x=%s%d%s y=%s%d%s />",
                    TAB,
                    DOUBLE_QUOTE, id, DOUBLE_QUOTE,
                    DOUBLE_QUOTE, x, DOUBLE_QUOTE,
                    DOUBLE_QUOTE, y, DOUBLE_QUOTE);
        }
    }

}