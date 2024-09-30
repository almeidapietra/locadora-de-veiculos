package dominio;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Aluguel implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public Long getDataInicio() { return dataInicio; }

    public String DataInicioFormatado() {
        Instant instant = Instant.ofEpochMilli(dataInicio);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }

    public long getDataFim() {
        return dataFim;
    }

    public String DataFimFormatado() {
        Instant instant = Instant.ofEpochMilli(dataFim);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
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