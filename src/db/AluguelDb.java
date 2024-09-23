package db;

import dominio.Aluguel;
import dominio.Cliente;
import dominio.Veiculo;
import interfaces.AluguelVeiculo;
import interfaces.IBancoDeDados;

import java.util.ArrayList;
import java.util.List;

public class AluguelDb implements IBancoDeDados<Aluguel>, AluguelVeiculo<Cliente, Veiculo> {

    private List<Aluguel> alugueis = new ArrayList<>();
    private List<Veiculo> veiculos;

    public AluguelDb(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
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
        return true;
    }

    @Override
    public boolean alterar(Aluguel aluguel) {
        for (int i = 0; i < alugueis.size(); i++) {
            if (alugueis.get(i).getId().equals(aluguel.getId())) {
                alugueis.set(i, aluguel);
                return true;
            }
        }
        // Se não encontrar o aluguel para alterar, retorna false
        return false;
    }

    @Override
    public Aluguel buscarPorId(String id) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getId().equals(id)) {
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
                return true;
            }
        }
        // Se não encontrar o aluguel para deletar, retorna false
        return false;
    }

    @Override
    public void alugarVeiculo(Cliente cliente, Veiculo veiculo, String localRetirada, long dataInicio) {
        // Lógica para alugar o veículo (implementação a ser feita)
    }

    @Override
    public void devolverVeiculo(Cliente cliente, Veiculo veiculo, String localDevolucao, long dataFim) {
        // Lógica para devolver o veículo (implementação a ser feita)
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
