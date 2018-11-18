package model;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MultaDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.MultaDAOProxy;

import java.time.LocalDate;

public class Emprestimo {
    private int codigo = 0;
    private LocalDate dataEmprestimo = LocalDate.now();
    private LocalDate dataDevolucao = LocalDate.now();
    private Boolean devolvido = false;
    private Livro livro = new Livro();
    private Usuario usuario = new Usuario();

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
        Multa multa = new Multa();
        dataDevolucao = LocalDate.now();

        if(atraso < 0){
            multa = new Multa(this,"Atraso no livro " + getLivro().getTitulo() + " no dia " + getDataDevolucao(), atraso);
            getUsuario().addMulta(multa);
            MultaDAO dao = MultaDAOProxy.getInstance();
            dao.insert(multa);
            System.out.println("Este livro foi devolvido atrasado, multa gerada para o usuario no valor de: R$" + multa.getValor());
        }

        EmprestimoDAO emprestimoDAO = EmprestimoDAOProxy.getInstance();
        emprestimoDAO.update(this);
        LivroDAO livroDAO = LivroDAOProxy.getInstance();
        livro.setDisponibilidade(true);
        livroDAO.update(this.getLivro());
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
        return "------------------\n" + getCodigo() + " Emprestado por: " + getUsuario().getNome() + "\nLivro: " + getLivro().getTitulo() + "\nEmpréstimo em: " + getDataEmprestimo() + "\nDevolução: " + getDataDevolucao() + "\n";
    }



}
