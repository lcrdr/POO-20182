package util.relatorios;

public final class GeradorRelatorioJSON implements GeradorRelatorio {

    private static GeradorRelatorioJSON instance;

    private GeradorRelatorioJSON(){

    }

    public static synchronized GeradorRelatorioJSON getInstance() {
        if(instance == null)
            instance = new GeradorRelatorioJSON();
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
