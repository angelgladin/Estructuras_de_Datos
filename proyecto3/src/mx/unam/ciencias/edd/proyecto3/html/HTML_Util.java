package mx.unam.ciencias.edd.proyecto3.html;

/**
 * @author Angel Gladin
 * @version 1.0
 * @since 26/05/2016.
 */
class HTML_Util {
    static final String PROJECT_NAME = "Proyecto 3: Estructuras de datos";
    static final String PROJECT_NAME_DESCRIPTION = "Generador de reportes de texto";
    static final String GRAPH_EXPLANATION = "Representación de los archivos por medio de una gráfica;" +
            " donde los vértices son los archivos y son vecinos si ambos contienen al menos " +
            "10 palabaras en común, con al menos 5 caracteres cada una.";
    static final String FILE_IMAGE_URL_TAG = "<img src='http://www.iconsdb.com/icons/preview/black/text-file-3-xxl.png' width='60' height='60' />";
    static final String NUMBER_WORDS_MSG = "Número de palabras: ";

    static String get_CSS(){
        return "/** A4 ***/page[size='A4'] {background: white;width: 21cm;height: 29.7cm;display: block;margin: " +
                "0 auto;margin-bottom: 0.5cm;box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5);}@media print " +
                "{body,page[size='A4'] {margin: 0;box-shadow: 0;}}/*** Global styles ***/html {width: 100%;height:" +
                " 100%;}body {font-family: 'Trebuchet MS', Arial, Helvetica, sans-serif;" +
                "font-size: 13px;background-position: left top;background-repeat: repeat-x;margin: 0 0 1px;height: 100%;" +
                "line-height: 1.5;}a:hover {color: white;}h1,h2,h3,{font-family: 'Trebuchet MS', Arial, Helvetica," +
                " sans-serif;position: relative;}h1 {font-size: 28px;line-height: 0.9;color: #0F9795;text-align: left;" +
                "}h2 {font-size: 20px;line-height: 1.3;color: #000000;text-transform: none;text-align: left;" +
                "}h3 {font-size: 23px;line-height: 0.9;color: #0F9795;}h4{font-size: 13px;line-height: 1.1;" +
                "color: #000000;text-transform: none;text-align: left;}/*** Main containers ***/#container " +
                "{width: 100%;position: relative;margin: 0 auto;background-color: transparent;" +
                "min-height: 100%;}.group1 {float: left;width: 230px;background: transparent;padding: 15px;" +
                "margin-bottom: 10px;}.group5 {margin-left: auto;margin-right: auto;width: 800px;padding: 15px;" +
                "}/*** Button ***/.button {width: 80px;height: 16px;position: relative;margin-top: 12px;padding: 9px;" +
                "background: #E9EAEB;display: inline-block;color: #0F9795;font-weight: bold;" +
                "cursor: pointer;text-align: center;font-family: 'Trebuchet MS', Arial, Helvetica, sans-serif;" +
                "text-decoration: none;-moz-box-shadow: 2px 2px #D7D7D7;-webkit-box-shadow: 2px 2px #D7D7D7;}" +
                ".button:hover {text-decoration: underline;}";
    }
}
