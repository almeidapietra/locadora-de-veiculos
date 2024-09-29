package dominio;

public class Cliente {
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
