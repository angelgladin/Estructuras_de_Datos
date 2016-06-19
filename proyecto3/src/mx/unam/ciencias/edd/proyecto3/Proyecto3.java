package mx.unam.ciencias.edd.proyecto3;

import mx.unam.ciencias.edd.proyecto3.exception.NoArgumentsGivenException;
import mx.unam.ciencias.edd.proyecto3.html.HtmlFilesCreator;
import mx.unam.ciencias.edd.proyecto3.io.DataProcessor;

import java.io.IOException;

/**
 * <p><b>Proyecto 3: Contador de palabra en un texto.</b></p>
 * <p>El programa consiste en generar reportes de varios archivos de texto.</p>
 * <p>El programa recibirá varios nombres de archivo por la línea de comandos,
 * y un directorio existente precedido de la bandera "-o"</p>
 * <p>La forma de ejecutar el pograma es la siguiente</p>
 * <p><code>java -jar proyecto3.jar archivo1.txt archivo2.txt -o directorio archivo3.txt</code></p>
 * <p>Para cada archivo de texto el programa generará un archivo HTML dentro del directorio especificado
 * en la línea de comandos con la siguiente información:</p>
 * <ul>
 * <li>Un conteo de cuántas veces aparece cada palabra en el archivo, sin considerar mayúsculas/minúsculas
 * ni acentos. Dicho de otra forma, líquido se considera la misma palabra que Liquidó. El conteo debe
 * realizarse en O(n), donde n es el número de palabras en el archivo.</li>
 * <li>Una gráfica de pastel de las palabras más comunes en el archivo y qué porcentaje del total
 * de palabras ocupan.</li>
 * <li>Un árbol rojinegro con las 15 palabras más utilizadas en el archivo, donde el valor de cada
 * palabra es el número de veces que aparece en el archivo.</li>
 * <li>Un árbol AVL con los mismos datos del árbol de arriba.</li>
 * </ul>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 17/06/2016.
 */
public class Proyecto3 {

    public static void main(String[] args) {
        try {
            HtmlFilesCreator htmlFilesCreator = new HtmlFilesCreator(new DataProcessor(args));
            htmlFilesCreator.invoke();
        } catch (NoArgumentsGivenException | IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println("El programa acabó satisfactoriamente :)");
    }

}

