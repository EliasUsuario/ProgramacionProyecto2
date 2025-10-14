package biblioteca.simple.modelo;

// La clase Pelicula hereda de Producto, por lo que tiene todos sus atributos:
// id, titulo, anho y formato. Además, añade información específica de las películas.
public class Pelicula extends Producto {

    // Atributos propios de una película
    private String director;
    private int minutosDuracion;

    // Constructor usado cuando el objeto proviene de una base de datos
    // (ya tiene un id asignado)
    public Pelicula(int id, String titulo, String anho, Formato formato, String director, int minutosDuracion) {
        // Llama al constructor de la superclase Producto con id
        super(id, titulo, anho, formato);
        this.director = director;
        this.minutosDuracion = minutosDuracion;
    }

    // Constructor para crear una película nueva desde la aplicación
    // (el id se generará después o lo asignará la BD)
    public Pelicula(String titulo, String anho, Formato formato, String director, int minutosDuracion) {
        // Llama al constructor de Producto que no tiene id
        super(titulo, anho, formato);
        this.director = director;
        this.minutosDuracion = minutosDuracion;
    }

    // Métodos getter para acceder a los atributos propios

    public String getDirector() {
        return director;
    }

    public int getMinutosDuracion() {
        return minutosDuracion;
    }

    // Sobrescribimos toString() para representar toda la información
    // de la película en forma de texto (útil al imprimir por consola)
    @Override
    public String toString() {
        return "Pelicula{" +
                "director='" + director + '\'' +
                ", minutosDuracion=" + minutosDuracion +
                ", formato=" + formato +
                ", anho='" + anho + '\'' +
                ", titulo='" + titulo + '\'' +
                ", id=" + id +
                '}';
    }
}
