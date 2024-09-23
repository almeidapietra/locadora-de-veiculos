package dominio;

public class Aluguel {
    private String id;
    private Veiculo veiculo;
    private Cliente cliente;
    private String localRetirada;
    private String localDevolucao;
    private long dataInicio;
    private long dataFim;
    private boolean ativo;
    private boolean devolvido;

    public Aluguel(String id, Veiculo veiculo, Cliente cliente, String localRetirada, long dataInicio) {
        this.id = id;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.localRetirada = localRetirada;
        this.dataInicio = dataInicio;
        this.dataFim = 0;
        this.ativo = true;
        this.devolvido = false; // Inicialmente, não foi devolvido
    }

    public String getId() {
        return id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getLocalRetirada() {
        return localRetirada;
    }

    public String getLocalDevolucao() {
        return localDevolucao;
    }

    public long getDataInicio() {
        return dataInicio;
    }

    public long getDataFim() {
        return dataFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setLocalDevolucao(String localDevolucao) {
        this.localDevolucao = localDevolucao;
    }

    public void setDataFim(long dataFim) {
        this.dataFim = dataFim;
        this.ativo = false;  // Aluguel finalizado
        this.devolvido = true; // Marca como devolvido
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}

