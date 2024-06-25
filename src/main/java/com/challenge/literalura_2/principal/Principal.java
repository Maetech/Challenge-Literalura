package com.challenge.literalura_2.principal;

import com.challenge.literalura_2.model.Autor;
import com.challenge.literalura_2.model.DatosConsulta;
import com.challenge.literalura_2.model.DatosLibro;
import com.challenge.literalura_2.model.Libro;
import com.challenge.literalura_2.repository.AutorRepository;
import com.challenge.literalura_2.repository.LibroRepository;
import com.challenge.literalura_2.service.ConsumoAPI;
import com.challenge.literalura_2.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String URL_BUSQUEDA = "?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private AutorRepository autorRepository;
    private LibroRepository libroRepository;

    public Principal(AutorRepository autorRepository,LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void muestraMenu(){
        int opcion = -1;
        var menu = """
                ********************************
                    Hola usuario. Por favor elija una opcion del menu
                    ********************************
                    1. Buscar libro por titulo
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    6. Listar Top10 Libros
                    0. Salir del menu
                """;

        while(opcion != 0){
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosIdioma();
                    break;
                case 6:
                    listarTop10Libros();
                    break;
                case 0:
                    System.out.println("Gracias por utilizar nuestra aplicacion. Vuelva pronto");
                    break;
                default:
                    System.out.println("Opcion no valida, por favor, vuelva a intentar");
                    opcion = teclado.nextInt();
            }
        }

    }

    //Metodo para obtener los datos de la API gutendex de un libro
    private DatosConsulta getDatosLibro(String libroBuscado){
        var json = consumoAPI.consumoDatos(URL_BASE + URL_BUSQUEDA + libroBuscado.toLowerCase()
                .replace(" " , "+"));
        var datosApi = conversor.convierteDatos(json, DatosConsulta.class);
        return datosApi;
    }

    //Metodo para obtener obtener y guardar los datos de un libro buscado por el usuario
    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desee buscar");
        String libroBuscado = teclado.nextLine();

        var datosLibroApi = getDatosLibro(libroBuscado);

        Optional<DatosLibro> libroApi = datosLibroApi.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(libroBuscado.toLowerCase()))
                .findFirst();

        Optional<Libro> libroDB = libroRepository.findByTituloContainsIgnoreCase(libroBuscado);

        //Evitar que un libro que ya se busco se agregue nuevamente en la DB
        if(libroDB.isPresent()){
            System.out.println("El libro ya se encuentra registrado en la Base de Datos");
            System.out.println(libroDB.get());

        } else if (libroApi.isPresent()) {
            List<Autor> autores = libroApi.get().autor().stream()
                    .map(a -> autorRepository.findByNombreContainsIgnoreCase(a.nombreAutor())
                            .orElseGet(() -> autorRepository.save(new Autor(a))))
                    .collect(Collectors.toList());

            Libro libroNuevo = new Libro(libroApi.get(), autores);
            libroRepository.save(libroNuevo);
            System.out.println(libroNuevo);
        } else {
            System.out.println("Libro no encontrado");
        }

    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        System.out.println("Los libros registrados en la DB son: ");
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        System.out.println("Los autores registrados en la DB son: ");
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingresa el año que quieres consultar que escritores estaban vivos: ");
        Integer fecha = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autoresVivos = autorRepository.filtrarAutoresPorFecha(fecha);
        autoresVivos.forEach(System.out::println);
    }

    private void listarLibrosIdioma() {
        String idioma = "";
        System.out.println("Por favor, seleccione un idioma");
        System.out.println("""
                1. Espanol (es)
                2. Ingles (en)
                3. Portugues (pt)
                """);
        var opcion = teclado.nextInt();
        if(opcion == 1){
            idioma = "es";
        } else if (opcion == 2){
            idioma = "en";
        } else if (opcion == 3){
            idioma = "pt";
        } else {
            idioma = null;
            System.out.println("Opcion no valida");
        }

        List<Libro> librosPorIdioma = libroRepository.findByIdiomaContaining(idioma);
        if(librosPorIdioma.isEmpty()){
            System.out.println("No se encontraron libros en el idioma seleccionado");
        } else {
            librosPorIdioma.forEach(System.out::println);
        }
    }

    private void listarTop10Libros() {
        List<Libro> top10Libros = libroRepository.findTop10ByOrderByNumeroDescargasDesc();
        System.out.println("El Top 10 de libros basado en el numero de descargas dentro de la base es: ");
        top10Libros.forEach(System.out::println);

    }

}
