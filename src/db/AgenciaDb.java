package db;

import dominio.Agencia;
import dominio.Veiculo;
import interfaces.IBancoDeDados;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class AgenciaDb implements IBancoDeDados<Agencia> {
    private List<Agencia> agencias;
    File file;

    public AgenciaDb() {
        agencias = new ArrayList<>();
        file = new File("agencia.ser");
        DadosWR<Agencia> DadosWR = new DadosWR<Agencia>();
        DadosWR.carregarDados(file, agencias);
        Agencia.setUltimoId(agencias.size());
    }

    public void salvarDados() {
        DadosWR.salvarDados(file, agencias);
    }

    @Override
    public boolean cadastrar(Agencia agencia) {
        // RN5: Agências não podem estar duplicadas;
        for (Agencia a : agencias) {
            if (a.getNome().equals(agencia.getNome()) && a.getEndereco().equals(agencia.getEndereco())) {
                return false; // Agencia duplicada
            }
        }
        agencias.add(agencia);
        salvarDados();
        return true;
    }

    @Override
    public boolean alterar(Agencia agencia) {
        for (Agencia a : agencias) {
            if (a.getId().equals(agencia.getId())) {
                agencias.set(agencias.indexOf(a), agencia);
                salvarDados();
                return true;
            }
        }
        // Exceção se a agência não for encontrada
        throw new IllegalArgumentException("Erro! Agência não encontrada.");
    }

    @Override
    public Agencia buscar(String valor) {
        for (Agencia agencia : agencias) {
            if (agencia.getNome().toLowerCase().contains(valor.toLowerCase()) ||
                    agencia.getEndereco().toLowerCase().contains(valor.toLowerCase())) {
                return agencia;
            }
        }
        return null;
    }

    @Override
    public boolean deletar(String nome) {
        boolean removed = false;
        for (Agencia agencia : agencias) {
            if (agencia.getNome().equalsIgnoreCase(nome)) {
                agencias.remove(agencia);
                salvarDados();
                removed = true;
                break;
            }
        }
        if (!removed) {
            throw new IllegalArgumentException("Erro ao excluir! Agência não foi encontrada.");
        }
        return removed;
    }

    public List<Agencia> listar() {
        return new ArrayList<>(agencias);  // Vai retornar lista de agências
    }

}