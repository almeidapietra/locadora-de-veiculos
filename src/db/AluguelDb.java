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
        public boolean cadastrar (Aluguel aluguel){
            return false;
        }

        @Override
        public boolean alterar (Aluguel entidade){
            return false;
        }

        @Override
        public Aluguel buscarPorCpf (String id){
            return null;
        }

        public Aluguel buscarPorCnpj(String id){
            return null;
        }

        @Override
        public boolean deletar (String id){
            return false;
        }

        @Override
        public void alugarVeiculo (Cliente cliente, Veiculo veiculo, String localRetirada,long dataInicio){

        }
        @Override
        public void devolverVeiculo (Cliente cliente, Veiculo veiculo, String localDevolucao,long dataFim){

        }

    }