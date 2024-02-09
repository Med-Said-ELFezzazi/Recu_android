package com.example.recu_android;

public class Libro {
    private int isbn;
    private String titulo;
    private String autor;
    private String editorial;

    public Libro(int isbn,String titulo,String autor,String editorial){
        this.isbn=isbn;
        this.titulo=titulo;
        this.autor=autor;
        this.editorial=editorial;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

}
