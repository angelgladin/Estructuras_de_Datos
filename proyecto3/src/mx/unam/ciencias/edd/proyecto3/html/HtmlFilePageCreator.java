package mx.unam.ciencias.edd.proyecto3.html;

import mx.unam.ciencias.edd.Diccionario;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.DataStructure;
import mx.unam.ciencias.edd.proyecto3.pojo.DictionaryEntry;
import mx.unam.ciencias.edd.proyecto3.pojo.WordEntry;
import mx.unam.ciencias.edd.proyecto3.svg.SvgFactory;

/**
 * <p>Clase con visibilidad local la cual crea un archivo html por cada archivo.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
class HtmlFilePageCreator {

    final int ELEMENTS_TREE_LIMIT = 15;
    int totalWords;
    DictionaryEntry dictionaryEntry;

    /**
     * Constructor de la clase el cual inicializa el {@link DictionaryEntry}.
     *
     * @param dictionaryEntry Una entrada de un diccionario.
     */
    HtmlFilePageCreator(DictionaryEntry dictionaryEntry) {
        if (dictionaryEntry == null)
            throw new IllegalArgumentException();
        this.dictionaryEntry = dictionaryEntry;
    }

    /**
     * Método el cual ordena las palabras de forma ascendente para mostrarlas en una lista
     * del html.
     *
     * @return Una lista ordenada de las palabras.
     */
    private Lista<WordEntry> getOrderedWordList() {
        Lista<WordEntry> wordEntriesList = new Lista<>();
        Diccionario<String, Integer> dictionary = dictionaryEntry.getDictionary();
        int t = 0, v = 0;
        for (String key : dictionary.llaves()) {
            v = dictionary.get(key);
            wordEntriesList.agrega(new WordEntry(key, v));
            t += v;
        }
        this.totalWords = t;
        return Lista.mergeSort(wordEntriesList).reversa();
    }

    /**
     * Hace un sublista de menor tamaño a la recibida.
     *
     * @param wordEntriesList Una lista.
     * @param limit           El tamaño de la sublista.
     * @return Un sublista.
     */
    private Lista<WordEntry> subList(Lista<WordEntry> wordEntriesList, int limit) {
        int i = 1;
        Lista<WordEntry> subList = new Lista<>();
        for (WordEntry wordEntry : wordEntriesList) {
            if (i++ == limit + 1)
                break;
            subList.agrega(wordEntry);
        }
        return shuffleList(shuffleList(subList));
    }

    /**
     * Método auxiliar el cual "revuelve" una lista, se hizo para que los árboles
     * quedaran esponjaditos.
     *
     * @param l Una lista.
     * @return Una lista con los elementos "revueltos".
     */
    private Lista<WordEntry> shuffleList(Lista<WordEntry> l) {
        Lista<WordEntry> auxList = new Lista<>();
        while (!l.esVacio())
            if (l.getElementos() % 2 == 0)
                auxList.agrega(l.eliminaUltimo());
            else
                auxList.agrega(l.eliminaPrimero());
        return auxList;
    }

    @Override public String toString() {
        Lista<WordEntry> wordEntriesList = getOrderedWordList();
        Lista<WordEntry> wordEntriesSubList = subList(wordEntriesList, ELEMENTS_TREE_LIMIT);

        String firstPartBeforeTitle = "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Proyecto 3: " +
            "Estructuras de datos</title><link rel='stylesheet' type='text/css' href='stylesheet.css' />" +
            "</head><body><!--Header : description--><div align='center'><section class='group5'><h1>";
        String firstPartAfterTitle = "</h1></section></div><!--start container--><div id='container'><!--Project " +
            "description--><div><section class='group5'><article><h3>Palabras</h3>";

        StringBuilder listTag = new StringBuilder();
        listTag.append("<ul><!--Word entry list-->");
        for (WordEntry wordEntry : wordEntriesList)
            listTag.append("<li>" + wordEntry.word + ": <font color='#0F9795'>" + wordEntry.wordCount + "</font></li>");
        listTag.append("</ul>");
        String closeFirstPart = "</article></section></div>";

        String pieChartSVGOpenTag = "<div style='text-align:center;'<article><h3>Gráfica de pastel</h3>" +
            "</article><!--Pie chart svg-->";
        String redBlackTreeSVGOpenTag = "<div style='text-align:center;'<article><h3>Árbol rojinegro</h3>" +
            "</article><!--Red black tree svg-->";
        String avlTreeSVGOpenTag = "<div style='text-align:center;'<article><h3>Árbol AVL</h3>" +
            "</article><!--AVL tree svg-->";

        String svgCloseTag = "</div>";

        String lastPart_HTML = "<h1><a href='index.html' style='color: #0F9795'>← Volver</a></h1></div></body></html>";

        return firstPartBeforeTitle + dictionaryEntry.getDictionaryName() + firstPartAfterTitle +
            listTag.toString() + closeFirstPart + pieChartSVGOpenTag +
            SvgFactory.getPieChart(wordEntriesSubList, totalWords).drawSVG() + svgCloseTag + redBlackTreeSVGOpenTag +
            SvgFactory.getTree(wordEntriesSubList, DataStructure.RED_BLACK_TREE).drawSVG() + svgCloseTag +
            avlTreeSVGOpenTag + SvgFactory.getTree(wordEntriesSubList, DataStructure.AVL_TREE).drawSVG() +
            svgCloseTag + lastPart_HTML;
    }

}
