package mx.unam.ciencias.edd;

/**
 * Clase para fabricar picadillos.
 */
public class FabricaPicadillos {

    /**
     * Regresa una instancia de {@link Picadillo} para cadenas.
     * @param algoritmo el algoritmo de picadillo que se desea.
     * @return una instancia de {@link Picadillo} para cadenas.
     * @throws IllegalArgumentException si recibe un identificador no
     *         reconocido.
     */
    public static Picadillo<String> getInstancia(AlgoritmoPicadillo algoritmo) {
        if (algoritmo == AlgoritmoPicadillo.BJ_STRING) {

        } else if (algoritmo == AlgoritmoPicadillo.GLIB_STRING) {

        } else if (algoritmo == AlgoritmoPicadillo.XOR_STRING) {
            return (str) -> {
                byte[] k = str.getBytes();
                int l = k.length;
                int r = 0, i = 0;
                while (l >= 4) {
                    r ^= (k[i]    << 24)  | (k[i + 1] << 16) |
                         (k[i + 2] << 8)  | (k[i + 3]);
                    i += 4;
                    l -= 4;
                }
                int t = 0;
                switch (l) {
                    case 3: t |= k[i + 2] << 8;
                    case 2: t |= k[i + 1] << 16;
                    case 1: t |= k[i]     << 24;
                }
                r ^= t;
                return r;
            };
        }
        throw new IllegalArgumentException();
    }
}
