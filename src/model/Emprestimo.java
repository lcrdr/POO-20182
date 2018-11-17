package model;

import dao.EmprestimoDAO;
import dao.MultaDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.MultaDAOProxy;

import java.time.LocalDate;
import java.util.Date;

public class Emprestimo {
    private int codigo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Boolean devolvido;
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

    public Boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void devolver(){
        devolvido = true;

        int atraso = dataDevolucao.compareTo(LocalDate.now()); //Se dataDevolucao < LocalDate.now() retorna negativo

        Multa multa = null;
        dataDevolucao = LocalDate.now();

        if(atraso < 0){
            multa = new Multa(this,"Atraso no livro " + getLivro().getTitulo() + " no dia " + getDataDevolucao(), atraso);
        }

        getUsuario().addMulta(multa);
        MultaDAO dao = MultaDAOProxy.getInstance();
        dao.insert(multa);

        EmprestimoDAO emprestimoDAO= EmprestimoDAOProxy.getInstance();
        emprestimoDAO.update(this);
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
