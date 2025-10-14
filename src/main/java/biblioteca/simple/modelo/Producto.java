package biblioteca.simple.modelo;

// Clase abstracta que representa un producto general (ej: libro, película, etc.)
// No se puede instanciar directamente; sirve como base para clases hijas.
public abstract class Producto {

    // Atributos comunes a todos los productos
    protected int id;          // Identificador único (cuando viene de la base de datos)
    protected String titulo;   // Título del producto
    protected String anho;     // Año de publicación, edición o lanzamiento
    protected Formato formato; // Tipo de formato (FISICO o DIGITAL, según el enum Formato)

    // Constructor pensado para crear objetos recuperados de la base de datos
    // Aquí ya se conoce el id porque fue asignado anteriormente (por la BD)
    protected Producto(int id, String titulo, String anho, Formato formato) {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.formato = formato;
    }

    // Constructor pensado para crear un producto nuevo desde la aplicación
    // No se tiene el id todavía (puede generarlo la BD después)
    protected Producto(String titulo, String anho, Formato formato) {
        this.titulo = titulo;
        this.anho = anho;
        this.formato = formato;
    }

    // Métodos "get" para acceder a los atributos
    // Están en protected, así pueden usarlos las subclases y otras clases del mismo paquete

    protected int getId() {
        return id;
    }

    protected String getTitulo() {
        return titulo;
    }

    protected String getAnho() {
        return anho;
    }

    protected Formato getFormato() {
        return formato;
    }

    // Método para mostrar la información del producto como texto
    // Sirve para imprimir en consola o depurar
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anho='" + anho + '\'' +
                ", formato=" + formato +
                '}';
    }
}
