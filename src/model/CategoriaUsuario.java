package model;

public class CategoriaUsuario {
    private int codigo;
    private String nome;
    private int devolucao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(int devolucao) {
        this.devolucao = devolucao;
    }

    public CategoriaUsuario(int codigo, String nome, int devolucao) {
        this.codigo = codigo;
        this.nome = nome;
        this.devolucao = devolucao;
    }

    public CategoriaUsuario() {
    }
}
