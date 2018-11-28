package model;

public class Autor {
    private int codigo = 0;
    private String nome = "";

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

    public Autor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Autor() {
    }

    @Override
    public boolean equals(Object o) {
        return ((Autor)o).getCodigo() == getCodigo();
    }

    @Override
    public String toString() {
        return "Código: " + getCodigo() + "\n Nome: " + getNome() + "\n\n";
    }
}
