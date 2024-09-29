package db;

import dominio.Cliente;
import interfaces.IBancoDeDados;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteDb implements IBancoDeDados<Cliente> {
    private List<Cliente> clientes = new ArrayList<>();
    File file = new File("clientes.ser");

    public ClienteDb() {
        DadosWR.carregarDados(file, clientes, "Cliente");
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        for (Cliente a : clientes) {
            if (a.getNome().equals(cliente.getNome()) && a.getCpf().equals(cliente.getCpf())) {
                return false;
            }
        }
        clientes.add(cliente);
        DadosWR.salvarDados(file, clientes);
        return true;
    }

    @Override
    public boolean alterar(Cliente cliente) {
        for (Cliente a : clientes) {
            if (a.getNome().equals(cliente.getNome())) {
                clientes.set(clientes.indexOf(a), cliente);
                DadosWR.salvarDados(file, clientes);
                return true;
            }
        }
        throw new IllegalArgumentException("Erro! Cliente n�o encontrado.");
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
                DadosWR.salvarDados(file, clientes);
                return true;
            }
        }
        throw new IllegalArgumentException("Erro ao excluir! Cliente n�o foi encontrado.");

    }
}