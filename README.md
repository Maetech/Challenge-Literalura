<h1 align="center"> Challenge Literalura </h1>

<h2>Descripcion del proyecto</h2>
<p>Esta aplicacion muestra un menu de opciones en el cual puede
  obtener los datos de diversos libros y autores utilizando la API gutendex: <a> https://www.exchangerate-api.com/](https://gutendex.com/books/ </a> </p>

## :hammer:Funcionalidades del proyecto

- `Funcionalidad General`: El usuario escribe el titulo de un libro, se obtienen los datos del libros, se imprimen en pantalla y se guardan en una base de datos (en este caso se utilizo PostgreSQL)
- `Otras funcionalidades`: Se pueden obtener los libros y autores guardados en la base de datos, asi como los autores vivos en cierta fecha, los libros por idioma y el top 10 libros usando el numero de descargas como parametro.

## ✔️ Tecnologias utilizadas
- `Java 17`
- `Programacion Orienrtada a Objetos`
- `Consumo de APIs`
- `Spring Framework`
- `Jackson Databind`
- `JPA`
- `PostgreSQL`
  
## ▶️ Imagenes del programa

<p> 1) <em>Vista inicial del programa </em></p>
<img src=Inicioprograma.png width = 800px height = 400px>

<p> 2) <em>Vista del programa al elegir la opcion 1. </em></p>
<p>Se le pide al usuario escribir el titulo del libro que desea buscar </p>
<img src=opcion1.png width = 800px height = 400px>

<p> 3) <em>Busqueda y guardado en la base de datos.</em> </p>
<p> El programa busca el libro en la API y guarda el libro en la base de datos </p>
<img src=busqueda.png width = 800px height = 400px>

<p> 4) <em> Validacion despues de que un libro ya se ha buscado y se encuentra guardado en la base de datos para que no vuelva a insertarse </em></p>
<img src=validacion.png width = 800px height = 400px>

<p> 5) <em> Opcion del menu para mostrar los libros guardados en la base de datos </em></p>
<img src=librosregistrados.png width = 800px height = 400px>

<p> 6) <em> Opcion del menu para mostrar los autores guardados en la base de datos </em></p>
<img src=autoresregistrados.png width = 800px height = 400px>

<p> 7) <em> Opcion del menu para pedir al usuario una fecha y mostrar los autores vivos dentro de la base de datos </em></p>
<img src=autorvivo.png width = 800px height = 400px>

<p> 8) <em> Opcion del menu para pedir al usuario un idioma y mostrar los libros en ese idioma dentro de la base de datos </em></p>
<img src=libroidioma.png width = 800px height = 400px>

<p> 9) <em> Opcion del menu para mostrar los 10 libros mas descargados dentro de la base de datos </em></p>
<img src=top10.png width = 800px height = 400px>
