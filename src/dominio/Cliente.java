package dominio;

public class Cliente {


    private String nome;
    private int idade;
    private String logradouro;

    public Cliente(String nome, int idade, String logradouro) {
        this.nome = nome;
        this.idade = idade;
        this.logradouro = logradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {

        if (idade < 0) {
            throw new ArithmeticException("Idade não pode ser negativa");
        } else if (idade < 18) {
            throw new ArithmeticException("Você precisa ser maior de idade para alugar um veiculo");
        }

        this.idade = idade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
}


 /*
    public static class ClientePessoaFisica extends Cliente {
    }

    public static class ClientePessoaJuridica {
    }
    */