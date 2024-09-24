import db.AgenciaDb;
import db.AluguelDb;
import db.ClienteDb;
import db.VeiculoDb;
import dominio.Agencia;
import dominio.Aluguel;
import dominio.Cliente;
import dominio.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AgenciaDb agenciaDb = new AgenciaDb();
        List<Veiculo> veiculos = new ArrayList<>();
        AluguelDb aluguelDb = new AluguelDb(veiculos);
        ClienteDb clienteDb = new ClienteDb();
        VeiculoDb veiculoDb = new VeiculoDb();

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Sistema de Locadora de Veículos");

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Gerenciar Agências");
            System.out.println("2 - Gerenciar Aluguéis");
            System.out.println("3 - Gerenciar Clientes");
            System.out.println("4 - Gerenciar Veículos");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            System.out.println();

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gerenciarAgencias(agenciaDb, scanner);
                    break;

                case 2:
                    gerenciarAlugueis(aluguelDb, scanner, veiculos);
                    System.out.println("Falta implementar");
                    break;

                case 3:
                    gerenciarClientes(clienteDb, scanner);
                    System.out.println("Falta implementar");
                    break;

                case 4:
                    gerenciarVeiculos(veiculoDb, scanner);
                    System.out.println("Falta implementar");
                    break;

                case 5:
                    continuar = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void gerenciarAgencias(AgenciaDb agenciaDb, Scanner scanner) {
        System.out.println("Gerenciamento de Agências");
        System.out.println();
        System.out.println("1 - Cadastrar Agência");
        System.out.println("2 - Buscar Agência por Nome");
        System.out.println("3 - Deletar Agência");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Digite o ID da agência: ");
                String idAgencia = scanner.nextLine();
                System.out.print("Digite o nome da agência: ");
                String nomeAgencia = scanner.nextLine();
                System.out.print("Digite o endereço da agência: ");
                String enderecoAgencia = scanner.nextLine();

                Agencia agencia = new Agencia(idAgencia, nomeAgencia, enderecoAgencia);
                boolean cadastrouAgencia = agenciaDb.cadastrar(agencia);
                if (cadastrouAgencia) {
                    System.out.println("Agência cadastrada com sucesso!");
                } else {
                    System.out.println("Erro: Agência já existe.");
                }
                break;

            case 2:
                System.out.print("Digite o nome da agência: ");
                String nomeBusca = scanner.nextLine();
                Agencia agenciaEncontrada = agenciaDb.buscarPorNome(nomeBusca);
                if (agenciaEncontrada != null) {
                    System.out.println("Agência encontrada: " + agenciaEncontrada.getNome());
                } else {
                    System.out.println("Agência não encontrada.");
                }
                break;

            case 3:
                System.out.print("Digite o ID da agência para deletar: ");
                String idDeletar = scanner.nextLine();
                boolean deletouAgencia = agenciaDb.deletar(idDeletar);
                if (deletouAgencia) {
                    System.out.println("Agência excluída com sucesso.");
                } else {
                    System.out.println("Erro: Agência não encontrada.");
                }
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void gerenciarAlugueis(AluguelDb aluguelDb, Scanner scanner, List<Veiculo> veiculos) {

    }

    private static void gerenciarClientes(ClienteDb clienteDb, Scanner scanner) {

    }

    private static void gerenciarVeiculos(VeiculoDb veiculoDb, Scanner scanner) {
    }

}

