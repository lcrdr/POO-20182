package model.categoriausuario;

public class Comunidade implements CategoriaUsuario{

    int id = 4;

    @Override
    public int getDias() {
        return 8;
    }

    @Override
    public double getMulta() {
        return 0.1;
    }

    @Override
    public String toString() {
        return "Comunidade";
    }

    public int getIdCategoria(){
        return id;
    }
}
