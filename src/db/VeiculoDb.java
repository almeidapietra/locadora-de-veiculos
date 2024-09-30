package db;

import dominio.Veiculo;
import interfaces.IBancoDeDados;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VeiculoDb implements IBancoDeDados<Veiculo> {

    public List<Veiculo> veiculos;
    File file;

    public VeiculoDb() {
        veiculos = new ArrayList<>();
        file = new File("veiculos.ser");
        DadosWR<Veiculo> DadosWR = new DadosWR<Veiculo>();
        DadosWR.carregarDados(file, veiculos);
    }

    public void salvarDados() {
        DadosWR.salvarDados(file, veiculos);
    }

    @Override
    public boolean cadastrar(Veiculo veiculo) {
        if (veiculoJaExiste(veiculo.getPlaca())) {
            System.out.println("Já existe um veículo com a mesma placa!");
        return false;
    } else {
        veiculos.add(veiculo);
        salvarDados();
        return true;
        }
    }

    @Override
    public boolean alterar(Veiculo veiculo) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                veiculos.set(i, veiculo);
                salvarDados();
                return true;
            }
        }
        System.out.println("Veículo não encontrado para alteração!");
        return false;
    }

    @Override
    public Veiculo buscar(String valor) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(valor)) {
                return v;
            }
        }
        System.out.println("Veículo não encontrado!");
        return null;
    }

    @Override
    public boolean deletar(String valor) {
    Iterator<Veiculo> iterator = veiculos.iterator();
    while (iterator.hasNext()) {
        Veiculo veiculo = iterator.next();
            if (veiculo.getPlaca().equalsIgnoreCase(valor)) {
                iterator.remove();
                salvarDados();
                return true;
            }
        }
     throw new IllegalArgumentException("Erro ao excluir! Cliente não foi encontrado.");
    }

private boolean veiculoJaExiste(String placa) {
    for (Veiculo v : veiculos) {
        if (v.getPlaca().equals(placa)) {
            return true;
        }
    }
    return false;
}
    public List<Veiculo> listar() {
        return veiculos;
    }

}





