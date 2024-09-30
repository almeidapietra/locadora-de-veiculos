package db;

import dominio.*;
import interfaces.IBancoDeDados;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteDb implements IBancoDeDados<Cliente> {
    private List<Cliente> clientes;
    File file;

    public ClienteDb() {
        clientes = new ArrayList<>();
        file = new File("clientes.ser");
        DadosWR<Cliente> DadosWR = new DadosWR<Cliente>();
        DadosWR.carregarDados(file, clientes);
    }

    public void salvarDados() {
        DadosWR.salvarDados(file, clientes);
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        for (Cliente a : clientes) {
            if (a.getNome().equals(cliente.getNome())) {
                return false;
            }
        }
            clientes.add(cliente);
            salvarDados();
            return true;
    }

    @Override
    public boolean alterar(Cliente entidade) {
        for (Cliente a : clientes) {
            if (a.getNome().equals(entidade.getNome())) {
                clientes.set(clientes.indexOf(a), entidade);
                salvarDados();
                return true;
            }
        }
        throw new IllegalArgumentException("Erro! Cliente não encontrado.");
    }

    @Override
    public Cliente buscar(String valor) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().toLowerCase().contains(valor.toLowerCase())) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean deletar(String valor) {
        Iterator<Cliente> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getNome().toLowerCase().contains(valor.toLowerCase())) {
                iterator.remove();
                salvarDados();
                return true;
            }
        }
        throw new IllegalArgumentException("Erro ao excluir! Cliente não foi encontrado.");
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }
}