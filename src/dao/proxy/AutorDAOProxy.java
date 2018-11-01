package dao.proxy;

import dao.AutorDAO;
import model.Autor;

import java.util.List;

public class AutorDAOProxy extends AutorDAO {

    private static AutorDAOProxy instance;
    private List<Autor> cache;

    private AutorDAOProxy() { }

    @Override
    public void insert(Autor autor) {
        super.insert(autor);
        cache = null;
    }

    @Override
    public List<Autor> listAutor() {
        if(cache == null) {
            cache = super.listAutor();
        }
        return cache;
    }

    @Override
    public void update(Autor autor) {
        super.update(autor);
        cache = null;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        cache = null;
    }

    public static synchronized AutorDAOProxy getInstance() {
        if(instance == null)
            instance = new AutorDAOProxy();
        return instance;
    }
}