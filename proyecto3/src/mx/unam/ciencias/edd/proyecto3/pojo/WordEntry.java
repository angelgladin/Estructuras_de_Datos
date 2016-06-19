package mx.unam.ciencias.edd.proyecto3.pojo;

/**
 * Clase que simula un entrada de una palabra.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class WordEntry implements Comparable<WordEntry> {
    public String word;
    public int wordCount;

    /**
     * Constructor de la clase.
     *
     * @param word      La palabra.
     * @param wordCount El número de veces que aparece.
     */
    public WordEntry(String word, int wordCount) {
        this.word = word;
        this.wordCount = wordCount;
    }

    @Override public int compareTo(WordEntry o) {
        if (wordCount > o.wordCount)
            return 1;
        else if (wordCount < o.wordCount)
            return -1;
        return 0;
    }

    @Override public String toString() {
        return word + ": " + wordCount;
    }
}