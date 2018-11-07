package model.categoriausuario;

public class Bibliotecario implements CategoriaUsuario {

    int id = 5;

    @Override
    public int getDias() {
        return 0;
    }

    @Override
    public double getMulta() {
        return 0;
    }

    @Override
    public String toString() {
        return "Bibliotecario";
    }

    @Override
    public int getIdCategoria() {
        return id;
    }
}
