package dao.proxy;

import dao.CategoriaLivroDAO;
import model.CategoriaLivro;

import java.util.List;

public class CategoriaLivroDAOProxy extends CategoriaLivroDAO {
    private static CategoriaLivroDAOProxy instance;
    private List<CategoriaLivro> cache;

    private CategoriaLivroDAOProxy() { }

    @Override
    public void insert(CategoriaLivro categoriaLivro) {
        super.insert(categoriaLivro);
        cache = null;
    }

    @Override
    public List<CategoriaLivro> listCategoria() {
        if(cache == null) {
            cache = super.listCategoria();
        }
        return cache;
    }

    @Override
    public void update(CategoriaLivro categoriaLivro) {
        super.update(categoriaLivro);
        cache = null;
    }

    public static synchronized CategoriaLivroDAOProxy getInstance() {
        if(instance == null)
            instance = new CategoriaLivroDAOProxy();
        return instance;
    }
}
