package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.Estructura;

public class FabricaSVG<T> {

    private Coleccion<T> coleccion;

    public FabricaSVG(Coleccion<T> coleccion) {
        this.coleccion = coleccion;
    }

    public EstructuraSVG obtenerEstructura(Estructura estructura) {
        if (estructura == Estructura.LISTA || estructura == Estructura.COLA
                || estructura == Estructura.PILA) {
            return new GraficaEstructura.Lista((Lista<T>) coleccion, estructura);
        }

        return null;
    }

}