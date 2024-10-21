package dominio;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String logradouro ;

    //public Cliente(String nomeCliente) {
   // }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
        }
        this.nome = nome;
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public static class ClientePessoaFisica extends Cliente {
    }

    public static class ClientePessoaJuridica extends Cliente {
    }
}
