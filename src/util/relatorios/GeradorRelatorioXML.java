package util.relatorios;

public class GeradorRelatorioXML implements GeradorRelatorio {

    private static GeradorRelatorioXML instance;

    private GeradorRelatorioXML(){}

    public static synchronized GeradorRelatorioXML getInstance() {
        if(instance == null)
            instance = new GeradorRelatorioXML();
        return instance;
    }

    @Override
    public void gerarRelatorioLivros() {

    }

    @Override
    public void gerarRelatorioUsuarios() {

    }

    @Override
    public void gerarRelatorioEmprestimos() {

    }
}
