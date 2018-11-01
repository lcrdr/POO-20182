package model.categoriausuario;

public class Funcionario implements CategoriaUsuario {

    int id = 2;

    @Override
    public int getDias() {
        return 12;
    }

    @Override
    public double getMulta() {
        return 0.3;
    }

    @Override
    public String toString() {
        return "Funcionario";
    }

    public int getIdCategoria(){
        return id;
    }
}
