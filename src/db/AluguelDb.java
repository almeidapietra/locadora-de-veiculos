package db;

import dominio.Aluguel;
import dominio.Cliente;
import dominio.Veiculo;
import interfaces.AluguelVeiculo;
import interfaces.IBancoDeDados;

public class AluguelDb implements IBancoDeDados<Aluguel>, AluguelVeiculo {
    @Override
    public boolean cadastrar(Aluguel entidade) {
        return false;
    }

    @Override
    public boolean alterar(Aluguel entidade) {
        return false;
    }

    @Override
    public Aluguel buscarPorId(String id) {
        return null;
    }

    @Override
    public boolean deletar(String id) {
        return false;
    }

    @Override
    public void alugarVeiculo(Cliente cliente, Veiculo veiculo, String localRetirada, long dataInicio) {

    }

    @Override
    public void devolverVeiculo(Cliente cliente, Veiculo veiculo, String localDevolucao, long dataFim) {

    }

}