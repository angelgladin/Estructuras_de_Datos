package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;

/**
 * <p>Clase que implementa {@link SVG_Graficable} la cual dada un gráfica hace las operaciones necesarias para
 * que el en método {@link SVG_Graficable#drawSVG} devuelva un código SVG.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
class SVG_Graph<T> implements SVG_Graficable {

    private Grafica<T> graph;
    private Lista<Point> pointsList = new Lista<>();

    SVG_Graph(Grafica<T> graph) {
        this.graph = graph;
    }

    @Override public String drawSVG() {
        createVertices(500, graph.getElementos());
        return SVG_Util.XML_PROLOG +
                SVG_Util.startSVGAndPutHeightWidth(SVG_Util.GRAPH_WIDTH_HEIGHT + 75, SVG_Util.GRAPH_WIDTH_HEIGHT + 75) +
                SVG_Util.OPEN_G_TAG +
                drawRelations() +
                drawVertices() +
                SVG_Util.CLOSE_G_TAG +
                SVG_Util.closeSVG();
    }

    /**
     * Método que dados los vértices de la gráfica, les va asignando una coordenada como si estuviesen en la
     * circunferencia de un círculo, para después llenar una lista con los puntos.
     *
     * @param r      El radio del círculo,
     * @param nParts El número de veces que será dividida en partes proporcionales la circunferencia.
     */
    private void createVertices(int r, int nParts) {
        double angle;
        double x, y;
        int i = 0;
        for (T e : graph) {
            angle = i++ * (360 / nParts);
            x = r * Math.cos(Math.toRadians(angle));
            y = r * Math.sin(Math.toRadians(angle));
            pointsList.agrega(new Point(x + r + 35, y + r + 35, e));
        }
    }

    /**
     * Método que dibuja vértices.
     *
     * @return Una cadena con código SVG de solamente las vertices.
     */
    private String drawVertices() {
        StringBuilder builder = new StringBuilder();
        for (Point p : pointsList)
            builder.append(SVG_Util.drawGraphVertex(p.x, p.y, p.data.toString()));
        return builder.toString();
    }

    /**
     * Método que dibuja aristas.
     *
     * @return Una cadena con código SVG de solamente las aristas.
     */
    private String drawRelations() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pointsList.getElementos(); i++) {
            Point a = pointsList.get(i);
            for (int j = i; j < graph.getElementos(); j++) {
                Point b = pointsList.get(j);
                if (graph.sonVecinos(a.data, b.data)) {
                    builder.append(SVG_Util.drawLine(a.x, a.y, b.x, b.y));
                }
            }
        }
        return builder.toString();
    }

    /**
     * Clase auxiliar la cual será útil para tener un punto con las coordenadas <b><i>x</i></b> y <b><i>y</i></b>.
     */
    private class Point {
        double x;
        double y;
        T data;

        Point(double x, double y, T data) {
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }
}
