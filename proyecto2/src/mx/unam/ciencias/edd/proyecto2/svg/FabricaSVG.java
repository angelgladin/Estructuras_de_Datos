package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.proyecto2.Estructura;
import mx.unam.ciencias.edd.proyecto2.excepciones.EstructuraInvalida;

public class FabricaSVG<T extends Comparable<T>> {

    private Estructura estructura;

    public FabricaSVG(Estructura estructura) {
        this.estructura = estructura;
    }

    public EstructuraSVG obtenerPilaListaCola(Lista<T> lista) {
        return new GraficaEstructura.Lista<T>(lista, estructura);
    }

    public EstructuraSVG obtenerArbolBinarioCompleto(ArbolBinarioCompleto<T> arbolBinarioCompleto) {
        return null;
    }

    public EstructuraSVG obtenerArbolBinarioOrdenado(ArbolBinarioOrdenado<T> arbolBinarioOrdenado) {
        return null;
    }

    public EstructuraSVG obtenerArbolRojinegro(ArbolRojinegro<T> arbolRojinegro) {
        return null;
    }

    public EstructuraSVG obtenerArbolAVL(ArbolAVL<T> arbolAVL) {
        return null;
    }

    public EstructuraSVG obtenerGrafica() {
        return null;
    }
}