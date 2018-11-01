package model;

import model.categoriausuario.CategoriaUsuario;

public class Usuario {
    private int codigo;
    private String nome;
    private boolean sexo;
    private String endereco;
    private String telefone;
    private CategoriaUsuario categoriaUsuario;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCategoriaUsuario(CategoriaUsuario categoriaUsuario){
        this.categoriaUsuario = categoriaUsuario;
    }

    public CategoriaUsuario getCategoriaUsuario() {
        return categoriaUsuario;
    }

    public Usuario(int codigo, String nome, boolean sexo, String endereco, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Código: " + getCodigo() + "\n" + "Nome: " + getNome() + "\n" + "Sexo: " + (getSexo() == true ? "Masc" : "Fem") + "\n" + "Endereço: " + getEndereco() + "\n" + "Telefone: " + getTelefone() + "\n" + "Categoria: " + categoriaUsuario;
    }

}
