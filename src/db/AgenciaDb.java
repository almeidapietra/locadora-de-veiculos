package db;

import dominio.Agencia;
import interfaces.IBancoDeDados;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class AgenciaDb implements IBancoDeDados<Agencia> {
    private List<Agencia> agencias = new ArrayList<>();
    File file = new File("agencia.ser");

    public AgenciaDb() {
        DadosWR.carregarDados(file, agencias, "Agencia");
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
        DadosWR.salvarDados(file, agencias);
        return true;
    }

    @Override
    public boolean alterar(Agencia agencia) {
        for (Agencia a : agencias) {
            if (a.getId().equals(agencia.getId())) {
                agencias.set(agencias.indexOf(a), agencia);
                DadosWR.salvarDados(file, agencias);
                return true;
            }
        }
        // Exceção se a agência não for encontrada
        throw new IllegalArgumentException("Erro! Agência não encontrada.");
    }

    @Override
    public Agencia buscar(String valor) {
        for (Agencia agencia : agencias) {
            if (agencia.getNome().toLowerCase().contains(valor.toLowerCase())) {
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
    public boolean deletar(String nome) {
        boolean removed = false;
        for (Agencia agencia : agencias) {
            if (agencia.getNome().equalsIgnoreCase(nome)) {
                agencias.remove(agencia);
                DadosWR.salvarDados(file, agencias);
                removed = true;
                return removed;
            }
        }
        if (!removed) {
            throw new IllegalArgumentException("Erro ao excluir! Agência não foi encontrada.");
        }
        return removed;
    }
}