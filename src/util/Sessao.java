package util;

import model.Usuario;

public class Sessao {
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        Sessao.usuarioLogado = usuarioLogado;
    }

    private static Usuario usuarioLogado;
}
