package model;

public class Multa {
    private int codigo;
    private String descricao;
    private double valor;
    private Emprestimo emprestimo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Multa(int codigo, Emprestimo emprestimo, String descricao, double valor) {
        this.codigo = codigo;
        this.emprestimo = emprestimo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Multa() {
    }

    public double calculaValor(){
        return 0;
    }

}
