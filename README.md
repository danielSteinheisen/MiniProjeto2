# Mini Projeo 02

Mini projeto 02 do curso FullStack Education do Floripa Mais Tech (FMT).

### Integrantes
- Daniel Alan Steinheisen
- Leonardo Vieira
- João Guilherme Ito Cypriano

### Ex1 - Setup do projeto de notas
Desenvolva um sistema simples para cálculo da média final dos alunos em diferentes disciplinas.

A plataforma deve conter:

- Matrícula de alunos em disciplinas;
- Lançamento de notas;
- Cálculo de média final de cada aluno por disciplina.

Para uma especificação básica das entidade, siga o seguinte diagrama:
![MER](https://github.com/danielSteinheisen/MiniProjeto2/blob/main/MER.png)

### Ex 2 - CRUD Alunos
Criar CRUD para a entidade Aluno.

Utilize adequadamente os padrões REST, MVC e os Logs.

Não se esqueça do tratamento de exceções, status de resposta e seus métodos HTTP.

### Ex 3 - CRUD Professores
Criar CRUD para a entidade Professor.

Utilize adequadamente os padrões REST, MVC e os Logs.

Não se esqueça do tratamento de exceções, status de resposta e seus métodos HTTP.

### Ex 4 - CRUD Disciplinas
Criar CRUD para a entidade Disciplina.

Utilize adequadamente os padrões REST, MVC e os Logs.

Não se esqueça do tratamento de exceções, status de resposta e seus métodos HTTP.

### Ex 5 - Matricular alunos
Matricular Alunos em Disciplinas.
Métodos disponíveis no controlador:

- **POST:**
  -Deve receber no body apenas os IDs de aluno e disciplina. Os demais campos devem ser utilizados valores padrões.

- **DELETE:**
  - Deve receber apenas o id no *PathVariable*;
  - Validar se existe notas já lançadas:
    - Caso exista, informar a falha ao cliente;
    - Caso **NÃO** exista, deve excluir a matrícula.

- **GET** Por ID: 
  - Deve receber apenas o id no *PathVariable*;
  - Retornar uma matrícula que tenha o ID informado.

- **GET** Por aluno:
  - Deve receber apenas o id de aluno no *PathVariable*;
  - Retornar todas as matrículas pertencentes à um aluno.

- **GET** Por disciplina:
  - Deve receber apenas o id da matrícula no *PathVariable*;
  - Retornar todas as matrículas pertencentes à uma disciplina.

Utilize adequadamente os padrões **REST**, **MVC** e os **Logs**;
Não se esqueça do tratamento de exceções, status de resposta e seus métodos HTTP.

### Ex 6 - Lançamento de nota
Lançamento de notas;
Métodos disponíveis no controlador:

- **POST:**
  - Deve receber no body:
    - ID da matrícula;
    - nota;
    - coeficiente.
  - O professor deve ser atribuído o mesmo existente na disciplina;
  - Ao incluir uma nova nota, a média final da matrícula deve ser atualizada automaticamente:
    - **Atenção:** Ao calcular a média final, respeite sempre o coeficiente informado;
    - *Exemplo:*
      - **NOTAS:**
        - nota1: 5,0 → Coeficiente: 0.4;
        - nota2: 8,0 → Coeficiente: 0.4;
        - nota3: 1,0 → Coeficiente: 0.2.
      - **MÉDIA FINAL:** 5,4

- **DELETE:**
  - Deve receber apenas o id no *PathVariable*;
  - Ao excluir nota, a média final da matrícula deve ser atualizada automaticamente:
    - **Atenção:** Ao calcular a média final; respeite sempre o coeficiente informado.
    - *Exemplo:*
      - **NOTAS:**
        - nota1: 5,0 → Coeficiente: 0.4;
        - nota2: 8,0 → Coeficiente: 0.4;
        - --nota3: 1,0 → Coeficiente: 0.2-- **(Excluído)**;
      - **MÉDIA FINAL:** 5,2

- **GET** Por matrícula:
  - Deve receber apenas o id da matrícula no *PathVariable*;
  - Retornar as notas da matrícula que tenha o ID informado;

Utilize adequadamente os padrões **REST**, **MVC** e os **Logs**;
Não se esqueça do tratamento de exceções, status de resposta e seus métodos HTTP.

### Ex 7 - Média geral do aluno
Calcular a média geral de um aluno.
Criar no controlador de matrículas:

- Novo método **GET** Por aluno:
  - Deve receber apenas o id do aluno no *PathVariable*;
  - A média deverá ser calculada com a fórmula comum de média: `(n1 + n2 + n3 ......)/quantidadeNotas`
  - Retornar um DTO com apenas a média geral do aluno em todas as disciplinas.
    - *Exemplo:*
      - Disciplina 1: Média final 8,0;
      - Disciplina 2: Média final 6,0;
      - Disciplina 3: Média final 7,0;
    - **Média geral:** 7,0

Utilize adequadamente os padrões **REST**, **MVC** e os **Logs**.
Não se esqueça do tratamento de exceções, status de resposta e seus métodos HTTP.
