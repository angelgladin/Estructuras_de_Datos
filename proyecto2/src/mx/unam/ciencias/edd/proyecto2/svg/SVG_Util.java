package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.proyecto2.Estructura;

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
    public static final String TEXT_LEFTWARD_ARROW = "←";

    public static final int STOKE_WIDTH = 1;

    public static final String COLOR_WHITE = "#FFFFFF";
    public static final String COLOR_BLACK = "#000000";
    public static final String COLOR_RED = "#FF0000";
    public static final String COLOR_BLUE = "#0000FF";

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

    public static String squareWithText(int x, int y, int height, int width, String text, Estructura estructura) {
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
                DOUBLE_QUOTE, estructura != Estructura.PILA ? x + 30 : 40    , DOUBLE_QUOTE,
                DOUBLE_QUOTE, estructura != Estructura.PILA ? 35     : y + 25, DOUBLE_QUOTE,
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