package dominio;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    private String logradouro ;

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {

    }

    public static class ClientePessoaFisica extends Cliente {
    }

    public static class ClientePessoaJuridica {
    }
}
