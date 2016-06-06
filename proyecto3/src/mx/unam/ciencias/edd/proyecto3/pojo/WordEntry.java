package mx.unam.ciencias.edd.proyecto3.pojo;

/**
 * @author Angel Gladin
 * @version 1.0
 * @since 05/06/2016.
 */
public class WordEntry implements Comparable<WordEntry> {
    public String word;
    public int wordCount;

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