package model;

public class Usuario {
    private int codigo;
    private String nome;
    private boolean sexo;
    private String endereco;
    private CategoriaUsuario categoria;
    private String telefone;

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

    public boolean getSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public CategoriaUsuario getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaUsuario categoria) {
        this.categoria = categoria;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario(int codigo, String nome, boolean sexo, String endereco, CategoriaUsuario categoria, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        this.categoria = categoria;
        this.telefone = telefone;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return getCodigo() + " " + getNome() + "\n" + getCategoria().getNome() + "\n" + (getSexo() == true ? "Masc" : "Fem") + "\n" + getEndereco() + "\n" + getTelefone();
    }
}
