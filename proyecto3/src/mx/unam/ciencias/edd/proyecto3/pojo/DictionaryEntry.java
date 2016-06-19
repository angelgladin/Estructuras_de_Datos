package mx.unam.ciencias.edd.proyecto3.pojo;

import mx.unam.ciencias.edd.Diccionario;

/**
 * Clase que simula un entrada de un diccionario.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class DictionaryEntry {
    String dictionaryName;
    Diccionario<String, Integer> dictionary;
    int words;

    /**
     * Constructor de la clase.
     *
     * @param dictionaryName Nombre del diccionario, en este caso es el del archivo.
     * @param dictionary     Un diccionario.
     * @param words          El numero de elementos en el diccionario.
     */
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

}
