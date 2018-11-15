package controller.comando;

import util.relatorios.GeradorRelatorio;
import util.relatorios.GeradorRelatorioCSV;
import util.relatorios.GeradorRelatorioJSON;
import util.relatorios.GeradorRelatorioXML;

import java.util.Scanner;

public class GerarRelatorio implements Command {
    @Override
    public void execute(Scanner entrada) {
        System.out.println("Você deseja gerar o relatório como: \n1 - CSV\n2 - XML\n3 - JSON");
        int opcao = entrada.nextInt();
        entrada.nextLine();

        GeradorRelatorio gerador;

        switch(opcao){
            case 1:
                gerador = GeradorRelatorioCSV.getInstance();
                break;
            case 2:
                gerador = GeradorRelatorioXML.getInstance();
                break;
            case 3:
                gerador = GeradorRelatorioJSON.getInstance();
                break;
            default:
                return;
        }

        System.out.println("Você deseja gerar o relatório de: \n1 - Livros\n2 - Usuários\n3 - Empréstimos");
        opcao = entrada.nextInt();
        entrada.nextLine();

        switch(opcao){
            case 1:
                gerador.gerarRelatorioLivros();
                break;
            case 2:
                gerador.gerarRelatorioUsuarios();
                break;
            case 3:
                gerador.gerarRelatorioEmprestimos();
                break;
            default:
                return;
        }

        System.out.println("Relatório gerado com sucesso!\nPressione enter para continuar...");
        entrada.nextLine();
    }
}
