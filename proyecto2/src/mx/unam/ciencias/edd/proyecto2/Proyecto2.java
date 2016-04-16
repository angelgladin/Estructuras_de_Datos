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

    //ESTO ES TEMPORAL SOLAMENTE ES PARA FILTRAR LA ENTRADA Y TRATARLA EN LO QUE METO LA GRAFICA.
    static Lista<String> relaciones = new Lista<>();

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

            while ((input = br.readLine()) != null) {
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
                    Integer a = null, b = null;
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < input.length() ; i++) {
                        if (Character.isDigit(input.charAt(i))) {
                            str.append(input.charAt(i));
                        } else if (input.charAt(i) == ',' && a == null) {
                            a = Integer.parseInt(str.toString());
                            str = new StringBuilder();
                        }
                        if (input.charAt(i) == ';' || i == input.length() - 1) {
                            b = Integer.parseInt(str.toString());
                            relaciones.agrega("relacion ~ " + a + " con " + b + "\n");
                            a = b = null;
                            str = new StringBuilder();
                        }
                    }
                    break;
                }
                c++;
            }
        } catch (IOException | FormatoInvalidoException | EstructuraInvalida e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        System.out.println(agregaElementosYRegresaSVG(estructura, elementos));
    }

    private static String agregaElementosYRegresaSVG(Estructura estructura, Lista<Integer> elementos) {
        FabricaSVG<Integer> fabricaSVG = new FabricaSVG<>(estructura);
        switch (estructura) {
            case LISTA:
            case COLA:
            case PILA:
                return fabricaSVG.obtenerPilaListaCola(elementos).dibujaSVG();
            case ARBOL_BINARIO_COMPLETO:
                arbolBinarioCompleto = new ArbolBinarioCompleto<>(elementos);
                return fabricaSVG.obtenerArbolBinarioCompleto(arbolBinarioCompleto).dibujaSVG();
            case ARBOL_BINARIO_ORDENADO:
                arbolBinarioOrdenado = new ArbolBinarioOrdenado<>(elementos);
                return fabricaSVG.obtenerArbolBinarioOrdenado(arbolBinarioOrdenado).dibujaSVG();
            case ARBOL_ROJINEGRO:
                elementos.forEach(arbolRojinegro::agrega);
                return fabricaSVG.obtenerArbolRojinegro(arbolRojinegro).dibujaSVG();
            case ARBOL_AVL:
                elementos.forEach(arbolAVL::agrega);
                return fabricaSVG.obtenerArbolAVL(arbolAVL).dibujaSVG();
            case GRAFICA:
                //TODO
                return relaciones.toString();
            default:
                return null;
            }
    }

}