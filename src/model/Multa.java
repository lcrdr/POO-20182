package model;

public class Multa {
    private int codigo = 0;
    private String descricao = "";
    private double valor = 0;
    private Emprestimo emprestimo = new Emprestimo();

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

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Multa(Emprestimo emprestimo, String descricao, int atraso) {
        this.emprestimo = emprestimo;
        this.descricao = descricao;
        calculaValor(atraso);
    }

    public Multa() {
    }

    public void calculaValor(int atraso){
        setValor((emprestimo.getLivro().getPrioridade() * (double)(Math.abs(atraso))) + (emprestimo.getUsuario().getCategoriaUsuario().getMulta() * (double)(Math.abs(atraso))));
    }

    @Override
    public String toString() {
        return getCodigo() + "\n" + getDescricao() + "\n" + getValor();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Multa)obj).getCodigo() == this.getCodigo();
    }
}
