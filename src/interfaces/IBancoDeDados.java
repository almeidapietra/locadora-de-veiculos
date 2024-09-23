package interfaces;

public interface IBancoDeDados <T>{
    boolean cadastrar(T entidade);
    boolean alterar(T entidade);
    T buscarPorId(String id);
    T buscarPorCpf(String cpf);
    T buscarPorCnpj(String cnpj);
    boolean deletar(String id);
}
