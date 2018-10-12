package model;

public class Multa {
    private int codigo;
    private Usuario usuario;
    private String descricao;
    private double valor;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Multa(int codigo, Usuario usuario, String descricao, double valor) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Multa() {
    }
}
