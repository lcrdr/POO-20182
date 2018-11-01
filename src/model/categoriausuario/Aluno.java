package model.categoriausuario;

public class Aluno implements CategoriaUsuario{

    int id = 3;

    @Override
    public int getDias() {
        return 20;
    }

    @Override
    public double getMulta() {
        return 0.2;
    }

    @Override
    public String toString() {
        return "Aluno";
    }

    public int getIdCategoria(){
        return id;
    }
}
