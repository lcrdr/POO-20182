package view;

import model.Usuario;

import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Usuario user = new Usuario();
        System.out.println("Login: ");
        user.setLogin(entrada.nextLine());
        System.out.println("Senha: ");
        user.setSenha(entrada.nextLine());


    }
}
