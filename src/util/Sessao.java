package util;

import dao.MultaDAO;
import dao.proxy.MultaDAOProxy;
import model.Usuario;

public class Sessao {
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        MultaDAO multaDAO = new MultaDAOProxy();
        usuarioLogado.setMultas(multaDAO.listMulta(usuarioLogado));
        Sessao.usuarioLogado = usuarioLogado;
    }

    private static Usuario usuarioLogado;
}
