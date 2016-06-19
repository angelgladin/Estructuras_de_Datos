package mx.unam.ciencias.edd.proyecto3.html;

import mx.unam.ciencias.edd.Diccionario;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.io.DataProcessor;
import mx.unam.ciencias.edd.proyecto3.io.FileUtils;
import mx.unam.ciencias.edd.proyecto3.pojo.DictionaryEntry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


/**
 * <p>Clase la cual se encarga de hacer el trabajo de gererar los archivos <b>html</b> y <b>css</b>
 * para después escribir en ellos la información necesaria.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class HtmlFilesCreator {

    Lista<Diccionario<String, Integer>> dictionaryList = new Lista<>();
    Lista<String> fileNameList = new Lista<>();
    Grafica<String> graph = new Grafica<>();

    private File directoryFile;
    private File fileIndexHTML;
    private File fileStyleSheet_CSS;
    private File[] filePageHTML;

    /**
     * Constructor de la clase el cual inicializa todas las instancias.
     *
     * @param dataProcessor Clase la cual tiene toda la informacion necesaria ya procesada.
     * @throws IOException Una excepción con la entrada o salida.
     */
    public HtmlFilesCreator(DataProcessor dataProcessor) throws IOException {
        if (dataProcessor == null)
            throw new IllegalArgumentException();
        this.fileNameList = dataProcessor.getFileNameList();
        this.filePageHTML = new File[dataProcessor.getFileNameList().getElementos()];
        this.directoryFile = dataProcessor.getDirectoryFile();
        this.dictionaryList = dataProcessor.getDictionaryList();
        this.graph = dataProcessor.getGraph();
    }

    /**
     * Método el cual se encarga de borrar lo que existe en el directorio, ya que pueden
     * existir archivos en el directorio y puede ser molesto ver archivos de una
     * previa ejecución del programa.
     *
     * @throws IOException Una excepción con la entrada o salida.
     */
    private void deleteFilesInDirectory() throws IOException {
        Files.walk(directoryFile.toPath())
            .filter(f -> f.toFile().isFile())
            .forEach(f -> f.toFile().delete());
    }

    /**
     * Método el cual se encarga de generar los archivos html por cada archivo de texto, SOLO LOS CREA.
     *
     * @throws IOException Una excepción con la entrada o salida.
     */
    private void createFiles() throws IOException {
        fileIndexHTML = FileUtils.fileCreator(directoryFile, "index", ".html");
        fileStyleSheet_CSS = FileUtils.fileCreator(directoryFile, "stylesheet", ".css");
        int i = 0;
        for (String fileName : fileNameList)
            filePageHTML[i++] = FileUtils.fileCreator(directoryFile, FileUtils.removeExtension(fileName), ".html");
    }

    /**
     * Método que se encarga del procesamiento de archivos y escribir los html y css necesarios.
     *
     * @throws IOException Una excepción con la entrada o salida.
     */
    public void invoke() throws IOException {
        deleteFilesInDirectory();
        createFiles();

        Lista<DictionaryEntry> dictionaryEntriesList = new Lista<>();
        int i = 0;
        for (Diccionario<String, Integer> dictionary : dictionaryList)
            dictionaryEntriesList.agrega(
                                         new DictionaryEntry(FileUtils.removeExtension(fileNameList.get(i++)), dictionary, dictionary.getElementos()));

        FileWriter writerIndex;

        writerIndex = new FileWriter(fileIndexHTML);
        writerIndex.write(new HtmlIndexCreator(graph, dictionaryEntriesList).toString());
        writerIndex.flush();
        writerIndex.close();

        writerIndex = new FileWriter(fileStyleSheet_CSS);
        writerIndex.write(HtmlUtil.getCSS());
        writerIndex.flush();
        writerIndex.close();

        i = 0;
        for (DictionaryEntry dictionaryEntry : dictionaryEntriesList) {
            writerIndex = new FileWriter(filePageHTML[i++]);
            writerIndex.write(new HtmlFilePageCreator(dictionaryEntry).toString());
            writerIndex.flush();
            writerIndex.close();
        }
    }


}
