package controller.comando;

import java.util.Scanner;

public class Sair implements Command {
    public void execute(Scanner entrada) {
        System.out.println("Sessão encerrada!");
    }
}
