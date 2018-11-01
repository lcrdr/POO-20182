package model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int codigo;
    private String titulo;
    private int prioridade;
    private List<CategoriaLivro> categoria = new ArrayList<>();
    private int ano;
    private boolean disponibilidade;
    private List<Autor> autor = new ArrayList<>();

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

    public boolean getDisponibilidade() {
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

    public void addAutor(Autor autorAdd) {
        if(!(autor.contains(autorAdd))){
            this.autor.add(autorAdd);
        }
    }

    public void removeAutor(Autor autor) {
        this.autor.remove(autor);
    }

    public void addCategoria(CategoriaLivro categoriaLivro) {
        if(!(categoria.contains(categoriaLivro))){
            this.categoria.add(categoriaLivro);
        }
    }

    public void removeCategoria(CategoriaLivro categoriaLivro) {
        this.categoria.remove(categoriaLivro);
    }

    public void setCategoria(List<CategoriaLivro> categoria) {
        this.categoria = categoria;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Codigo:" + getCodigo() + "\n" + "Titulo:" + getTitulo() + "\n" + "Categoria(s):" + getCategoria() + "\n" +  "Autor(es):" + getAutor() + "\n" + "Ano:" + getAno() + "\n" + "Disponibilidade:" + (getDisponibilidade() ? "Disponível" : "Não disponível");
    }

}