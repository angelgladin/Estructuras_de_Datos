package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.proyecto2.Estructura;

public class GraficaEstructura {

    public static class Lista<T> implements EstructuraSVG {

        private mx.unam.ciencias.edd.Lista<T> lista;
        private Estructura estructura;

        public Lista(mx.unam.ciencias.edd.Lista<T> lista, Estructura estructura) {
            this.lista = lista;
            this.estructura = estructura;
        }

        @Override
        public String dibujaSVG() {
            int i = 0;

            StringBuilder builder = new StringBuilder();
            builder.append(SVG_Util.XML_PROLOG);
            builder.append(SVG_Util.NEW_LINE);

            if (estructura != Estructura.PILA) {
                String arrow = estructura == Estructura.LISTA ? SVG_Util.TEXT_LEFT_RIGHT_ARROW : SVG_Util.TEXT_RIGHTWARD_ARROW;
                builder.append(SVG_Util.startSVGAndPutHeightWidth(60, (10 + 80 * lista.getElementos() - 10)));
                builder.append(SVG_Util.Defs.createDefs(SVG_Util.ID_DEF_ARROW, SVG_Util.drawArrow(arrow)));
                builder.append(SVG_Util.NEW_LINE);
                for (T x : lista) {
                    builder.append(SVG_Util.squareWithText(10 + (80 * i), 10, 40, 60, x.toString(), estructura));
                    builder.append(SVG_Util.NEW_LINE);
                    if (i != lista.getElementos() - 1) {
                        builder.append(SVG_Util.Defs.createUseTag(SVG_Util.ID_DEF_ARROW, 80 + i * 80, 35));
                        builder.append(SVG_Util.NEW_LINE);
                    }
                    i++;
                }
            } else {
                builder.append(SVG_Util.startSVGAndPutHeightWidth(20 + lista.getElementos() * 40, 80));
                builder.append(SVG_Util.NEW_LINE);
                for (T x : lista.reversa())
                    builder.append(SVG_Util.squareWithText(10, 10 + 40 * i++, 40, 60, x.toString(), estructura));
            }
            builder.append(SVG_Util.NEW_LINE);
            builder.append(SVG_Util.closeSVG());
            return builder.toString();
        }
    }


}