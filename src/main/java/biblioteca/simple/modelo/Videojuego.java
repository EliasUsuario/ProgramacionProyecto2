package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

public class Videojuego extends Producto implements Prestable {

    // Atributos propios de videojuego
    private Plataforma plataforma;
    private int edadMinima;
    private String desarrolladora;
    private double tamanioGB;

    // Control del estado del préstamo
    private boolean prestado;
    private Usuario prestadoA;

    // Constructor usado cuando el objeto proviene de una base de datos
    // (ya tiene un "id" asignado)
    public Videojuego(int id, String titulo, String anho, Formato formato, Plataforma plataforma, int edadMinima, String desarrolladora, double tamanioGB) {
        // Llama al constructor de la superclase Producto con "id"
        super(id, titulo, anho, formato);
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
        this.desarrolladora = desarrolladora;
        this.tamanioGB = tamanioGB;
    }

    //Constructor para crear un videojuego nuevo desde la aplicación
    // (el "id" se generará después o lo asignará la BD)
    public Videojuego(String titulo, String anho, Formato formato, Plataforma plataforma, int edadMinima, String desarrolladora, double tamanioGB) {
        //Llama al constructor Producto que no tiene "id"
        super(titulo, anho, formato);
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
        this.desarrolladora = desarrolladora;
        this.tamanioGB = tamanioGB;
    }

    // Getters para obtener información específica del videojuego
    public Plataforma getPlataforma() {return plataforma;}

    public int getEdadMinima() {return edadMinima;}

    public String getDesarrolladora() {return desarrolladora;}

    public double getTamanioGB() {return tamanioGB;}

    // Implementación del método de la interfaz Prestable
    @Override public void prestar(Usuario u) {
        if (prestado) throw new IllegalStateException("Ya prestado");
        prestado = true;
        this.prestadoA = u;
    }

    @Override public void devolver() {prestado = false; this.prestadoA = null;}

    @Override public boolean estaPrestado() {return prestado;}

    // Sobrescribimos toString() para representar toda la información
    // del videojuego en forma de texto (útil al imprimir por consola)
    @Override public String toString() {
        return  "Videojuego{" +
                "id=" + id +
                ", titulo='" + titulo + "'" +
                ", anho=" + anho +
                ", formato=" + formato +
                ", plataforma=" + plataforma +
                ", edadMinima=" + edadMinima +
                ", desarrolladora=" + desarrolladora +
                ", tamanioGB=" + tamanioGB +
                "}";
    }

}
