package mx.unam.ciencias.edd.proyecto3.pojo;

import mx.unam.ciencias.edd.Diccionario;

/**
 * @author Angel Gladin
 * @version 1.0
 * @since 05/06/2016.
 */
public class DictionaryEntry implements Comparable<DictionaryEntry> {
    String dictionaryName;
    Diccionario<String, Integer> dictionary;
    int words;

    public DictionaryEntry(String dictionaryName, Diccionario<String, Integer> dictionary, int words) {
        this.dictionaryName = dictionaryName;
        this.dictionary = dictionary;
        this.words = words;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public Diccionario<String, Integer> getDictionary() {
        return dictionary;
    }

    public int getWords() {
        return words;
    }

    @Override public int compareTo(DictionaryEntry o) {
        return 0;
    }
}
