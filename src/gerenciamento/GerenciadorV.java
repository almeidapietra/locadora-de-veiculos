package gerenciamento;

import db.AgenciaDb;
import db.AluguelDb;
import db.ClienteDb;
import db.VeiculoDb;
import dominio.Agencia;
import dominio.Veiculo;
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

    public void gerenciarAlugueis(AluguelDb aluguelDb, Scanner scanner, List<Veiculo> veiculos) {
        // Falta Implementar
    }

    public void gerenciarClientes(ClienteDb clienteDb, Scanner scanner) {
        // Falta Implementar
    }

    public void gerenciarVeiculos(VeiculoDb veiculoDb, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("[Gerenciamento de Veículos]");
            System.out.println();
            System.out.println("1 - Cadastrar Veiculo");
            System.out.println("2 - Alterar Veiculo");
            System.out.println("3 - Buscar Veiculo");
            System.out.println("4 - Deletar Veiculo");
            System.out.println("5 - Listar Veiculo");
            System.out.println("0 - Voltar ao menu principal");
            System.out.println("Opção: ");

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
                    buscarVeiculoPorPlaca(veiculoDb, scanner);
                    break;
                case 4:
                    deletarVeiculo(veiculoDb, scanner);
                    break;
                case 5:
                    listarVeiculos(veiculoDb);
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

    private void buscarVeiculoPorPlaca(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = veiculoDb.buscar(placa);
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
}
