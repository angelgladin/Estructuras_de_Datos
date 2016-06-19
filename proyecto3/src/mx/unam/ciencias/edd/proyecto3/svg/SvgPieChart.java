package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.pojo.WordEntry;

import java.text.DecimalFormat;

import static mx.unam.ciencias.edd.proyecto3.svg.SvgUtil.*;

/**
 * <p>Clase con visibilidad local (la cual obliga al usuario a interactuar con la interfaz)
 * que con implementa {@link SvgGraphicable} la cual dada una lista {@link WordEntry}
 * hace las operaciones necesarias para generar una gráfica de pastel; para que
 * el en método{@link SvgGraphicable#drawSVG} devuelva un código SVG con una gráfica de pastel.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
class SvgPieChart implements SvgGraphicable {

    final int LIMIT = 10;

    Lista<WordEntry> wordEntryList;
    Point[] points;

    int totalWordSublist;
    int totalWords;
    int wordsWithRepetition;

    SvgPieChart(Lista<WordEntry> wl, int totalWords) {
        this.totalWords = totalWords;
        this.wordEntryList = orderedSubList(wl);
        this.totalWordSublist = wordEntryList.getElementos();
        wordsWithRepetition = getTotalWordWithRepetitions();
    }

    /**
     * Método que dada una lista la ordena y pone un límite de cuantos elementos habra en la lista.
     *
     * @param l Una lista
     * @return Una sublista ordenada.
     */
    private Lista<WordEntry> orderedSubList(Lista<WordEntry> l) {
        Lista<WordEntry> aux = Lista.mergeSort(l).reversa();
        Lista<WordEntry> wordEntryList = new Lista<>();
        int i = 0;
        for (WordEntry wordEntry : aux) {
            if (i++ == LIMIT)
                break;
            wordEntryList.agrega(wordEntry);
        }
        return wordEntryList;
    }

    /**
     * Método auxiliar que indica cuantas palabras tiene la lista recibida.
     *
     * @return El número de palabras.
     */
    private int getTotalWordWithRepetitions() {
        int t = 0;
        for (WordEntry wordEntry : wordEntryList)
            t += wordEntry.wordCount;
        return t;
    }

    @Override public String drawSVG() {
        generatePoints(wordsWithRepetition);
        StringBuilder builder = new StringBuilder();

        builder.append(XML_PROLOG);
        if (wordEntryList.getElementos() == 0) {
            builder.append(startSVGAndPutHeightWidth(0, 0));
        } else {
            builder.append(startSVGAndPutHeightWidth(400 + (totalWordSublist * 30), 400));
            builder.append(OPEN_G_TAG);

            if (wordEntryList.getElementos() == 1) {
                builder.append(drawCircle(200, 200, 180, pieChartPaletteColor[0]));
            } else {
                for (int i = 0; i < totalWordSublist; i++) {
                    if (i == totalWordSublist - 1) {
                        builder.append(drawPath(200, 200, points[i].x, points[i].y,
                                                180, moreThanHalf(i), points[0].x, points[0].y, pieChartPaletteColor[i]));
                    } else {
                        builder.append(drawPath(200, 200, points[i].x, points[i].y,
                                                180, moreThanHalf(i), points[i + 1].x, points[i + 1].y, pieChartPaletteColor[i]));
                    }
                }
            }

            int aux = 400, i = 0;
            for (WordEntry wordEntry : wordEntryList) {
                String text = wordEntry.word + " -> %" +
                    (new DecimalFormat("##.##").format((wordEntry.wordCount * 100.0) / totalWords));
                builder.append(wordInfoPieChart(20, aux, text, pieChartPaletteColor[i++]));
                aux += 30;
            }
            builder.append(CLOSE_G_TAG);
        }

        builder.append(closeSVG());
        return builder.toString();
    }

    /**
     * Método auxiliar el cual sirve en el svg para indicar hacia que direccion
     * se crea el arco se circunferencia. El motivo de hacerlo es ya que en el svg cuando
     * el ángulo formado entre dos puntos si es mayor igual que 180 grados
     * se debe de indicar en el svg con una bandera.
     *
     * @param i La i-ésima entrada de la lista
     * @return <b>True</b> si el ángulo formado es mayor o igual que 180 grados,
     * <b>False</b> en otro caso.
     */
    private boolean moreThanHalf(int i) {
        return (wordEntryList.get(i).wordCount * 100.0) / wordsWithRepetition > 50.0;
    }

    /**
     * Método auxiliar el cual genera las coordenadas deseadas de la gráfica de pastel.
     *
     * @param total El número de elementos que hay en total.
     */
    private void generatePoints(int total) {
        if (totalWordSublist > 1) {
            points = new Point[totalWordSublist];
            double angle, x, y, aux = 0;
            for (int i = 0; i < totalWordSublist; i++) {
                angle = 2 * Math.PI * (aux / total);
                x = Math.cos(angle);
                y = Math.sin(angle);
                points[i] = new Point(convertCoordinate(x), convertCoordinate(y));
                aux += wordEntryList.get(i).wordCount;
            }
        }
    }

    /**
     * Método auxiliar llamado en {@link SvgPieChart#generatePoints(int)} que mueve el origen
     * y por ende los puntos, ajustandolos en el centro del canvas del svg.
     *
     * @param f La coordenada con el origen en (0,0).
     * @return La cordenada en la posición correcta.
     */
    private double convertCoordinate(double f) {
        return 200 - (f * 180);
    }

    /**
     * Clase auxiliar la cual será útil para tener un punto con las coordenadas <b><i>x</i></b> y <b><i>y</i></b>.
     */
    private class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
