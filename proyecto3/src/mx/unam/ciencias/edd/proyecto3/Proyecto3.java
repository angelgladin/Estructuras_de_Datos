package mx.unam.ciencias.edd.proyecto3;

import mx.unam.ciencias.edd.proyecto3.exception.NoArgumentsGivenException;
import mx.unam.ciencias.edd.proyecto3.html.HTML_FilesCreator;
import mx.unam.ciencias.edd.proyecto3.io.DataProcessor;

import java.io.IOException;

public class Proyecto3 {

    public static void main(String[] args) {
        try {
            HTML_FilesCreator html_filesCreator = new HTML_FilesCreator(new DataProcessor(args));
            html_filesCreator.invoke();
        } catch (NoArgumentsGivenException | IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("El programa acabó satisfactoriamente :)");
    }

}

