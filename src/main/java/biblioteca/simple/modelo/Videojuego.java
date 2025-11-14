package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;
import biblioteca.simple.modelo.Usuario;


public class Videojuego extends Producto implements Prestable {

    private String plataforma;
    private int edadMinima;
    private String desarrolladora;
    private double tamanioGB;

    private boolean prestado;
    private Usuario prestadoA;

    //Constructor desde ID
    public Videojuego(int id, String titulo, String anho, Formato formato, String plataforma, int edadMinima,String desarrolladora, double tamanioGB) {
        super(id, titulo, anho, formato);
        if (edadMinima < 0) {
            throw new IllegalArgumentException("Edad mínima no puede ser negativa");
        }
        if (tamanioGB <= 0) {
            throw new IllegalArgumentException("Tamaño en GB debe ser positivo");
        }
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
        this.desarrolladora = desarrolladora;
        this.tamanioGB = tamanioGB;
        this.prestado = false;
        this.prestadoA = null;
    }

    //Constructor desde menú

public Videojuego(String titulo, String anho, Formato formato, String plataforma, int edadMinima, String desarrolladora, double tamanioGB) {
        super(titulo, anho, formato);
        if (edadMinima < 0) {
            throw new IllegalArgumentException("Edad mínima no puede ser negativa");
        }
        if (tamanioGB <= 0) {
            throw new IllegalArgumentException("Tamaño en GB debe ser positivo");
        }
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
        this.desarrolladora = desarrolladora;
        this.tamanioGB = tamanioGB;
        this.prestado = false;
        this.prestadoA = null;
    }

// Getters y Setters

public String getPlataforma() {return plataforma;}

public void setPlataforma(String plataforma) {this.plataforma = plataforma;}

public int getEdadMinima() {return edadMinima;}

public void setEdadMinima(int edadMinima) {
    if (edadMinima < 0) {
        throw new IllegalArgumentException("Edad mínima no puede ser negativa");
    }
    this.edadMinima = edadMinima;
}

public String getDesarrolladora() {return desarrolladora;}

public void setDesarrolladora(String desarrolladora) {this.desarrolladora = desarrolladora;}

public double getTamanioGB() {return tamanioGB;}

public void setTamanioGB(double tamanioGB) {
    if (tamanioGB <= 0) {
        throw new IllegalArgumentException("Tamaño en GB debe ser positivo");
    }
    this.tamanioGB = tamanioGB;
}

public boolean isPrestado() {return prestado;}

public Usuario getPrestadoA() {return prestadoA;}

//Implementación de Prestable

@Override public void prestar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no puede ser nulo");
        }
        if (this.prestado) {
            throw new IllegalStateException("El videojuego ya está prestado.");
        }
        this.prestado = true;
        this.prestadoA = usuario;
}

@Override public void devolver() {
        if (!this.prestado) {
            throw new IllegalStateException("El videojuego no está prestado.");
        }
        this.prestado = false;
        this.prestadoA = null;
    }

@Override public boolean estaPrestado() {return this.prestado;}


//toString

@Override public String toString() {
        return " Videojuego{" +
            "plataforma='" + plataforma + '\'' +
            ", edadMinima=" + edadMinima +
            ", desarrolladora='" + desarrolladora + '\'' +
            ", tamanioGB=" + tamanioGB +
            ", prestado=" + prestado +
            (prestado ? ", prestadoA=" + prestadoA : "") +
            '}';
    }
}
