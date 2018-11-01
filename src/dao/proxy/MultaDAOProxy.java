package dao.proxy;

import dao.MultaDAO;
import model.Multa;

import java.util.List;

public class MultaDAOProxy extends MultaDAO {
    private static MultaDAOProxy instance;
    private List<Multa> cache;

    private MultaDAOProxy() { }

    @Override
    public void insert(Multa multa) {
        super.insert(multa);
        cache = null;
    }

    @Override
    public List<Multa> listMulta() {
        if(cache == null) {
            cache = super.listMulta();
        }
        return cache;
    }

    @Override
    public void update(Multa multa) {
        super.update(multa);
        cache = null;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        cache = null;
    }

    public static synchronized MultaDAOProxy getInstance() {
        if(instance == null)
            instance = new MultaDAOProxy();
        return instance;
    }
}
