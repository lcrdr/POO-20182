package dao.proxy;

import dao.LivroDAO;
import model.Livro;

import java.util.List;

public class LivroDAOProxy extends LivroDAO {
    private static LivroDAOProxy instance;
    private List<Livro> cache;

    private LivroDAOProxy() { }

    @Override
    public void insert(Livro livro) {
        super.insert(livro);
        cache = null;
    }

    @Override
    public List<Livro> listLivro() {
        if(cache == null) {
            cache = super.listLivro();
        }
        return cache;
    }

    @Override
    public void update(Livro livro) {
        super.update(livro);
        cache = null;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        cache = null;
    }

    public static synchronized LivroDAOProxy getInstance() {
        if(instance == null)
            instance = new LivroDAOProxy();
        return instance;
    }
}
