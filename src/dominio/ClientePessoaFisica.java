package dominio;

public class ClientePessoaFisica extends Cliente{

    private String cpf;

    public ClientePessoaFisica(String nome, int idade, String logradouro, String cpf) {
        super(nome, idade, logradouro);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {

        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido. O CPF deve conter 11 dígitos.");
        }
        this.cpf = cpf;
    }
}
