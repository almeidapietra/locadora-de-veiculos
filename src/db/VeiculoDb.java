package db;
import dominio.Veiculo;
import interfaces.IBancoDeDados;

public class VeiculoDb implements IBancoDeDados<Veiculo> {

    @Override
    public boolean cadastrar(Veiculo entidade) {
        return false;
    }

    @Override
    public boolean alterar(Veiculo entidade) {
        return false;
    }

    @Override
    public Veiculo buscarPorId(String id) {
        return null;
    }

    @Override
    public boolean deletar(String id) {
        return false;
    }
}
