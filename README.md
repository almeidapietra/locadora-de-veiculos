# Sistema de Locadora de Veículos


Este projeto é um sistema de locação de veículos que permite o gerenciamento de agências, aluguéis, clientes e veículos. O sistema oferece funcionalidades para gerenciar todas as etapas do processo de aluguel de veículos. O projeto foi desenvolvido como projeto final do módulo III - programa Santander Coders - Back-end JAVA pela ADA TECH.

## Funcionalidades
Gerenciar Agências
Gerenciar Aluguéis
Gerenciar Clientes
Gerenciar Veículos
Sair do Sistema

## Diagrama de Classes

<img src="./src/diagrama 1.png" alt="imagem de diagrama de classes">


## Uso

A aplicação permite o gerenciamento completo de uma locadora de veículos. Abaixo estão os detalhes de cada funcionalidade:

- Gerenciar Agências
Como usar: Escolha a opção 1 no menu principal.
Descrição: Permite adicionar, remover e visualizar as agências cadastradas no sistema.

- Gerenciar Aluguéis
Como usar: Escolha a opção 2 no menu principal.
Descrição: Gerencia os aluguéis de veículos, incluindo o aluguel e devolução de veículos por clientes.

- Gerenciar Clientes
Como usar: Escolha a opção 3 no menu principal.
Descrição: Permite adicionar, editar, remover e buscar clientes no sistema.

- Gerenciar Veículos
Como usar: Escolha a opção 4 no menu principal.
Descrição: Gerencia o cadastro, edição, remoção e visualização dos veículos disponíveis na locadora.

- Sair do Sistema
Como usar: Escolha a opção 0 no menu principal.
Descrição: Encerra o sistema de locadora de veículos.


## Implementação das Funções

1. Gerenciar Agências

- Estruturas e Métodos Utilizados:
AgenciaDb: Classe que simula um banco de dados em memória para armazenar as agências cadastradas. A estrutura interna é baseada em uma ArrayList<Agencia>.
ArrayList: Utilizada para armazenar os objetos do tipo Agencia.
Método adicionarAgencia: Adiciona uma nova agência na lista, verificando se o código da agência já existe para evitar duplicidade.
Método listarAgencias: Retorna uma lista de todas as agências cadastradas, usando o método forEach da ArrayList para exibir os resultados.
Método removerAgencia: Localiza a agência com base no ID informado e remove da lista utilizando o método removeIf para filtrar a agência com o ID correspondente.
Tratamento de Exceções: Implementado para capturar e alertar o usuário caso tente adicionar uma agência com um código duplicado ou remover uma agência que não existe.

2. Gerenciar Aluguéis
- Estruturas e Métodos Utilizados:
AluguelDb: Classe que armazena e gerencia os registros de aluguel de veículos. Internamente, utiliza um ArrayList<Aluguel>.
ArrayList: Utilizada para armazenar os objetos do tipo Aluguel, que mantém informações sobre o cliente, veículo e datas de aluguel.
Método realizarAluguel: Verifica a disponibilidade do veículo e, se estiver disponível, registra o aluguel na lista.
Método devolverVeiculo: Verifica se o veículo está alugado, utilizando o método stream().filter para localizar o aluguel ativo. Após a confirmação, marca o veículo como devolvido.
Tratamento de Exceções: Caso o veículo já esteja alugado, o sistema avisa o usuário para evitar aluguéis duplicados. Da mesma forma, ao tentar devolver um veículo que não foi alugado, uma mensagem de erro é exibida.

3. Gerenciar Clientes
- Estruturas e Métodos Utilizados:
ClienteDb: Classe que simula o armazenamento de clientes, utilizando uma ArrayList<Cliente>.
ArrayList: Utilizada para armazenar os objetos do tipo Cliente.
Método adicionarCliente: Adiciona um cliente após validar se o CPF informado já está registrado na lista, utilizando o método stream().anyMatch para verificar duplicidade.
Método removerCliente: Remove um cliente com base no CPF, utilizando o método removeIf.
Método buscarCliente: Permite buscar um cliente pelo CPF, utilizando o método stream().filter para localizar o cliente na lista.
Tratamento de Exceções: Garante que CPFs duplicados não sejam cadastrados e que clientes inexistentes não sejam removidos.

4. Gerenciar Veículos
- Estruturas e Métodos Utilizados:
VeiculoDb: Classe que mantém os dados dos veículos disponíveis para aluguel. Os veículos são armazenados em um ArrayList<Veiculo>.
ArrayList: Usada para armazenar os veículos, permitindo que o sistema adicione, remova e atualize as informações dos veículos.
Método adicionarVeiculo: Adiciona um veículo na lista após verificar que o número de registro não está duplicado.
Método removerVeiculo: Localiza um veículo pelo número de registro e o remove da lista.
Método listarVeiculosDisponiveis: Retorna uma lista de veículos disponíveis para aluguel, utilizando o método stream().filter para filtrar veículos que não estão alugados.
Tratamento de Exceções: Ao tentar cadastrar veículos com o mesmo número de registro, uma exceção é lançada, e ao tentar remover ou alugar veículos que não existem, o sistema alerta o usuário.

