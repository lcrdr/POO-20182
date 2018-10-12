package model;

import java.util.List;

public class Livro {
    private int codigo;
    private String titulo;
    private int prioridade;
    private List<CategoriaLivro> categoria;
    private int ano;
    private boolean disponibilidade;
    private List<Autor> autor;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public List<CategoriaLivro> getCategoria() {
        return categoria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public Livro(int codigo, String titulo, int prioridade, List<CategoriaLivro> categoria, int ano, boolean disponibilidade) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.ano = ano;
        this.disponibilidade = disponibilidade;
    }

    public Livro() {
    }

    public void addAutor(Autor autor) {
        this.autor.add(autor);
    }

    public void removeAutor(Autor autor) {
        this.autor.remove(autor);
    }

    public void addCategoria(CategoriaLivro categoriaLivro) {
        this.categoria.add(categoriaLivro);
    }

    public void removeCategoria(CategoriaLivro categoriaLivro) {
        this.categoria.remove(categoriaLivro);
    }
}
