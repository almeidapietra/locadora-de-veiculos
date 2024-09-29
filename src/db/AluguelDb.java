package db;

import dominio.Agencia;
import dominio.Aluguel;
import dominio.Cliente;
import dominio.Veiculo;
import interfaces.AluguelVeiculo;
import interfaces.IBancoDeDados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AluguelDb implements IBancoDeDados<Aluguel>, AluguelVeiculo<Cliente, Veiculo> {
    private static final long serialVersionUID = 1L;

    private List<Aluguel> alugueis = new ArrayList<>();
    private List<Veiculo> veiculos;
    File file = new File("alugueis.ser");

    public AluguelDb(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
        carregarDados();
    }

    public void carregarDados() {
        if (file.exists()) {
            try (ObjectInputStream arquivo = new ObjectInputStream(new FileInputStream(file))) {
                alugueis = (List<Aluguel>) arquivo.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Aluguel> listar() {
        return new ArrayList<>(alugueis); // Retorna uma cópia da lista de aluguéis
    }


    public void salvarDados() {
        try (ObjectOutputStream arquivo = new ObjectOutputStream(new FileOutputStream(file))) {
            arquivo.writeObject(alugueis);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean cadastrar(Aluguel aluguel) {
        for (Aluguel a : alugueis) {
            // Se já houver um aluguel com o mesmo ID, não cadastra
            if (a.getId().equals(aluguel.getId())) {
                return false;
            }
        }
        alugueis.add(aluguel);
        salvarDados();
        return true;
    }

    @Override
    public boolean alterar(Aluguel aluguel) {
        for (int i = 0; i < alugueis.size(); i++) {
            if (alugueis.get(i).getId().equals(aluguel.getId())) {
                alugueis.set(i, aluguel);
                salvarDados();
                return true;
            }
        }
        // Se não encontrar o aluguel para alterar, retorna false
        return false;
    }

    @Override
    public Aluguel buscar(String valor) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getId().equals(valor)) {
                return aluguel;
            }
        }
        // Se não encontrar, retorna null
        return null;
    }

    @Override
    public boolean deletar(String id) {
        for (int i = 0; i < alugueis.size(); i++) {
            if (alugueis.get(i).getId().equals(id)) {
                alugueis.remove(i);
                salvarDados();
                return true;
            }
        }
        // Se não encontrar o aluguel para deletar, retorna false
        return false;
    }

    @Override
    public void alugarVeiculo(Cliente cliente, Veiculo veiculo, String localRetirada, long dataInicio) {
        // Verificar se o veículo está disponível
        if (!isVeiculoDisponivel(veiculo)) {
            System.out.println("Veículo não disponível para aluguel.");
            return; // Se não estiver disponível, sai do método
        }

        // Gerar um ID simples baseado no tamanho da lista
        String id = String.valueOf(alugueis.size() + 1);

        // Criar um novo aluguel
        Aluguel novoAluguel = new Aluguel(id, veiculo, cliente, localRetirada, dataInicio);

        // Adicionar o aluguel à lista e salvar os dados
        alugueis.add(novoAluguel);
        salvarDados();
        System.out.println("Veículo alugado com sucesso: " + novoAluguel.getId());
    }




    @Override
    public void devolverVeiculo(Cliente cliente, Veiculo veiculo, String localDevolucao, long dataFim) {
        for (Aluguel aluguel : alugueis) {
            // Encontrar o aluguel ativo que corresponde ao cliente e veículo
            if (aluguel.getCliente().equals(cliente) && aluguel.getVeiculo().equals(veiculo) && aluguel.isAtivo()) {
                // Configurar os dados de devolução
                aluguel.setLocalDevolucao(localDevolucao);
                aluguel.setDataFim(dataFim);

                // Cálculo da duração do aluguel em dias
                long duracao = (dataFim - aluguel.getDataInicio()) / (1000 * 60 * 60 * 24);
                double desconto = 0;

                // Verificar se é cliente pessoa física
                if (cliente instanceof Cliente.ClientePessoaFisica && duracao > 5) {
                    desconto = 0.05; // 5% de desconto
                    System.out.println("Desconto de 5% aplicado para pessoa física.");
                }

                // Verificar se é cliente pessoa jurídica
                if (cliente instanceof Cliente.ClientePessoaJuridica && duracao > 3) {
                    desconto = 0.10; // 10% de desconto
                    System.out.println("Desconto de 10% aplicado para pessoa jurídica.");
                }

                // Exibir informações sobre o desconto
                if (desconto > 0) {
                    System.out.printf("Desconto: %.2f%%\n", desconto * 100);
                } else {
                    System.out.println("Nenhum desconto aplicável.");
                }

                // Salvar os dados após a devolução
                salvarDados();
                System.out.println("Veículo devolvido com sucesso: " + aluguel.getId());
                return; // Sai do método após a devolução
            }
        }
        // Se não encontrar o aluguel correspondente
        System.out.println("Não foi possível encontrar o aluguel ativo para devolução.");
    }



    public boolean isVeiculoDisponivel(Veiculo veiculo) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getVeiculo().equals(veiculo) && !aluguel.isDevolvido()) {
                return false; // Veículo está alugado, então não está disponível
            }
        }
        return true; // Veículo está disponível
    }
}
