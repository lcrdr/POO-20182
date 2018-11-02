# POO-20182
Requisitos - Atualizado em 11/10

Sistema de Controle de Biblioteca


Requisitos Funcionais

Adicionais:<b>
- Sistema de Login;
- Não cadastrar livro sem autor/categoria;
- Unificar interface;
- Reservar livro na data de devolução (criar fila de reserva por data de hora);
- Implementar sistema de devolução de emprestimo;
- Usuário não pode emprestar se estiver devendo (?);
- Só excluir usuário se todos os empréstimos estiverem devolvidos;
- Só excluir livro se todos os empréstimos dele estiverem devolvidos (?).
  
</b>

<b>DONE</b> - RF01 – o sistema deve permitir ao bibliotecário cadastrar livros contendo código, autor,
categoria, ano, prioridade e disponibilidade.


<b>DONE</b> - RF02 – o sistema deve permitir ao bibliotecário cadastrar usuários contendo código, nome,
sexo, categoria, endereço e telefone.


<b>DONE</b> - RF03 – o sistema deve permitir ao bibliotecário cadastrar empréstimos contendo código,
código do livro, código do usuário, data de empréstimo e data de devolução.


RF04 – o sistema deve permitir ao bibliotecário cadastrar multas contendo código, código do
cliente, descrição e valor.


<b>DONE</b> - RF05 – o sistema deve permitir aos usuários consultarem os livros podendo filtrar por nome,
autor, categoria e disponibilidade.


<b>DONE</b> - RF06 – a data de devolução será calculada de acordo com a categoria do usuário (professor,
funcionário, aluno, comunidade). Professores têm prazo de 15 dias. Funcionários têm prazo de
12 dias. Alunos têm prazo de 20 dias. Pessoas da comunidade têm prazo de 8 dias.


RF07 – o valor da multa será de $1 por dia de atraso se o livro for de prioridade baixa, se o livro
for de prioridade média será de $2 e se for de prioridade alta $3. Além disso será adicionado o
valor de $0,5 por dia se o usuário for professor, $0,3 se for funcionário, $0,2 se for aluno e $0,1
se for da comunidade.


<b>DONE</b> - RF08 – o sistema deve permitir ao bibliotecário alterar e remover livros.


<b>DONE</b> - RF09 – o sistema deve permitir ao bibliotecário alterar e remover usuários.


<b>DONE</b> - RF10 – o sistema deve permitir ao bibliotecário consultar os livros, os empréstimos e os
usuários.


<b>DONE</b> - RF11 – o sistema deve ter uma interface para o bibliotecário em linha de comando.


<b>DONE</b> - RF12 – o sistema deve ter uma interface para o usuário em linha de comando.


RF13 – o sistema deve permitir ao bibliotecário gerar relatórios das consultas em 3 formatos
possíveis: CSV, XML, JSON.


RF14 – o sistema deve permitir ao usuário reservar livros que não estejam disponíveis.


Requisitos não funcionais


<b>DONE</b> - RNF1 – o sistema prezar pela eficiência, evitando ir ao banco quando não for necessário. Para
tanto deve ter um sistema de cache.


RNF2 – o sistema deve evitar repetição desnecessária de código.


RNF3 – o sistema deve estar preparado para ser expandido sem a necessidade de muitas
mudanças no código que já está em funcionamento.


<b>DONE</b> - RNF4 – o sistema deve usar o banco de dados hsqldb.
