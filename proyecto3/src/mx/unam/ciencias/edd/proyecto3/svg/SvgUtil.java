package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.proyecto3.DataStructure;

/**
 * <p>Clase que tiene solamente constantes y métodos estáticos necesarios para generar los SVG.</p>
 * <p>Dentro de este existen metodos y constantes estaticas por lo que no es necesario instanciar la clase.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
class SvgUtil {

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

    static final double VERTEX_FONT_SIZE = 11;

    static final int FONT_BALANCE_SIZE_TEXT = 9;

    static final int STOKE_WIDTH = 2;
    static final int STOKE_LINE_WIDTH = 2;

    static final String COLOR_WHITE = "white";
    static final String COLOR_BLACK = "black";
    static final String COLOR_RED = "red";
    static final String COLOR_BLUE = "blue";

    static final String[] pieChartPaletteColor = {
        "#53A2BE", "#904C77", "#656839", "#A00027",
        "#703603", "#337357", "#01200F", "#CA61C3",
        "#613F75", "#FDE74C", "#EF798A", "#B5B8A3",
        "#BAF2BB", "#04E824", "#32BA55"};

    static int getVertexRadius(Iterable<?> iterable) {
        int max = 0;
        int vertexRadius = 15;
        for (Object e : iterable)
            if (e.toString().length() > max)
                max = e.toString().length();
        if (max >= 4)
            vertexRadius = ((max - 6) * 4) + vertexRadius;
        return vertexRadius;
    }

    static String wordInfoPieChart(double x, double y, String text, String color) {
        final int SQUARE_WIDTH = 20;
        final int FONT_SIZE = 12;
        final String RECT_TAG = String.format("<rect " +
                                              "x='%f' y='%f' height='%d' width='%d' fill='%s' stroke='black' stroke-width='2'/>\n",
                                              x, y,
                                              SQUARE_WIDTH,
                                              SQUARE_WIDTH,
                                              color);

        final String TEXT_TAG = String.format("<text " +
                                              "x='%f' y='%f' fill='black' font-family='%s' font-size='%d' >%s</text>\n",
                                              x + 30, y + 12.5,
                                              FONT_SANS_SERIF,
                                              FONT_SIZE,
                                              text);

        return RECT_TAG + TEXT_TAG;
    }

    static String drawPath(double mx, double my, double lx, double ly,
                           double a, boolean clockwise, double px, double py, String color) {
        return String.format("<path d='M%f,%f L%f,%f A%f,%f 0 " + (clockwise ? "1" : "0") + ",1 %f,%f Z' "
                             + "stroke-linecap='round' style='stroke: black; stroke-width: 2; fill: " + color + ";' />\n",
                             mx, my, lx, ly, a, a, px, py);

    }

    static String startSVGAndPutHeightWidth(double height, double width) {
        return String.format("<svg height='%f' width='%f'>\n", height, width);
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

    static String drawGraphVertex(double x, double y, double radius, String text) {
        final String CIRCLE_TAG = String.format("<circle " +
                                                "cx='%f' cy='%f' r='%f' fill='%s' stroke='%s' stroke-width='%d'/>\n",
                                                x, y,
                                                radius,
                                                COLOR_WHITE,
                                                COLOR_BLACK,
                                                STOKE_WIDTH);

        final String TEXT_TAG = String.format("<text " +
                                              "x='%f' y='%f' fill='%s' font-family='%s' font-size='%f' text-anchor='%s'>%s</text>\n",
                                              x, y + 4,
                                              COLOR_BLACK,
                                              FONT_SANS_SERIF,
                                              VERTEX_FONT_SIZE,
                                              FONT_TEXT_ANCHOR,
                                              text);

        return CIRCLE_TAG + TEXT_TAG;
    }

    static String drawCircle(double x, double y, double radius, String color) {
        return String.format("<circle " +
                             "cx='%f' cy='%f' r='%f' fill='%s' stroke='%s' stroke-width='%d'/>\n",
                             x, y,
                             radius,
                             color,
                             COLOR_BLACK,
                             STOKE_WIDTH);
    }

    static String drawTreeVertex(double x, double y, double radius, String text, String vertexColor,
                                 boolean avlTree, String balanceHeight) {
        final String CIRCLE_TAG = String.format("<circle " +
                                                "cx='%f' cy='%f' r='%f' fill='%s' stroke='%s' stroke-width='%d'/>\n",
                                                x, y,
                                                radius, vertexColor,
                                                COLOR_BLACK,
                                                STOKE_WIDTH);

        final String TEXT_TAG = String.format("<text " +
                                              "x='%f' y='%f' fill='%s' font-family='%s' font-size='%f' text-anchor='%s'>%s</text>\n",
                                              x, y + 4,
                                              vertexColor.equals(COLOR_WHITE) ? COLOR_BLACK : COLOR_WHITE,
                                              FONT_SANS_SERIF,
                                              VERTEX_FONT_SIZE,
                                              FONT_TEXT_ANCHOR,
                                              text);

        final String BALANCE_AND_DEPTH_TEXT_TAG = String.format("<text " +
                                                                "x='%f' y='%f' fill='%s' font-family='%s' font-size='%d' text-anchor='%s'>%s</text>\n",
                                                                x,
                                                                y - radius - 5,
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
