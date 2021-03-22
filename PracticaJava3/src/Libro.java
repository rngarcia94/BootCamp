public class Libro {
    private String titulo;
    private String autor;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Libro(){
        this.autor = "";
        this.titulo = "";
    }

    public void prestamos(){}
    public void devolucion(){}

    @Override
    public String toString(){
        return ("Titulo: " + this.titulo + ", Autor: " + this.autor);
    }
}
