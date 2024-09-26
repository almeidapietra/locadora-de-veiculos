package db;

import dominio.Cliente;
import interfaces.IBancoDeDados;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDb implements IBancoDeDados<Cliente> {
    private List<Cliente> clientes = new ArrayList<>();
    File file = new File("clientes.ser");

    public ClienteDb() {
        carregarDados();
    }

    private void carregarDados() {
        if (file.exists()) {
            try (ObjectInputStream arquivo = new ObjectInputStream(new FileInputStream(file))) {
                clientes = (List<Cliente>) arquivo.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream arquivo = new ObjectOutputStream(new FileOutputStream(file))) {
            arquivo.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        for (Cliente a : clientes) {
            if (a.getNome().equals(cliente.getNome()) && a.getCpf().equals(cliente.getCpf())) {
                return false;
            }
        }
        clientes.add(cliente);
        salvarDados();
        return true;
    }

    @Override
    public boolean alterar(Cliente cliente) {
        for (Cliente a : clientes) {
            if (a.getNome().equals(cliente.getNome())) {
                clientes.set(clientes.indexOf(a), cliente);
                salvarDados();
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
        boolean removed = false;
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(valor)) {
                clientes.remove(cliente);
                salvarDados();
                removed = true;
                return removed;
            }
        }
        if (!removed) {
            throw new IllegalArgumentException("Erro ao excluir! Cliente n�o foi encontrado.");
        }
        return removed;
    }
}