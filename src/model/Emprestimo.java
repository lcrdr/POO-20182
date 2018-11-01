package model;

import java.time.LocalDate;
import java.util.Date;

public class Emprestimo {
    private int codigo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Livro livro;
    private Usuario usuario;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Emprestimo() {
    }

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = LocalDate.now().plusDays(usuario.getCategoriaUsuario().getDias());
    }


    @Override
    public String toString() {
        return getCodigo() + " Emprestado por: " + getUsuario().getNome() + "\nLivro: " + getLivro().getTitulo() + "\nEmpréstimo em: " + getDataEmprestimo() + "\nDevolução: " + getDataDevolucao();
    }



}
