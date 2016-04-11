package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Pila;
import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.ArbolAVL;

///////////////
/////TODO: implementar SVG
///////////////

/**
 * <p>Proyecto 2</p>
 *
 * <p>.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 06/05/2016.
 */
public class Proyecto2 {

	static Lista<Integer> lista = new Lista<>();
	static Pila<Integer> pila = new Pila<>();
	static Cola<Integer> cola = new Cola<>();

	static ArbolBinarioCompleto<Integer> arbolBinarioCompleto = new ArbolBinarioCompleto<>();
	static ArbolBinarioOrdenado<Integer> arbolBinarioOrdenado = new ArbolBinarioOrdenado<>();
	static ArbolRojinegro<Integer> arbolRojinegro = new ArbolRojinegro<>();
	static ArbolAVL<Integer> arbolAVL = new ArbolAVL<>();

	public static void main(String[] args) {

		Estructura estructura = Estructura.NINGUNA;
		Lista<Integer> elementos = new Lista<>();

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
				if (c == 2 && estructura == Estructura.GRAFICA) {}
				c++;
			}
		} catch (IOException io) {
			System.out.println(io.getMessage());
			System.exit(-1);
		} catch (FormatoInvalidoException fi) {
			System.out.println(fi.getMessage());
			System.exit(-1);
		} catch (EstructuraInvalida ei) {
			System.out.println(ei.getMessage());
			System.exit(-1);
		}
		agregaElementosEstructura(estructura, elementos);

		System.out.println(toStringEstructura(estructura));
	}

	private static void agregaElementosEstructura(Estructura estructura, Lista<Integer> elementos) {
		for (Integer x : elementos) {
			switch (estructura) {
				case LISTA:
					lista.agrega(x);
					break;
				case PILA:
					break;
				case COLA:
					break;
				case ARBOL_BINARIO_COMPLETO:
					arbolBinarioCompleto.agrega(x);
					break;
				case ARBOL_BINARIO_ORDENADO:
					arbolBinarioOrdenado.agrega(x);
					break;
				case ARBOL_ROJINEGRO:
					arbolRojinegro.agrega(x);
					break;
				case ARBOL_AVL:
					arbolAVL.agrega(x);
					break;
				case GRAFICA:
					//TODO
					break;
				}
		}
	}

	private static String toStringEstructura(Estructura estructura) {
		switch (estructura) {
			case LISTA:
				return lista.toString();
			case PILA:
				//TODO
				return null;
			case COLA:
				//TODO
				return null;
			case ARBOL_BINARIO_COMPLETO:
				return arbolBinarioCompleto.toString();
			case ARBOL_BINARIO_ORDENADO:
				return arbolBinarioOrdenado.toString();
			case ARBOL_ROJINEGRO:
				return arbolRojinegro.toString();
			case ARBOL_AVL:
				return arbolAVL.toString();
			default:
				//TODO
				return null;
		}
	}

}