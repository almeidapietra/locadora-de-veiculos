package db;

import dominio.Veiculo;
import interfaces.IBancoDeDados;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDb implements IBancoDeDados<Veiculo> {

    private List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public boolean cadastrar(Veiculo veiculo) {
        if (veiculos.contains(veiculo)) {
            System.out.println("Veículo já cadastrado!");
            return false;
        } else if (veiculoJaExiste(veiculo.getPlaca())) {
            System.out.println("Já existe um veículo com a mesma placa!");
            return false;
        } else {
            veiculos.add(veiculo);
            System.out.println("Veículo cadastrado com sucesso!");
            return true;
        }
    }

    @Override
    public boolean alterar(Veiculo veiculo) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                veiculos.set(i, veiculo);
                System.out.println("Veículo alterado com sucesso!");
                return true;
            }
        }
        System.out.println("Veículo não encontrado para alteração!");
        return false;
    }

    @Override
    public Veiculo buscarPorId(String id) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(id)) {
                return v;
            }
        }
        System.out.println("Veículo não encontrado!");
        return null;
    }

    @Override
    public boolean deletar(String id) {
        return false;
    }

    private boolean veiculoJaExiste(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }
}



