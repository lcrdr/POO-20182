package dao.proxy;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;

public class UsuarioDAOProxy extends UsuarioDAO {
    private static UsuarioDAOProxy instance;
    private List<Usuario> cache;

    private UsuarioDAOProxy() { }

    @Override
    public void insert(Usuario usuario) {
        super.insert(usuario);
        cache = null;
    }

    @Override
    public List<Usuario> listUsuario() {
        if(cache == null) {
            cache = super.listUsuario();
        }
        return cache;
    }

    @Override
    public void update(Usuario usuario) {
        super.update(usuario);
        cache = null;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        cache = null;
    }

    public static synchronized UsuarioDAOProxy getInstance() {
        if(instance == null)
            instance = new UsuarioDAOProxy();
        return instance;
    }
}
