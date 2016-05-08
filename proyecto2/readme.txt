
██████╗ ██████╗  ██████╗ ██╗   ██╗███████╗ ██████╗████████╗ ██████╗     ██████╗ 
██╔══██╗██╔══██╗██╔═══██╗╚██╗ ██╔╝██╔════╝██╔════╝╚══██╔══╝██╔═══██╗    ╚════██╗
██████╔╝██████╔╝██║   ██║ ╚████╔╝ █████╗  ██║        ██║   ██║   ██║     █████╔╝
██╔═══╝ ██╔══██╗██║   ██║  ╚██╔╝  ██╔══╝  ██║        ██║   ██║   ██║    ██╔═══╝ 
██║     ██║  ██║╚██████╔╝   ██║   ███████╗╚██████╗   ██║   ╚██████╔╝    ███████╗
╚═╝     ╚═╝  ╚═╝ ╚═════╝    ╚═╝   ╚══════╝ ╚═════╝   ╚═╝    ╚═════╝     ╚══════╝
                                                                                
Graficador de estructuras de datos.
Graficador de diferentes estructuras de datos a SVG.
El programa consiste en dada la entrada estandar o un archivo a leer,
se agregaran los elementos a una estructura de datos, en caso de 
ser gráfica también se harán relaciones, para después mostrar el 
resultado en la salida estándar.
Las estructuras de datos que se pueden graficar son las siguientes:
	Lista
	Pila
	Cola
	Árbol binario completo
	Árbol binario ordenado
	Árbol Rojinegro
	Árbol AVL
	Gráfica
	Montículo mínimo
Todas la estructuras de datos anteriores ya fueron enseñadas.

Autor: Ángel Gladín
Version: 1.0
Fecha de entrega: 13/05/2016.
                                                                                                                                                
█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗
╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝

Para compilar el proyecto ubicarse en la carpeta del proyecto y escribir:
ant

Para correr las pruebas unitarias ubicarse en la carpeta del proyecto y escribir:
ant test

Para ejecutar el programa ubicarse en la carpeta del proyecto y escribir:
java -jar proyecto2.jar
                                                                             
Para generar la documentación ubicarse en la carpeta del proyecto y escribir:
ant doc

Para "limpiar" el proyecto ubicarse en la carpeta del proyecto y escribir:
ant clean

█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗
╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝

El programa escribirá su salida en la salida estándard, y recibirá su 
entrada a través de un nombre de archivo o de la entrada estándar 
(si no se especifica ningún nombre de archivo en la línea de comandos).
En el caso de todas las estructuras distintas a gráficas, el formato 
del archivo es el siguiente:

# <NombreDeClase>
<Elementos>
En el caso de gráficas, el formato es el siguiente:

# Grafica
<Elementos>
<Relaciones>
Por ejemplo, el archivo para un arból rojinegro sería:

# ArbolRojinegro
1, 2, 3, 4, 5, 6, 7, 8, 9

Y para una gráfica:

# Grafica
1, 2, 3, 4, 5
1, 2; 1, 3; 1, 4; 1, 5; 2, 3; 2, 4; 2, 5; 3, 4; 3, 5; 4, 5

█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗
╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝

Dentro del proyecto existe en una carpeta llamada "testCases" con casos
prueba de la entrada y salida al proyecto.

█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗█████╗
╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝
