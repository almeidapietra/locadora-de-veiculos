package db;

import dominio.Cliente;
import dominio.ClientePessoaFisica;
import dominio.ClientePessoaJuridica;
import interfaces.IBancoDeDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteDb implements IBancoDeDados<Cliente> {

    private List<Cliente> listaDeClientes = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean cadastrar(Cliente cliente) {
        System.out.println("Escolha o tipo de cliente:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Digite a opção (1 ou 2): ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o logradouro: ");
            String logradouro = scanner.nextLine();

            System.out.print("Digite o CPF: ");
            String cpf = scanner.nextLine();

            cliente = new ClientePessoaFisica(nome, idade, logradouro, cpf);
            boolean sucesso = listaDeClientes.add(cliente);
            System.out.println(sucesso ? "Cliente Pessoa Física cadastrado com sucesso!" : "Erro ao cadastrar cliente.");
            return sucesso;

        } else if (opcao == 2) {
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a idade da empresa: ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o logradouro: ");
            String logradouro = scanner.nextLine();

            System.out.print("Digite o CNPJ: ");
            String cnpj = scanner.nextLine();

            cliente = new ClientePessoaJuridica(nome, idade, logradouro, cnpj);
            boolean sucesso = listaDeClientes.add(cliente);
            System.out.println(sucesso ? "Cliente Pessoa Jurídica cadastrado com sucesso!" : "Erro ao cadastrar cliente.");
            return sucesso;

        } else {
            System.out.println("Opção inválida.");
            return false;
        }
    }

    @Override
    public boolean alterar(Cliente cliente) {
        System.out.println("Deseja buscar o cliente por:");
        System.out.println("1. CPF");
        System.out.println("2. CNPJ");
        System.out.print("Digite a opção (1 ou 2): ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        Cliente clienteEncontrado = null;

        if (opcao == 1) {
            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();
            clienteEncontrado = buscarPorCpf(cpf);
        } else if (opcao == 2) {
            System.out.print("Digite o CNPJ do cliente: ");
            String cnpj = scanner.nextLine();
            clienteEncontrado = buscarPorCnpj(cnpj);
        } else {
            System.out.println("Opção inválida.");
            return false;
        }

        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: " + clienteEncontrado);
            System.out.println("Escolha o que deseja alterar:");
            System.out.println("1. Nome");
            System.out.println("2. Idade");
            System.out.println("3. Logradouro");
            System.out.println("4. CPF/CNPJ");
            System.out.print("Digite a opção (1-4): ");
            int opcaoAlteracao = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoAlteracao) {
                case 1:
                    System.out.print("Digite o novo nome: ");
                    String novoNome = scanner.nextLine();
                    clienteEncontrado.setNome(novoNome);
                    break;

                case 2:
                    System.out.print("Digite a nova idade: ");
                    int novaIdade = scanner.nextInt();
                    scanner.nextLine();
                    clienteEncontrado.setIdade(novaIdade);
                    break;

                case 3:
                    System.out.print("Digite o novo logradouro: ");
                    String novoLogradouro = scanner.nextLine();
                    clienteEncontrado.setLogradouro(novoLogradouro);
                    break;

                case 4:
                    if (clienteEncontrado instanceof ClientePessoaFisica) {
                        System.out.print("Digite o novo CPF: ");
                        String novoCpf = scanner.nextLine();
                        ((ClientePessoaFisica) clienteEncontrado).setCpf(novoCpf);
                    } else if (clienteEncontrado instanceof ClientePessoaJuridica) {
                        System.out.print("Digite o novo CNPJ: ");
                        String novoCnpj = scanner.nextLine();
                        ((ClientePessoaJuridica) clienteEncontrado).setCnpj(novoCnpj);
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
                    return false;
            }

            System.out.println("Cliente atualizado com sucesso: " + clienteEncontrado);
            return true;
        } else {
            System.out.println("Cliente não encontrado.");
            return false;
        }
    }

    @Override
    public Cliente buscarPorId(String id){
        return null;
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cliente : listaDeClientes) {
            if (cliente instanceof ClientePessoaFisica) {
                if (((ClientePessoaFisica) cliente).getCpf().equals(cpf)) {
                    return cliente;
                }
            }
        }
        System.out.println("Cliente com CPF " + cpf + " não encontrado.");
        return null;
    }


    @Override
    public Cliente buscarPorCnpj(String cnpj) {
        for (Cliente cliente : listaDeClientes) {
            if (cliente instanceof ClientePessoaJuridica) {
                if (((ClientePessoaJuridica) cliente).getCnpj().equals(cnpj)) {
                    return cliente;
                }
            }
        }
        System.out.println("Cliente com CNPJ " + cnpj + " não encontrado.");
        return null;
    }

    @Override
    public boolean deletar(String identificador) {
        System.out.println("Deseja deletar o cliente por:");
        System.out.println("1. CPF");
        System.out.println("2. CNPJ");
        System.out.print("Digite a opção (1 ou 2): ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        Cliente clienteEncontrado = null;

        if (opcao == 1) {
            clienteEncontrado = buscarPorCpf(identificador);
        } else if (opcao == 2) {
            clienteEncontrado = buscarPorCnpj(identificador);
        } else {
            System.out.println("Opção inválida.");
            return false;
        }

        if (clienteEncontrado != null) {
            listaDeClientes.remove(clienteEncontrado);
            System.out.println("Cliente removido com sucesso: " + clienteEncontrado);
            return true;
        } else {
            System.out.println("Cliente não encontrado.");
            return false;
        }
    }


}
