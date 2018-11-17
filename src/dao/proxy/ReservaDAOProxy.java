package dao.proxy;

import dao.ReservaDAO;
import model.Reserva;

import java.util.List;

public class ReservaDAOProxy extends ReservaDAO {
    private static ReservaDAOProxy instance;
    private List<Reserva> cache;

    private ReservaDAOProxy() {
    }

    @Override
    public void insert(Reserva reserva) {
        super.insert(reserva);
        cache = null;
    }

    @Override
    public List<Reserva> listReserva() {
        if (cache == null) {
            cache = super.listReserva();
        }
        return cache;
    }

    @Override
    public void update(Reserva reserva) {
        super.update(reserva);
        cache = null;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        cache = null;
    }

    public static synchronized ReservaDAOProxy getInstance() {
        if (instance == null)
            instance = new ReservaDAOProxy();
        return instance;
    }
}

