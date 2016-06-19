package mx.unam.ciencias.edd.proyecto3.io;

import java.io.File;
import java.io.IOException;

/**
 * Clase la cual me provee de utilidades de los archivos.
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class FileUtils {
    /**
     * Método cuya función es remover la extensión de un archivo.
     *
     * @param s El nombre del archivo
     * @return El nombre del archivo sin la extensión.
     */
    public static String removeExtension(String s) {
        String separator = System.getProperty("file.separator");
        String filename;
        int lastSeparatorIndex = s.lastIndexOf(separator);
        if (lastSeparatorIndex == -1)
            filename = s;
        else
            filename = s.substring(lastSeparatorIndex + 1);
        // Remove the extension.
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1)
            return filename;
        return filename.substring(0, extensionIndex);
    }

    /**
     * Método el cual se encarga de crear un archivo.
     *
     * @param directoryFile El directorio donde estará el archivo.
     * @param fileName      El nombre del archivo.
     * @param extension     La extension del archivo.
     * @return Un objeto file que hace referencia al objeto creado.
     * @throws IOException Una excepción con la entrada o salida.
     */
    public static File fileCreator(File directoryFile, String fileName, String extension) throws IOException {
        File fileIndexHTML = new File(directoryFile, fileName + extension);
        if (fileIndexHTML.exists())
            fileIndexHTML.delete();
        fileIndexHTML.createNewFile();
        return fileIndexHTML;
    }
}
