package db;

import dominio.*;
import interfaces.AluguelVeiculo;
import interfaces.IBancoDeDados;
import java.util.Iterator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AluguelDb implements IBancoDeDados<Aluguel>, AluguelVeiculo<Cliente, Veiculo> {
    private static final long serialVersionUID = 1L;

    private List<Aluguel> alugueis;
    private List<Veiculo> veiculos;
    File file;

    public AluguelDb(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
        alugueis = new ArrayList<>();
        file = new File("alugueis.ser");
        DadosWR<Aluguel> DadosWR = new DadosWR<Aluguel>();
        DadosWR.carregarDados(file, alugueis);
    }

    public void salvarDados() {
        DadosWR.salvarDados(file, alugueis);
    }

    public List<Aluguel> listar() {
        return new ArrayList<>(alugueis); // Retorna uma cópia da lista de aluguéis
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
    Iterator<Aluguel> var6 = this.alugueis.iterator();
    Aluguel aluguel;

    do {
        if (!var6.hasNext()) {
            System.out.println("Não foi possível encontrar o aluguel ativo para devolução.");
            return;
        }
        aluguel = var6.next();
    } while (!aluguel.getCliente().equals(cliente) || !aluguel.getVeiculo().equals(veiculo) || !aluguel.isAtivo());

    aluguel.setLocalDevolucao(localDevolucao);
    aluguel.setDataFim(dataFim);
    long duracao = (dataFim - aluguel.getDataInicio()) / 86400000L; // Duração em dias
    double desconto = 0.0;

    // Cálculo do valor da diária
    double valorDiaria = 0.0;
    if (veiculo instanceof Moto) {
        valorDiaria = 100.0;
    } else if (veiculo instanceof Carro) {
        valorDiaria = 150.0;
    } else if (veiculo instanceof Caminhao) {
        valorDiaria = 200.0;
    }

    // Cálculo do valor total
    double valorTotal = valorDiaria * duracao;
    System.out.printf("Valor total a pagar: R$ %.2f\n", valorTotal);

    // Aplicação de descontos
    if (cliente instanceof Cliente.ClientePessoaFisica && duracao > 5L) {
        desconto = 0.05;
        System.out.println("Desconto de 5% aplicado para pessoa física.");
    }

    if (cliente instanceof Cliente.ClientePessoaJuridica && duracao > 3L) {
        desconto = 0.1;
        System.out.println("Desconto de 10% aplicado para pessoa jurídica.");
    }

    if (desconto > 0.0) {
        double valorDesconto = valorTotal * desconto;
        valorTotal -= valorDesconto;
        System.out.printf("Desconto: %.2f%%\n", desconto * 100.0);
        System.out.printf("Valor total com desconto: R$ %.2f\n", valorTotal);
    } else {
        System.out.println("Nenhum desconto aplicável.");
    }

    this.salvarDados();
    System.out.println("Veículo devolvido com sucesso: " + aluguel.getId());
    }

    public boolean isVeiculoDisponivel(Veiculo veiculo) {
        Iterator<Aluguel> var2 = this.alugueis.iterator();
        Aluguel aluguel;

        do {
            if (!var2.hasNext()) {
                return true;
            }
            aluguel = var2.next();
        } while (!aluguel.getVeiculo().equals(veiculo) || aluguel.isDevolvido());

        return false;
    }




}
