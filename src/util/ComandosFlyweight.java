package util;

import model.categoriausuario.*;

import java.util.Hashtable;

public class ComandosFlyweight {
    private static ComandosFlyweight instance;

    Hashtable<Integer, CategoriaUsuario> categorias = new Hashtable<Integer, CategoriaUsuario>();

    private ComandosFlyweight(){
        categorias.put(1, new Professor());
        categorias.put(2, new Funcionario());
        categorias.put(3, new Aluno());
        categorias.put(4, new Comunidade());
    }

    public CategoriaUsuario getCategoria(Integer codigo){
        return categorias.get(codigo);
    }

    public static synchronized ComandosFlyweight getInstance() {
        if(instance == null)
            instance = new ComandosFlyweight();
        return instance;
    }
}