5. Exibir Menu
- Estruturas e Métodos Utilizados:
Método System.out.println: Utilizado para exibir o menu de opções, permitindo que o usuário escolha qual funcionalidade deseja acessar.
Scanner: Usado para capturar a entrada do usuário e interpretar as escolhas realizadas.
Switch-case: Utilizado para gerenciar as diferentes opções do menu, redirecionando para as funções de gerenciamento correspondentes.
Tratamento de Exceções: Garante que o sistema exiba mensagens apropriadas quando o usuário entra com uma opção inválida no menu.

## Princípios SOLID aplicados

* Single Responsibility Principle (Princípio da Responsabilidade Única):

Cada classe ou interface no projeto tem uma única responsabilidade. Por exemplo, a classe ClienteDb é responsável apenas pelo gerenciamento de clientes, enquanto a VeiculoDb lida com a gestão dos veículos.

* Open/Closed Principle (Princípio Aberto/Fechado):

As interfaces do projeto, como AluguelVeiculo, são abertas para extensão (implementações específicas), mas fechadas para modificações. Novas funcionalidades podem ser adicionadas sem alterar o código existente.

* Liskov Substitution Principle (Princípio da Substituição de Liskov):

O projeto utiliza corretamente interfaces e classes genéricas, como IBancoDeDados<T>, garantindo que qualquer classe que implemente essa interface possa ser utilizada no lugar de outra sem quebrar o funcionamento.

* Interface Segregation Principle (Princípio da Segregação de Interfaces):

As interfaces são específicas para suas funções, como a interface AluguelVeiculo que trata apenas das operações relacionadas ao aluguel de veículos. Isso evita a criação de interfaces grandes e com responsabilidades múltiplas.
Dependency Inversion Principle (Princípio da Inversão de Dependência):

O sistema utiliza abstrações (interfaces) ao invés de classes concretas diretamente, promovendo a inversão de dependência. As classes dependem de interfaces como IBancoDeDados<T> ao invés de dependerem diretamente de implementações concretas.


## Divisão de Funções :


### 1 Veículos - Mayara
Cadastrar, alterar, buscar veículos: Implementar funcionalidades de cadastro de veículos (carro, moto, caminhão), garantir que não sejam duplicados (RN1), e criar a busca por parte do nome.
Regras de aluguel (RN4): Implementar a lógica para garantir que veículos alugados não apareçam como disponíveis.


### 2 Agências - Rosangela 
Cadastrar, alterar, buscar agências: Cuidar do gerenciamento das agências (cadastrar, alterar, buscar por nome ou endereço).
Garantir que as agências não sejam duplicadas (RN5).


### 3 Clientes - Gustavo Queiroz
Cadastrar, alterar, buscar clientes (pessoa física e jurídica): Implementar as funcionalidades de cadastro e edição dos clientes, garantindo que não haja duplicados (RN6).
Descontos para aluguéis de pessoa física e jurídica (RN7): Implementar a lógica de cálculo de desconto para devoluções de clientes. ***


### 4 Aluguel e Devolução de Veículos - Pietra
Alugar e devolver veículos: Criar o processo de aluguel e devolução para pessoa física e jurídica, registrando local, data e horário (RN3).
Controle de disponibilidade dos veículos (RN4): Garantir que veículos alugados não possam ser alugados novamente enquanto não forem devolvidos.


### 5 Relatórios - Gustavo Habib
Gerar comprovantes: Implementar a geração dos comprovantes de aluguel e devolução, incluindo todas as informações decididas pelo grupo.
Banco de Dados

## Divisão de Funções Implementadas entregues ao final do MÓDULO IV:

### 1. Relatório de Aluguéis Mensais - Pietra
- **Função**: Gera um arquivo CSV com as colunas: ID do Aluguel, Cliente, Veículo, Data Início, Data Fim.

### 2. Comprovante de Devolução de Veículo - Mayara
- **Função**: Gera um arquivo CSV com as colunas: ID do Aluguel, Cliente, Veículo, Local de Retirada, Local de Devolução, Data Início, Data Fim, Valor Total.

### 3. Relatório de Agências - Rosângela
- **Função**: Lê, importa e exporta um arquivo CSV com as colunas: Nome, Endereço,



## Integrantes:

Gustavo de Queiroz (módulo III)


Gustavo Habib (módulo III)


Mayara Santos (Módulos I, III e IV)


Pietra Almeida (Módulos I, III e IV)


Rosângela Batista (Módulos I, III e IV)
