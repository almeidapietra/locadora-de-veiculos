package interfaces;

public interface IBancoDeDados <T>{
    boolean cadastrar(T entidade);
    boolean alterar(T entidade);
    T buscar(String valor);
    boolean deletar(String valor);
}
