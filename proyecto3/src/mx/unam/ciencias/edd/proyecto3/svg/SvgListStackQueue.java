package mx.unam.ciencias.edd.proyecto3.svg;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.DataStructure;

/**
 * <p>Clase con visibilidad local (la cual obliga al usuario a interactuar con la interfaz)
 * que implementa {@link SvgGraphicable} la cual dada una lista hace las operaciones necesarias para
 * que el en método {@link SvgGraphicable#drawSVG} devuelva un código SVG de una lista, pila o cola.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
class SvgListStackQueue<T> implements SvgGraphicable {
    private Lista<T> elements;
    private DataStructure dataStructure;

    SvgListStackQueue(Lista<T> elements, DataStructure dataStructure) {
        this.elements = elements;
        this.dataStructure = dataStructure;
    }

    @Override public String drawSVG() {
        int i = 0;

        StringBuilder builder = new StringBuilder();
        builder.append(SvgUtil.XML_PROLOG);
        if (dataStructure != DataStructure.STACK) {
            String arrow = dataStructure == DataStructure.LIST ? SvgUtil.TEXT_LEFT_RIGHT_ARROW : SvgUtil.TEXT_RIGHTWARD_ARROW;
            builder.append(SvgUtil.startSVGAndPutHeightWidth(60, (10 + 80 * elements.getElementos() - 10)));
            builder.append(SvgUtil.Defs.createDefs(SvgUtil.ID_DEF_ARROW, SvgUtil.drawArrow(arrow)));
            builder.append(SvgUtil.OPEN_G_TAG);
            for (T x : elements) {
                builder.append(SvgUtil.drawSquare(10 + (80 * i), 10, 40, 60, x.toString(), dataStructure));
                if (i != elements.getElementos() - 1) {
                    builder.append(SvgUtil.Defs.createUseTag(SvgUtil.ID_DEF_ARROW, 80 + i * 80, 35));
                }
                i++;
            }
        } else {
            builder.append(SvgUtil.startSVGAndPutHeightWidth(20 + elements.getElementos() * 40, 80));
            builder.append(SvgUtil.OPEN_G_TAG);
            for (T x : elements.reversa())
                builder.append(SvgUtil.drawSquare(10, 10 + 40 * i++, 40, 60, x.toString(), dataStructure));
        }
        builder.append(SvgUtil.CLOSE_G_TAG);
        builder.append(SvgUtil.closeSVG());
        return builder.toString();
    }
}
