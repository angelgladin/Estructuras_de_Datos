package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.excepciones.EstructuraInvalida;
import mx.unam.ciencias.edd.proyecto2.excepciones.FormatoInvalidoException;
import mx.unam.ciencias.edd.proyecto2.svg.FabricaSVG;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * <p>Proyecto 2</p>
 * <p>
 * <p>.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 06/05/2016.
 */
public class Proyecto2 {

    static ArbolBinarioCompleto<Integer> arbolBinarioCompleto;
    static ArbolBinarioOrdenado<Integer> arbolBinarioOrdenado;
    static ArbolRojinegro<Integer> arbolRojinegro = new ArbolRojinegro<>();
    static ArbolAVL<Integer> arbolAVL = new ArbolAVL<>();
    /**
     * Me servira tanto para listas pilas y colas, para no  volver a repetir o crear una nueva estructura.
     */
    static Lista<Integer> elementos = new Lista<>();

    public static void main(String[] args) {

        Estructura estructura = Estructura.NINGUNA;

        boolean esEntradaEstandar = args.length == 0;

        try {
            BufferedReader br;
            String input;
            int c = 0;

            if (esEntradaEstandar)
                br = new BufferedReader(new InputStreamReader(System.in));
            else
                br = new BufferedReader(new FileReader(args[0]));

            while ((input = br.readLine()) != null && c != 2) {
                if (input.isEmpty() || c == 0 && input.charAt(0) != '#')
                    throw new FormatoInvalidoException();
                if (c == 0) {
                    estructura = Estructura.estaEnEnum(input.substring(2).trim());
                    if (estructura == null || estructura == Estructura.NINGUNA)
                        throw new EstructuraInvalida();
                }
                if (c == 1) {
                    input = input + " ";
                    StringBuilder a = new StringBuilder();
                    for (int i = 0; i < input.length() - 1; i++) {
                        if (Character.isDigit(input.charAt(i))) {
                            a.append(input.charAt(i));
                            if (!Character.isDigit(input.charAt(i + 1))) {
                                elementos.agrega(Integer.parseInt(a.toString()));
                                a = new StringBuilder();
                            }
                        }
                    }
                    if (estructura != Estructura.GRAFICA)
                        break;
                }
                if (c == 2 && estructura == Estructura.GRAFICA) {
                }
                c++;
            }
        } catch (IOException | FormatoInvalidoException | EstructuraInvalida e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        agregaElementosEstructura(estructura, elementos);

        System.out.println(obtenerSVG(estructura));
    }

    private static void agregaElementosEstructura(Estructura estructura, Lista<Integer> elementos) {
        switch (estructura) {
            case ARBOL_BINARIO_COMPLETO:
                arbolBinarioCompleto = new ArbolBinarioCompleto<>(elementos);
                break;
            case ARBOL_BINARIO_ORDENADO:
                arbolBinarioOrdenado = new ArbolBinarioOrdenado<>(elementos);
                break;
            case ARBOL_ROJINEGRO:
                elementos.forEach(arbolRojinegro::agrega);
                break;
            case ARBOL_AVL:
                elementos.forEach(arbolAVL::agrega);
                break;
            case GRAFICA:
                //TODO
                break;
            default:
                break;
        }
    }

    private static String obtenerSVG(Estructura estructura) {
        FabricaSVG<Integer> fabricaSVG = new FabricaSVG<>(elementos);
        return fabricaSVG.obtenerEstructura(estructura).dibujaSVG();
    }

}