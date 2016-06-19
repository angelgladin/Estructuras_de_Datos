package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;

/**
 * <p>Clase con visibilidad local (la cual obliga al usuario a interactuar con la interfaz)
 * que implementa {@link SvgGraphicable} la cual dada un gráfica hace las operaciones necesarias para
 * que el en método {@link SvgGraphicable#drawSVG} devuelva un código SVG.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
class SvgGraph<T> implements SvgGraphicable {

    private Grafica<T> graph;
    private Lista<Point> pointsList = new Lista<>();

    SvgGraph(Grafica<T> graph) {
        this.graph = graph;
    }

    @Override public String drawSVG() {
        StringBuilder builder = new StringBuilder();
        int vertexRadius = SvgUtil.getVertexRadius(graph);
        double radiusCircle = ((2 * graph.getElementos()) * (2 * vertexRadius)) / Math.PI;
        double height = (2 * vertexRadius) + (2 * radiusCircle) + vertexRadius;

        builder.append(SvgUtil.XML_PROLOG);
        if (graph.getElementos() == 0)
            builder.append(SvgUtil.startSVGAndPutHeightWidth(0, 0));
        else
            builder.append(SvgUtil.startSVGAndPutHeightWidth(height, height));

        builder.append(SvgUtil.OPEN_G_TAG);
        createVertices(radiusCircle, graph.getElementos(), height / 2);
        if (graph.getElementos() > 1)
            builder.append(drawEdges());
        builder.append(drawVertices(vertexRadius));
        builder.append(SvgUtil.CLOSE_G_TAG);

        builder.append(SvgUtil.closeSVG());
        return builder.toString();
    }

    /**
     * Método que dados los vértices de la gráfica, les va asignando una coordenada como si estuviesen en la
     * circunferencia de un círculo, para después llenar una lista con los puntos.
     *
     * @param r      El radio del círculo,
     * @param nParts El número de veces que será dividida en partes proporcionales la circunferencia.
     * @param half
     */
    private void createVertices(double r, int nParts, double half) {
        double angle;
        double x, y;
        int i = 0;
        for (T e : graph) {
            if (graph.getElementos() == 1) {
                pointsList.agrega(new Point(half, half, e));
            } else {
                angle = i++ * (360 / nParts);
                x = r * Math.cos(Math.toRadians(angle));
                y = r * Math.sin(Math.toRadians(angle));
                pointsList.agrega(new Point(x + half, y + half, e));
            }
        }
    }

    /**
     * Método que dibuja vértices.
     *
     * @return Una cadena con código SVG de solamente las vertices.
     */
    private String drawVertices(int radius) {
        StringBuilder builder = new StringBuilder();
        for (Point p : pointsList)
            builder.append(SvgUtil.drawGraphVertex(p.x, p.y, radius, p.data.toString()));
        return builder.toString();
    }

    /**
     * Método que dibuja aristas.
     *
     * @return Una cadena con código SVG de solamente las aristas.
     */
    private String drawEdges() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pointsList.getElementos(); i++) {
            Point a = pointsList.get(i);
            for (int j = i; j < graph.getElementos(); j++) {
                Point b = pointsList.get(j);
                if (graph.sonVecinos(a.data, b.data)) {
                    builder.append(SvgUtil.drawLine(a.x, a.y, b.x, b.y));
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
