package dominio;
import java.io.Serializable;

public class Agencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int ultimoId = 0;
    private Integer id;
    private String nome;
    private String endereco;

    public Agencia() {
    }

    public Agencia(String nome, String endereco) {
        this.id = ++ultimoId;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static void setUltimoId(int ultimoId) {
        Agencia.ultimoId = ultimoId;
    }

    @Override
    public String toString() {
        return "Agencia: " +
                "Id = " + id +
                ", Nome = " + nome +
                ", Endereco = " + endereco +
                '.';
    }
}