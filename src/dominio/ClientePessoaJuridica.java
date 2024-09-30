package dominio;

public class ClientePessoaJuridica extends Cliente {

    private String cnpj;

    public ClientePessoaJuridica(String cnpj) {
        this.setCnpj(cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d+")) {
            throw new IllegalArgumentException("CNPJ inválido. O CNPJ deve conter 14 dígitos.");
        }
        this.cnpj = cnpj;
    }
}
