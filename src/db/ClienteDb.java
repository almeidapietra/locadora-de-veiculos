package db;

import dominio.Cliente;
import interfaces.IBancoDeDados;

public class ClienteDb implements IBancoDeDados<Cliente> {
    @Override
    public boolean cadastrar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean alterar(Cliente cliente) {
        return false;
    }

    @Override
    public Cliente buscarPorId(String id) {
        return null;
    }

    @Override
    public boolean deletar(String id) {
        return false;
    }
}
