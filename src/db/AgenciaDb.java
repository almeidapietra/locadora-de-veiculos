package db;

import dominio.Agencia;
import interfaces.IBancoDeDados;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDb implements IBancoDeDados<Agencia> {
    private List<Agencia> agencias = new ArrayList<>();

    @Override
    public boolean cadastrar(Agencia agencia) {
        // RN5: Agências não podem estar duplicadas;
        for (Agencia a : agencias) {
            if (a.getNome().equals(agencia.getNome()) && a.getEndereco().equals(agencia.getEndereco())) {
                return false; // Agencia duplicada
            }
        }
        agencias.add(agencia);
        return true;
    }

    @Override
    public boolean alterar(Agencia agencia) {
        for (Agencia a : agencias) {
            if (a.getId().equals(agencia.getId())) {
                agencias.set(agencias.indexOf(a), agencia);
                return true;
            }
        }
        // Exceção se a agência não for encontrada
        throw new IllegalArgumentException("Erro! Agência não encontrada.");
    }

    @Override
    public Agencia buscarPorId(String id) {
        for (Agencia agencia : agencias) {
            if (agencia.getId().equals(id)) {
                return agencia;
            }
        }
        return null;
    }

    public Agencia buscarPorNome(String nome) {
        for (Agencia agencia : agencias) {
            if (agencia.getNome().equalsIgnoreCase(nome)) {
                return agencia;
            }
        }
        return null;
    }

    public Agencia buscarPorEndereco(String endereco) {
        for (Agencia agencia : agencias) {
            if (agencia.getEndereco().contains(endereco)) {
                return agencia;
            }
        }
        return null;
    }

    @Override
    public boolean deletar(String id) {
        boolean removed = agencias.removeIf(agencia -> agencia.getId().equals(id));
        if (!removed) {
            throw new IllegalArgumentException("Erro ao excluir! Agência não foi encontrada.");
        }
        return removed;
    }
}