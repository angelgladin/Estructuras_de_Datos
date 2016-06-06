package mx.unam.ciencias.edd.proyecto3.html;

import mx.unam.ciencias.edd.Diccionario;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.io.DataProcessor;
import mx.unam.ciencias.edd.proyecto3.io.FileUtils;
import mx.unam.ciencias.edd.proyecto3.pojo.DictionaryEntry;
import mx.unam.ciencias.edd.proyecto3.svg.SVG_Factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Angel Gladin
 * @version 1.0
 * @since 26/05/2016.
 */
public class HTML_FilesCreator {

    Lista<Diccionario<String, Integer>> dictionaryList = new Lista<>();
    Lista<String> fileNameList = new Lista<>();
    Grafica<String> graph = new Grafica<>();

    File directoryFile;
    private File fileIndexHTML;
    private File fileStyleSheet_CSS;
    private File[] filePageHTML;

    public HTML_FilesCreator(DataProcessor dataProcessor) throws IOException {
        if (dataProcessor == null)
            throw new IllegalArgumentException();
        this.fileNameList = dataProcessor.getFileNameList();
        this.filePageHTML = new File[dataProcessor.getFileNameList().getElementos()];
        this.directoryFile = new File(dataProcessor.getDirectoryName());
        this.dictionaryList = dataProcessor.getDictionaryList();
        this.graph = dataProcessor.getGraph();
        deteFilesInDirectory();
        createFiles();
    }

    private void deteFilesInDirectory() throws IOException {
        Files.walk(directoryFile.toPath())
                .filter(f -> f.toFile().isFile())
                .forEach(f -> f.toFile().delete());
    }

    private void createFiles() throws IOException {
        fileIndexHTML = FileUtils.fileCreator(directoryFile, "index", ".html");
        fileStyleSheet_CSS = FileUtils.fileCreator(directoryFile, "stylesheet", ".css");
        int i = 0;
        for (String fileName : fileNameList)
            filePageHTML[i++] = FileUtils.fileCreator(directoryFile, FileUtils.removeExtension(fileName), ".html");
    }

    public void invoke() throws IOException {
        FileWriter writerIndex;
        Lista<DictionaryEntry> dictionaryEntriesList = new Lista<>();
        int i = 0;
        for (Diccionario<String, Integer> dictionary : dictionaryList)
            dictionaryEntriesList.agrega(
                    new DictionaryEntry(FileUtils.removeExtension(fileNameList.get(i++)), dictionary, dictionary.getElementos()));

        writerIndex = new FileWriter(fileIndexHTML);
        writerIndex.write(new HTML_IndexCreator(new SVG_Factory<String>().getGraph(graph).drawSVG(), dictionaryEntriesList).toString());
        writerIndex.flush();
        writerIndex.close();

        writerIndex = new FileWriter(fileStyleSheet_CSS);
        writerIndex.write(HTML_Util.get_CSS());
        writerIndex.flush();
        writerIndex.close();

        i = 0;
        for (DictionaryEntry dictionaryEntry : dictionaryEntriesList) {
            writerIndex = new FileWriter(filePageHTML[i++]);
            writerIndex.write(new HTML_FilePageCreator(dictionaryEntry).toString());
            writerIndex.flush();
            writerIndex.close();
        }
    }


}
