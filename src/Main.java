import db.AgenciaDb;
import db.AluguelDb;
import db.ClienteDb;
import db.VeiculoDb;
import dominio.Veiculo;
import dominio.Cliente;
import gerenciamento.GerenciadorV;

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

        GerenciadorV gerenciadorV = new GerenciadorV();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("****** Locadora de Veículos ******");
            System.out.println();
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Gerenciar Agências");
            System.out.println("2 - Gerenciar Aluguéis");
            System.out.println("3 - Gerenciar Clientes");
            System.out.println("4 - Gerenciar Veículos");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            System.out.println();

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gerenciadorV.gerenciarAgencias(agenciaDb, scanner);
                    break;

                case 2:
                    gerenciadorV.gerenciarAlugueis(aluguelDb, clienteDb, veiculoDb, scanner);
                    break;

                case 3:
                    gerenciadorV.gerenciarClientes(clienteDb, scanner);
                    break;

                case 4:
                    gerenciadorV.gerenciarVeiculos(veiculoDb, scanner);
                    break;

                case 0:
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

}
