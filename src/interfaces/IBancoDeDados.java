package interfaces;

public interface IBancoDeDados <T>{
    boolean cadastrar(T entidade);
    boolean alterar(T entidade);
    T buscarPorId(String id);
    boolean deletar(String id);
}
