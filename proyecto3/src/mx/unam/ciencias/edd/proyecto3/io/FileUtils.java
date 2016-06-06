package mx.unam.ciencias.edd.proyecto3.io;

import java.io.File;
import java.io.IOException;

/**
 * @author Angel Gladin
 * @version 1.0
 * @since 21/05/2016.
 */
public class FileUtils {
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

    public static File fileCreator(File directoryFile, String fileName, String extension) throws IOException {
        File fileIndexHTML = new File(directoryFile, fileName + extension);
        if (fileIndexHTML.exists())
            fileIndexHTML.delete();
        fileIndexHTML.createNewFile();
        return fileIndexHTML;
    }
}
