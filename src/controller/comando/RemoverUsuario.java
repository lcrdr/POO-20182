package controller.comando;

import dao.EmprestimoDAO;
import dao.UsuarioDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Usuario;

import java.util.Scanner;

public class RemoverUsuario implements Command {
    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO udao = UsuarioDAOProxy.getInstance();
        EmprestimoDAO edao = EmprestimoDAOProxy.getInstance();

        System.out.println("Entre com o código do usuário a ser removido:");
        Usuario usuario = udao.getUsuario(entrada.nextInt());
        entrada.nextLine();

        if(usuario.getMultas().isEmpty() && edao.listEmprestimo(usuario).isEmpty()){
            udao.remove(entrada.nextInt());

            System.out.println("Usuario removido com sucesso.");
        }else{
            System.out.println("O usuário não pode ser removido pois possui multa(s) a pagar ou livro(s) a devolver.");
        }

        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
