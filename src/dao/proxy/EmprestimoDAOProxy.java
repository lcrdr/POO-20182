package dao.proxy;

import dao.EmprestimoDAO;
import model.Emprestimo;

import java.util.List;

public class EmprestimoDAOProxy extends EmprestimoDAO {
    private static EmprestimoDAOProxy instance;
    private List<Emprestimo> cache;

    private EmprestimoDAOProxy() { }

    @Override
    public void insert(Emprestimo emprestimo) {
        super.insert(emprestimo);
        cache = null;
    }

    @Override
    public List<Emprestimo> listEmprestimo() {
        if(cache == null) {
            cache = super.listEmprestimo();
        }
        return cache;
    }

    @Override
    public void update(Emprestimo emprestimo) {
        super.update(emprestimo);
        cache = null;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        cache = null;
    }

    public static synchronized EmprestimoDAOProxy getInstance() {
        if(instance == null)
            instance = new EmprestimoDAOProxy();
        return instance;
    }
}
