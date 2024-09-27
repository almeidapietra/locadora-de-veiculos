package dominio;

import java.io.Serializable;
import java.util.Objects;

public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String placa;
    private String modelo;
    private boolean disponivel = true;

    public Veiculo(String placa, String modelo) {

        this.placa = placa;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(placa, veiculo.placa);
    }
    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }
}

