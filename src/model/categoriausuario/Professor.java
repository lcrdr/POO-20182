package model.categoriausuario;

public class Professor implements CategoriaUsuario {

    private int id = 1;

    @Override
    public int getDias() {
        return 15;
    }

    @Override
    public double getMulta() {
        return 0.5;
    }

    @Override
    public String toString() {
        return "Professor";
    }

    public int getIdCategoria(){
        return id;
    }
}
