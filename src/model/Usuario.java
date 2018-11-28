package model;

import model.categoriausuario.Bibliotecario;
import model.categoriausuario.CategoriaUsuario;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int codigo = 0;
    private String nome = "";
    private boolean sexo = false;
    private String endereco = "";
    private String telefone = "";
    private CategoriaUsuario categoriaUsuario = new Bibliotecario();
    private List<Multa> multas = new ArrayList<>();
    private String login = "";
    private String senha = "";

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

    public String getSexoString() {
        return getSexo() == true ? "Masc" : "Fem";
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

    public List<Multa> getMultas() {
        return multas;
    }

    public void addMulta(Multa multa){
        multas.add(multa);
    }

    public void removeMulta(Multa multa){
        multas.remove(multa);
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
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
        return "Código: " + getCodigo() + "\n" + "Nome: " + getNome() + "\n" + "Sexo: " + getSexoString() + "\n" +
                "Endereço: " + getEndereco() + "\n" + "Telefone: " + getTelefone() + "\n" +
                "Categoria: " + categoriaUsuario + "\n" + "Login: " + getLogin() + "\n" + "Senha: " + getSenha() + "\n\n";
    }

}
