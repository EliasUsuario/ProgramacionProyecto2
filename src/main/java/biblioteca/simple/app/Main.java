package biblioteca.simple.app;

import biblioteca.simple.contratos.Prestable;
import biblioteca.simple.modelo.Formato;
import biblioteca.simple.modelo.Libro;
import biblioteca.simple.modelo.Pelicula;
import biblioteca.simple.modelo.Producto;
import biblioteca.simple.modelo.Usuario;
import biblioteca.simple.modelo.Videojuego;
import biblioteca.simple.servicios.Catalogo;
import biblioteca.simple.servicios.SocioUsuario;
import biblioteca.simple.modelo.Plataforma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Clase principal de la aplicacion de consola para la gestion de una biblioteca.
public class Main {

    // Atributos
    private static final Catalogo catalogo = new Catalogo();

    private static final List<Usuario> usuarios =new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        menu();
    }

    //Carga del conjunto de productos y usuarios iniciales en la aplicación.
    private static void cargarDatos(){
    
        catalogo.alta(new Libro(1, "El Quijote", "1608", Formato.FISICO, "25225", "Cervantes"));
        catalogo.alta(new Libro(2, "El nombre del viento", "2007", Formato.FISICO, "9788401352836", "Patrick Rothfuss"));
        catalogo.alta(new Pelicula(3, "El Padrino", "1972", Formato.FISICO, "rancis Ford Coppola", 175));
        catalogo.alta(new Pelicula(4, "Parásitos", "2019", Formato.FISICO, "Bong Joon-ho", 132));

        catalogo.alta(new Videojuego(6, "Sonic", "2021", Formato.DIGITAL, Plataforma.NINTENDO, 6, "Sega", 8.00));
        catalogo.alta(new Videojuego(7, "Mario", "2023", Formato.DIGITAL, Plataforma.XBOX, 9, "Nintendo", 8.34));

        //Carga de usuarios iniciales

        usuarios.add(new Usuario(1, "Juan"));
        usuarios.add(new Usuario(2, "María"));

    }

    //Menu principal de la aplicacion
    private static void menu(){
        int op;
        do {
            System.out.println("\n***Menú de Biblioteca***");
            System.out.println("1. Listar productos");
            System.out.println("2. Buscar por título");
            System.out.println("3. Buscar por año");
            System.out.println("4. Prestar producto");
            System.out.println("5. Devolver producto");
            System.out.println("6. Crear usuario");
            System.out.println("7. Exportar usuarios a JSON");
            System.out.println("8. Importar usuarios desde JSON");
            System.out.println("0. Salir");

            while(!sc.hasNextInt()) sc.next();
            op = sc.nextInt();
            sc.nextLine();

            switch (op){
                case 1 -> listar();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorAnio();
                case 4 -> prestar();
                case 5 -> devolver();
                case 6 -> crearUsuario();
                case 7 -> exportarUsuarios();
                case 8 -> importarUsuarios();
                case 0 -> System.out.println("Saliendo del programa");
                default -> System.out.println("Opción no válida");
            }
        } while(op != 0);
    }

    //Listar todos los productos
    private static void listar(){
        List<Producto> lista = catalogo.listar();
        if(lista.isEmpty()){
            System.out.println("Catálogo vacío");
            return;
        }
        System.out.println("Lista de productos");
        for(Producto p : lista) System.out.println("- " + p);
    }

    //Buscar productos por titulo
    private static void buscarPorTitulo(){
        System.out.println("Título (escribe parte del título): ");
        String t = sc.nextLine();
        catalogo.buscar(t).forEach(p -> System.out.println("- " + p));
    }

    //Buscar productos por año
    private static void buscarPorAnio(){
        System.out.println("Año: ");
        while(!sc.hasNextInt()) sc.next();
        int a = sc.nextInt();
        sc.nextLine();
        catalogo.buscar(a).forEach(p -> System.out.println("- " + p));
    }

    //Listar todos los usuarios
    private static void listarUsuarios(){
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
            return;
        }
        System.out.println("Lista usuarios");
        usuarios.forEach( u ->
                        System.out.println("- Código : " + u.getId() + "| Nombre: " + u.getNombre() )
        );
    }

    //Devuelve un usuario por su codigo
    private static Usuario getUsuarioPorCodigo(int id){
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //Prestar un producto
    private static void prestar(){

// 1)Mostrar productos disponibles

        List<Producto> disponibles = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && !pN.estaPrestado())
                .collect(Collectors.toList());

        if ( disponibles.isEmpty() ) {
            System.out.println("No hay productos para prestar");
            return;
        }

        System.out.println("-- PRODUCTOS DISPONIBLES --");
        disponibles.forEach( p -> System.out.println("- ID: " + p.getId() + " | " + p));

        System.out.println("Escribe el id del producto: ");

        while(!sc.hasNextInt()) sc.next();
        int id = sc.nextInt();
        sc.nextLine();

// 2)Buscar el producto por id
        Producto pEncontrado = disponibles.stream()
        .filter(p -> {
            try {
                return p.getId() == id;
            } catch (NumberFormatException e) {
                return false;
            }
        })
        .findFirst()
        .orElse(null);

// 3)Comprobar si el producto existe
        if (pEncontrado == null){
            System.out.println("El id no existe");
            return;
        }

        // Aplicamos la opción de crear un usuario nuevo si al hacer el préstamo el usuario no existe
        Usuario u1 = null;
        int opcion;

        do {
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Prestar a un usuario existente");
            System.out.println("2. Crear un nuevo usuario y prestar");
            while(!sc.hasNextInt()) sc.next();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> {
// 4)Mostrar usuarios
                    listarUsuarios();

                    System.out.println("Ingresa código de usuario");

                    while (!sc.hasNextInt()) sc.next();
                    int cUsuario = sc.nextInt();
                    sc.nextLine();
                    u1 = getUsuarioPorCodigo(cUsuario);

                    if (u1 == null) {
                        System.out.println("Usuario no encontrado");
                        return;
                    }
                }
                case 2 -> {
// 5)Crear un nuevo usuario
                    u1 = crearUsuario();
                }
                case 3 -> {
                    System.out.println("Préstamo cancelado");
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        }while(!(opcion == 1 || opcion == 2));

// 6)Prestar el producto
        Prestable pPrestable = (Prestable) pEncontrado;
        pPrestable.prestar(u1);
    }

//Devolver un producto
    public static void devolver(){
        List<Producto> pPrestados = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && pN.estaPrestado())
                .collect(Collectors.toList());

        if ( pPrestados.isEmpty() ) {
            System.out.println("No hay productos prestados");
            return;
        }
// 2)Mostrar productos prestados
        System.out.println("-- PRODUCTOS PRESTADOS --");
        pPrestados.forEach( p -> System.out.println("- ID: " + p.getId() + " | " + p));

// 3)Buscar el producto por id
        System.out.println("Escribe el id del producto: ");
        while(!sc.hasNextInt()) sc.next();
        int id = sc.nextInt();
        sc.nextLine();

        Producto pEncontrado = pPrestados.stream()
                .filter(p -> {
                    try {
                        return p.getId() == id;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })

                .findFirst()
                .orElse(null);

        if (pEncontrado == null){
            System.out.println("El id no existe");
            return;
        }
// 5)Devolver el producto
        Prestable pE = (Prestable) pEncontrado;
        pE.devolver();
        System.out.println("Devuelto correctamente");

    }

    public static Usuario crearUsuario() {
        System.out.println("¿Cuál es el nombre del nuevo usuario?");
// Aunque el nombre ya exista no lo comprobamos porque puede haber dos usuarios con el mismo nombre
        String nombre = sc.nextLine();

        int id;
// Sin embargo, sí comprobamos el "id" del usuario porque este es único
        boolean idValido;
        do {
            idValido = true;
            System.out.println("¿Cuál es el número de código del nuevo usuario?");
            while (!sc.hasNextInt()) sc.next();
            id = sc.nextInt();
            sc.nextLine();
            for (Usuario u : usuarios) {
                if (id == u.getId()) {
                    System.out.println("Ya existe un usuario con ese código");
                    idValido = false;
                    break;
                }
            }
        } while (!idValido);
// 6)Crear el usuario
        Usuario nuevoUsuario = new Usuario(id, nombre);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario " + nombre + " con código " + id + " creado correctamente.");
        return nuevoUsuario;
    }

    private static void exportarUsuarios() {
        try {
//Exportar usuarios
            SocioUsuario.exportar(usuarios);
            System.out.println("Usuarios exportados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al exportar usuarios" + e.getMessage());;
        }
    }

    private static void importarUsuarios() {
        try {
//Importar usuarios
            List<Usuario> cargados = SocioUsuario.importar();
            usuarios.clear();
            usuarios.addAll(cargados);
            System.out.println("Usuarios cargados con éxito");
        } catch (Exception e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

}