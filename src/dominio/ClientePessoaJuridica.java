package dominio;

public class ClientePessoaJuridica extends Cliente {

    private String cnpj;

    public ClientePessoaJuridica(String nome, int idade, String logradouro, String cnpj) {
        super(nome, idade, logradouro);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {

        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d+")) {
            throw new IllegalArgumentException("CNPJ inválido. O CNPJ deve conter 14 digitos.");

        }
        this.cnpj = cnpj;
    }

}
