package model;

import model.categoriausuario.CategoriaUsuario;

public class Usuario {
    private int codigo;
    private String nome;
    private boolean sexo;
    private String endereco;
    private String telefone;
    private CategoriaUsuario categoriaUsuario;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String login;
    private String senha;

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

    public Usuario(int codigo, String nome, boolean sexo, String endereco, String telefone, String login, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Código: " + getCodigo() + "\n" + "Nome: " + getNome() + "\n" + "Sexo: " + (getSexo() == true ? "Masc" : "Fem") + "\n" +
                "Endereço: " + getEndereco() + "\n" + "Telefone: " + getTelefone() + "\n" +
                "Categoria: " + categoriaUsuario + "\n" + "Login: " + getLogin() + "\n" + "Senha: " + getSenha();
    }

}
