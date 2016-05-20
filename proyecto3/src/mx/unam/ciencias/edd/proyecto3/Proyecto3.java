package mx.unam.ciencias.edd.proyecto3;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto3.exception.InvalidDirectoryException;
import mx.unam.ciencias.edd.proyecto3.exception.NoArgumentsGivenException;

import java.io.*;

public class Proyecto3 {

    static String DIRECTORY_FLAG = "-o";

    static Lista<String> fileList = new Lista<>();
    static String directoryName;

    static File directoryPathFile;
    static File fileIndexHTML;

    public static void main(String[] args) {
        try {
            getFilesAndDirectoryName(args);
            createIndexHTML_File();
            writeIndexHTML_File();
        } catch (NoArgumentsGivenException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getFilesAndDirectoryName(String[] args) throws InvalidDirectoryException {
        if (args.length == 0)
            throw new NoArgumentsGivenException();
        else
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals(DIRECTORY_FLAG)) {
                    if (i + 1 >= args.length || !(new File(args[i + 1]).isDirectory()))
                        throw new InvalidDirectoryException();
                    directoryName = args[(i++) + 1];
                } else {
                    fileList.agrega(args[i]);
                }
            }

        directoryPathFile = new File(directoryName);
        if (!directoryPathFile.isDirectory())
            throw new InvalidDirectoryException();
    }

    private static void createIndexHTML_File() throws IOException {
        fileIndexHTML = new File(directoryPathFile, "index.txt");
        if (fileIndexHTML.exists())
            fileIndexHTML.delete();
        fileIndexHTML.createNewFile();
    }

    private static void writeIndexHTML_File() throws IOException {
        FileWriter writer = new FileWriter(fileIndexHTML);
        String input;
        for (String file : fileList) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((input = br.readLine()) != null)
                writer.write(input + "\n");
        }
        writer.flush();
        writer.close();
    }

}

