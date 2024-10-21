package gerenciamento;

import db.AgenciaDb;
import db.AluguelDb;
import db.ClienteDb;
import db.VeiculoDb;
import dominio.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorV {

    public void gerenciarAgencias(AgenciaDb agenciaDb, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("[Gerenciamento de Agências]");
            System.out.println();
            System.out.println("1 - Cadastrar Agência");
            System.out.println("2 - Alterar Agência");
            System.out.println("3 - Consultar Agência");
            System.out.println("4 - Deletar Agência");
            System.out.println("5 - Listar Agências");
            System.out.println("6 - Exportar Relatório CSV");
            System.out.println("7 - Importação de Agências em Massa CSV");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAgencia(agenciaDb, scanner);
                    break;
                case 2:
                    alterarAgencia(agenciaDb, scanner);
                    break;
                case 3:
                    buscarAgenciaPorNomeOuendereco(agenciaDb, scanner);
                    break;
                case 4:
                    deletarAgencia(agenciaDb, scanner);
                    break;
                case 5:
                    listarAgencias(agenciaDb);
                    break;
                case 6:
                    gerarRelatorioAgenciaCSV(agenciaDb);
                    break;
                case 7:
                    lerAgenciasDoCSV(agenciaDb, scanner);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
    }

    private void cadastrarAgencia(AgenciaDb agenciaDb, Scanner scanner) {
        System.out.print("Digite o nome da agência: ");
        String nomeAgencia = scanner.nextLine();
        System.out.print("Digite o endereço da agência: ");
        String enderecoAgencia = scanner.nextLine();

        Agencia agencia = new Agencia(nomeAgencia, enderecoAgencia);
        boolean sucesso = agenciaDb.cadastrar(agencia);
        System.out.println(sucesso ? "Agência cadastrada com sucesso!" : "Erro: Agência já existe.");
    }

    private void alterarAgencia(AgenciaDb agenciaDb, Scanner scanner) {
        System.out.print("Digite o nome da agência: ");
        String nome = scanner.nextLine();

        Agencia agencia = agenciaDb.buscar(nome);
        if (agencia == null) {
            System.out.println("Agência não encontrada.");
            return;
        }

        System.out.print("Digite o novo nome da agência: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo endereço da agência: ");
        String novoEndereco = scanner.nextLine();

        agencia.setNome(novoNome);
        agencia.setEndereco(novoEndereco);

        boolean sucesso = agenciaDb.alterar(agencia);
        System.out.println(sucesso ? "Agência alterada com sucesso!" : "Erro ao alterar a agência.");
    }

    private void buscarAgenciaPorNomeOuendereco(AgenciaDb agenciaDb, Scanner scanner) {
        System.out.print("Digite o nome ou endereço da agência: ");
        String valor = scanner.nextLine();

        Agencia agencia = agenciaDb.buscar(valor);
        if (agencia != null) {
            System.out.println("Agência encontrada: ");
            System.out.println("Nome: " + agencia.getNome());
            System.out.println("Endereço: " + agencia.getEndereco());
        } else {
            System.out.println("Agência não encontrada.");
        }
    }

    private void deletarAgencia(AgenciaDb agenciaDb, Scanner scanner) {
        System.out.print("Digite o nome da agência a ser deletada: ");
        String nome = scanner.nextLine();

        try {
            boolean sucesso = agenciaDb.deletar(nome);
            System.out.println(sucesso ? "Agência deletada com sucesso." : "Erro: Agência não encontrada.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());  // Exibir a mensagem da exceção no console
        }
    }

    private void listarAgencias(AgenciaDb agenciaDb) {
        List<Agencia> agencias = agenciaDb.listar();
        if (agencias.isEmpty()) {
            System.out.println("Nenhuma agência cadastrada.");
        } else {
            System.out.println("Agências cadastradas:");
            for (Agencia agencia : agencias) {
                System.out.println("ID: " + agencia.getId() + ", Nome: " + agencia.getNome() + ", Endereço: " + agencia.getEndereco());
            }
        }
    }

    private void gerarRelatorioAgenciaCSV(AgenciaDb agenciaDb) {
        agenciaDb.gerarRelatorioAgenciasCSV();
    }

    private void lerAgenciasDoCSV(AgenciaDb agenciaDb, Scanner scanner) {
        System.out.print("Digite o caminho do arquivo CSV: ");
        String caminhoArquivo = scanner.nextLine();
        agenciaDb.lerAgenciasDoCSV(caminhoArquivo);
    }

    public void gerenciarAlugueis(AluguelDb aluguelDb, ClienteDb clienteDb, VeiculoDb veiculoDb, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("[Gerenciamento de Aluguéis]");
            System.out.println("1 - Cadastrar Aluguel");
            System.out.println("2 - Listar Aluguéis");
            System.out.println("3 - Devolver Aluguel");
            System.out.println("4 - Gerar Relatório CSV");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAluguel(aluguelDb, clienteDb, veiculoDb, scanner);
                    break;
                case 2:
                    listarAlugueis(aluguelDb);
                    break;
                case 3:
                    devolverAluguel(aluguelDb, scanner);
                    break;
                case 4:
                    gerarRelatorioCSV(aluguelDb, scanner);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
    }

    private void gerarRelatorioCSV(AluguelDb aluguelDb, Scanner scanner) {
        System.out.print("Digite o período para o relatório (ex: 2024-10): ");
        String periodo = scanner.nextLine();
        aluguelDb.gerarRelatorioAlugueisCSV(periodo);
    }
    private void cadastrarAluguel(AluguelDb aluguelDb, ClienteDb clienteDb, VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite o Nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = clienteDb.buscar(nome);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite o nome do veículo: ");
        String modelo = scanner.nextLine();
        Veiculo veiculo = veiculoDb.buscar(modelo);

        if (veiculo == null || !veiculo.isDisponivel()) {
            System.out.println("Veículo não disponível.");
            return;
        }

        System.out.print("Digite o local de retirada: ");
        String localRetirada = scanner.nextLine();
        long dataInicio = System.currentTimeMillis();

        Aluguel aluguel = new Aluguel(String.valueOf(aluguelDb.listar().size() + 1), veiculo, cliente, localRetirada, dataInicio);
        if (aluguelDb.cadastrar(aluguel)) {
            System.out.println("Aluguel cadastrado com sucesso!");
            comprovanteAluguel(aluguel);
        } else {
            System.out.println("Erro ao cadastrar aluguel.");
        }
    }

    private void listarAlugueis(AluguelDb aluguelDb) {
        List<Aluguel> alugueis = aluguelDb.listar();
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
        } else {
            System.out.println("Aluguéis cadastrados:");
            for (Aluguel aluguel : alugueis) {
                System.out.println("ID: " + aluguel.getId() + ", Cliente: " + aluguel.getCliente().getNome() + ", Veículo: " + aluguel.getVeiculo().getModelo() + ", Devolvido: " + aluguel.isDevolvido());
            }
        }
    }

    public static void comprovanteAluguel(Aluguel aluguel) {
        System.out.println("====================================");
        System.out.println("          COMPROVANTE DE ALUGUEL");
        System.out.println("====================================");
        System.out.println("ID do Aluguel: " + aluguel.getId());
        System.out.println("Cliente: " + aluguel.getCliente().getNome());
        System.out.println("Veículo: " + aluguel.getVeiculo().getModelo());
        System.out.println("Local de Retirada: " + aluguel.getLocalRetirada());
        System.out.println("Data de Início do Aluguel: " + aluguel.DataInicioFormatado());
        System.out.println("====================================");
    }

    private void devolverAluguel(AluguelDb aluguelDb, Scanner scanner) {
        System.out.print("Digite o ID do aluguel a ser devolvido: ");
        String id = scanner.nextLine();
        Aluguel aluguel = aluguelDb.buscar(id);

        if (aluguel == null || !aluguel.isAtivo()) {
            System.out.println("Aluguel não encontrado ou já devolvido.");
            return;
        }

        System.out.print("Digite o local de devolução: ");
        String localDevolucao = scanner.nextLine();
        long dataFim = System.currentTimeMillis();

        aluguelDb.devolverVeiculo(aluguel.getCliente(), aluguel.getVeiculo(), localDevolucao, dataFim);
        comprovanteDevolucao(aluguel);
    }

    public static void comprovanteDevolucao(Aluguel aluguel) {
        System.out.println("====================================");
        System.out.println("       COMPROVANTE DE DEVOLUÇÃO");
        System.out.println("====================================");
        System.out.println("ID do Aluguel: " + aluguel.getId());
        System.out.println("Cliente: " + aluguel.getCliente().getNome());
        System.out.println("Veículo: " + aluguel.getVeiculo().getModelo());
        System.out.println("Local de Retirada: " + aluguel.getLocalRetirada());
        System.out.println("Local de Devolução: " + aluguel.getLocalDevolucao());
        System.out.println("Data de Início do Aluguel: " + aluguel.DataInicioFormatado());
        System.out.println("Data de Fim do Aluguel: " + aluguel.DataFimFormatado());
        System.out.println("Devolvido: " + (aluguel.isDevolvido() ? "Sim" : "Não"));
        System.out.println("====================================");
    }


    public void gerenciarClientes(ClienteDb clienteDb, Scanner scanner) {
        for (boolean continuar = true; continuar; System.out.println()) {
            System.out.println("[Gerenciamento de Clientes]");
            System.out.println();
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Alterar Cliente");
            System.out.println("3 - Buscar Cliente");
            System.out.println("4 - Deletar Cliente");
            System.out.println("5 - Listar Cliente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.println("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    this.cadastrarCliente(clienteDb, scanner);
                    break;
                case 2:
                    this.alterarCliente(clienteDb, scanner);
                    break;
                case 3:
                    this.buscarCliente(clienteDb, scanner);
                    break;
                case 4:
                    this.deletarCliente(clienteDb, scanner);
                    break;
                case 5:
                    this.listarClientes(clienteDb);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listarClientes(ClienteDb clienteDb) {
        List<Cliente> clientes = clienteDb.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("Veículos cadastrados:");
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.getNome() + ", Endereço: " + cliente.getLogradouro());
            }
        }
    }

    public void cadastrarCliente(ClienteDb clienteDb, Scanner scanner) {
        System.out.println("Digite seu nome completo ou razão social: ");
        String nome = scanner.nextLine();
        System.out.println("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.println("O cliente é uma pessoa física ou jurídica? (F/J): ");
        String tipo = scanner.nextLine();
        Cliente cliente;
        String cnpj;
        if (tipo.equalsIgnoreCase("F")) {
            System.out.println("Digite o CPF (somente números): ");
            cnpj = scanner.nextLine();
            cliente = new ClientePessoaFisica(cnpj);
        } else {
            if (!tipo.equalsIgnoreCase("J")) {
                System.out.println("Tipo inválido.");
                return;
            }

            System.out.println("Digite o CNPJ (somente números): ");
            cnpj = scanner.nextLine();
            cliente = new ClientePessoaJuridica(cnpj);
        }

        cliente.setNome(nome);
        cliente.setLogradouro(logradouro);
        boolean sucesso = clienteDb.cadastrar(cliente);
        System.out.println(sucesso ? "Cliente cadastrado com sucesso! " : "Erro: Cliente já cadastrado");
    }

    public void alterarCliente(ClienteDb clienteDb, Scanner scanner) {
        System.out.println("Informe o nome ou razão social completo do cliente a ser alterado: ");
        String nome = scanner.nextLine();
        Cliente cliente = clienteDb.buscar(nome);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println("Informe o novo nome: ");
            String novoNome = scanner.nextLine();
            cliente.setNome(novoNome);
            String novoCnpj;
            if (cliente instanceof ClientePessoaFisica) {
                System.out.println("Informe o novo CPF: ");
                novoCnpj = scanner.nextLine();
                ((ClientePessoaFisica) cliente).setCpf(novoCnpj);
            } else if (cliente instanceof ClientePessoaJuridica) {
                System.out.println("Informe o novo CNPJ: ");
                novoCnpj = scanner.nextLine();
                ((ClientePessoaJuridica) cliente).setCnpj(novoCnpj);
            }

            boolean sucesso = clienteDb.alterar(cliente);
            System.out.println(sucesso ? "Cliente alterado com sucesso!" : "Erro ao alterar cliente");
        }
    }

    public void buscarCliente(ClienteDb clienteDb, Scanner scanner) {
        System.out.println("Informe o nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = clienteDb.buscar(nome);
        if (cliente != null) {
            System.out.println("Cliente encontrado: ");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Logradouro: " + cliente.getLogradouro());
            if (cliente instanceof ClientePessoaFisica) {
                System.out.println("CPF: " + ((ClientePessoaFisica) cliente).getCpf());
            } else if (cliente instanceof ClientePessoaJuridica) {
                System.out.println("CNPJ: " + ((ClientePessoaJuridica) cliente).getCnpj());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }

    }

    public void deletarCliente(ClienteDb clienteDb, Scanner scanner) {
        System.out.println("Digite o nome do cliente a ser deletado: ");
        String nome = scanner.nextLine();
        boolean sucesso = clienteDb.deletar(nome);
        System.out.println(sucesso ? "Cliente deletado com sucesso. " : "Cliente não encontrado.");
    }

    public void gerenciarVeiculos(VeiculoDb veiculoDb, Scanner scanner) {
        boolean continuar = true;
        List<Veiculo> veiculos = new ArrayList<>();
        List<Aluguel> alugueis = new ArrayList<>();
        while (continuar) {
            System.out.println("[Gerenciamento de Veículos]");
            System.out.println("1 - Cadastrar Veículo");
            System.out.println("2 - Alterar Veículo");
            System.out.println("3 - Buscar Veículo");
            System.out.println("4 - Deletar Veículo");
            System.out.println("5 - Listar Veículos");
            System.out.println("6 - Gerar Relatório Veículo CSV");
            System.out.println("7 - Importar Veículos do CSV");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarVeiculo(veiculoDb, scanner);
                    break;
                case 2:
                    alterarVeiculo(veiculoDb, scanner);
                    break;
                case 3:
                    buscarVeiculo(veiculoDb, scanner);
                    break;
                case 4:
                    deletarVeiculo(veiculoDb, scanner);
                    break;
                case 5:
                    listarVeiculos(veiculoDb);
                    break;
                case 6:
                    gerarRelatorioVeiculoCSV(veiculoDb,alugueis);
                    break;
                case 7:
                    System.out.print("Digite o caminho do arquivo CSV: ");
                    String caminhoArquivo = scanner.nextLine();
                    leituraDeVeiculos(veiculoDb, caminhoArquivo);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
    }

    private void cadastrarVeiculo(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();

        Veiculo veiculo = new Veiculo(placa, modelo);

        boolean sucesso = veiculoDb.cadastrar(veiculo);
        System.out.println(sucesso ? "Veículo cadastrado com sucesso!" : "Erro: Veículo já cadastrado.");
    }

    private void alterarVeiculo(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo a ser alterado: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = veiculoDb.buscar(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Digite o novo modelo do veículo: ");
        String novoModelo = scanner.nextLine();

        veiculo.setModelo(novoModelo);

        boolean sucesso = veiculoDb.alterar(veiculo);
        System.out.println(sucesso ? "Veículo alterado com sucesso!" : "Erro ao alterar o veículo.");
    }

    private void buscarVeiculo(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();

        Veiculo veiculo = veiculoDb.buscar(modelo);
        if (veiculo != null) {
            System.out.println("Veículo encontrado: ");
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Modelo: " + veiculo.getModelo());
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private void deletarVeiculo(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo a ser deletado: ");
        String placa = scanner.nextLine();

        boolean sucesso = veiculoDb.deletar(placa);
        System.out.println(sucesso ? "Veículo deletado com sucesso." : "Erro: Veículo não encontrado.");
    }

    private void listarVeiculos(VeiculoDb veiculoDb) {
        List<Veiculo> veiculos = veiculoDb.listar();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("Veículos cadastrados:");
            for (Veiculo veiculo : veiculos) {
                System.out.println("Placa: " + veiculo.getPlaca() + ", Modelo: " + veiculo.getModelo());
            }
        }

    }


    static void gerarRelatorioVeiculoCSV(VeiculoDb veiculoDb, List<Aluguel> alugueis) {
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel encontrado para gerar o relatório.");
            return;
        }

        veiculoDb.gerarComprovanteDevolucaoCSV(alugueis);

        System.out.println("NOVO Comprovante de devolução gerado com sucesso!");
    }

    private void leituraDeVeiculos(VeiculoDb veiculoDb, String caminhoArquivo) {
        try {
            veiculoDb.lerComprovantesDoCSV(caminhoArquivo);
            System.out.println("Veículos importados com sucesso do arquivo: " + caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao importar veículos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}