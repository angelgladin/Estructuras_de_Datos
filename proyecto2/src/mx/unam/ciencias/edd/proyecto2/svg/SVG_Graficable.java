package mx.unam.ciencias.edd.proyecto2.svg;

/**
 * <p>Interfaz que será implementada a todas las clases que puedan ser graficables a SVG.</p>
 *
 * @author Angel Gladin
 * @version 1.0
 * @since 13/05/2016.
 */
public interface SVG_Graficable {
    /**
     * Metodo que regresa un String el cual es un svg.
     *
     * @return Un svg.
     */
    String drawSVG();
}