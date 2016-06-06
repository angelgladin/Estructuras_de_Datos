package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.proyecto3.DataStructure;

/**
 * <p>Clase que tiene solamente constantes y métodos estáticos necesarios para generar los SVG.</p>
 * <p>Dentro de este existen metodos y constantes estaticas por lo que no es necesario instanciar la clase.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
class SVG_Util {

    static final String XML_PROLOG = "<?xml version='1.0' encoding='UTF-8' ?>\n";

    static final String OPEN_G_TAG = "<g>\n";
    static final String CLOSE_G_TAG = "</g>\n";
    static final String OPEN_DEFS_TAG = "<defs>\n";
    static final String CLOSE_DEFS_TAG = "</defs>\n";

    static final String ID_DEF_ARROW = "arrow";

    static final String FONT_SANS_SERIF = "sans-serif";
    static final String FONT_TEXT_ANCHOR = "middle";
    static final int FONT_SIZE_TEXT = 16;

    static final int FONT_SIZE_ARROW = 21;
    static final String TEXT_LEFT_RIGHT_ARROW = "↔";
    static final String TEXT_RIGHTWARD_ARROW = "→";

    static final int VERTEX_RADIUS = 25;
    static final int VERTEX_FONT_SIZE = 11;

    static final int GRAPH_WIDTH_HEIGHT = 1000;

    static final int FONT_BALANCE_SIZE_TEXT = 20;

    static final int STOKE_WIDTH = 2;
    static final int STOKE_LINE_WIDTH = 2;

    static final String COLOR_WHITE = "white";
    static final String COLOR_BLACK = "black";
    static final String COLOR_RED = "red";
    static final String COLOR_BLUE = "blue";

    static String startSVGAndPutHeightWidth(int height, int width) {
        return String.format("<svg height='%d' width='%d'>\n", height, width);
    }

    static String closeSVG() {
        return "</svg>\n";
    }

    static String openG_TagWithId(String id) {
        return String.format("<g id='%s'>\n", id);
    }

    static String drawArrow(String arrow) {
        return String.format("<text x='%d' y='%d' fill='%s' font-family='%s' font-size='%d' " +
                        "text-anchor='%s'>%s</text>\n",
                0,
                0,
                COLOR_BLACK,
                FONT_SANS_SERIF,
                FONT_SIZE_ARROW,
                FONT_TEXT_ANCHOR,
                arrow);
    }

    static String drawLine(double x1, double y1, double x2, double y2) {
        return String.format("<line stroke='%s' stroke-width='%d' " +
                        "x1='%f' y1='%f' x2='%f' y2='%f'/>\n",
                COLOR_BLACK,
                STOKE_LINE_WIDTH,
                x1, y1,
                x2, y2);
    }

    static String drawGraphVertex(double x, double y, String text) {
        final String CIRCLE_TAG = String.format("<circle " +
                        "cx='%f' cy='%f' r='%d' fill='%s' stroke='%s' stroke-width='%d'/>\n",
                x, y,
                VERTEX_RADIUS,
                COLOR_WHITE,
                COLOR_BLACK,
                STOKE_WIDTH);

        final String TEXT_TAG = String.format("<text " +
                        "x='%f' y='%f' fill='%s' font-family='%s' font-size='%d' text-anchor='%s'>%s</text>\n",
                x, y + 8,
                COLOR_BLACK,
                FONT_SANS_SERIF,
                VERTEX_FONT_SIZE,
                FONT_TEXT_ANCHOR,
                text);

        return CIRCLE_TAG + TEXT_TAG;
    }

    static String drawTreeVertex(double x, double y, String text, String vertexColor, boolean avlTree, String balanceHeight) {
        final String CIRCLE_TAG = String.format("<circle " +
                        "cx='%f' cy='%f' r='%d' fill='%s' stroke='%s' stroke-width='%d'/>\n",
                x, y,
                VERTEX_RADIUS, vertexColor,
                COLOR_BLACK,
                STOKE_WIDTH);

        final String TEXT_TAG = String.format("<text " +
                        "x='%f' y='%f' fill='%s' font-family='%s' font-size='%d' text-anchor='%s'>%s</text>\n",
                x, y + 8,
                vertexColor.equals(COLOR_WHITE) ? COLOR_BLACK : COLOR_WHITE,
                FONT_SANS_SERIF,
                VERTEX_FONT_SIZE,
                FONT_TEXT_ANCHOR,
                text);

        final String BALANCE_AND_DEPTH_TEXT_TAG = String.format("<text " +
                        "x='%f' y='%f' fill='%s' font-family='%s' font-size='%d' text-anchor='%s'>%s</text>\n",
                x + 35,
                y - 15,
                COLOR_BLUE,
                FONT_SANS_SERIF,
                FONT_BALANCE_SIZE_TEXT,
                FONT_TEXT_ANCHOR,
                balanceHeight);

        return CIRCLE_TAG + TEXT_TAG + (avlTree ? BALANCE_AND_DEPTH_TEXT_TAG : "");
    }

    static String drawSquare(int x, int y, int height, int width, String text, DataStructure dataStructure) {
        final String RECT_TAG = String.format("<rect " +
                        "x='%d' y='%d' height='%d' width='%d' fill='%s' stroke='%s' stroke-width='%d'/>\n",
                x, y,
                height,
                width,
                COLOR_WHITE, COLOR_BLACK, STOKE_WIDTH);

        final String TEXT_TAG = String.format("<text " +
                        "x='%d' y='%d' fill='%s' font-family='%s' font-size='%d' text-anchor='%s'>%s</text>\n",
                dataStructure != DataStructure.STACK ? x + 30 : 40,
                dataStructure != DataStructure.STACK ? 35 : y + 25,
                COLOR_BLACK,
                FONT_SANS_SERIF,
                FONT_SIZE_TEXT,
                FONT_TEXT_ANCHOR,
                text);

        return RECT_TAG + TEXT_TAG;
    }

    static class Defs {
        static String createDefs(String id, String inside) {
            return OPEN_DEFS_TAG +
                    openG_TagWithId(id) +
                    inside +
                    CLOSE_G_TAG +
                    CLOSE_DEFS_TAG;
        }

        static String createUseTag(String id, int x, int y) {
            return String.format("<use xlink:href='#%s' x='%d' y='%d' />\n", id, x, y);
        }
    }

}