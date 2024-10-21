package db;

import dominio.Cliente;
import dominio.Veiculo;
import dominio.Aluguel;
import interfaces.IBancoDeDados;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VeiculoDb implements IBancoDeDados<Veiculo> {

    private List<Veiculo> veiculos;
    private List<Aluguel> alugueis; // Inicializando a lista de aluguéis
    private final File file;

    public VeiculoDb(List<Aluguel> alugueis) {
        veiculos = new ArrayList<>();
        this.alugueis = new ArrayList<>(); // Inicialização da lista de aluguéis
        file = new File("veiculos.ser");
        DadosWR<Veiculo> dadosWR = new DadosWR<>();
        dadosWR.carregarDados(file, veiculos);
    }

    public void salvarDados() {
        DadosWR<Veiculo> dadosWR = new DadosWR<>();
        dadosWR.salvarDados(file, veiculos);
    }

    @Override
    public boolean cadastrar(Veiculo veiculo) {
        if (veiculoJaExiste(veiculo.getPlaca())) {
            System.out.println("Já existe um veículo com a mesma placa!");
            return false;
        }
        veiculos.add(veiculo);
        salvarDados();
        return true;
    }

    @Override
    public boolean alterar(Veiculo veiculo) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                veiculos.set(i, veiculo);
                salvarDados();
                return true;
            }
        }
        System.out.println("Veículo não encontrado para alteração!");
        return false;
    }

    @Override
    public Veiculo buscar(String valor) {
        for (Veiculo v : veiculos) {
            if (v.getModelo().toLowerCase().contains(valor.toLowerCase())) {
                return v;
            }
        }
        System.out.println("Veículo não encontrado!");
        return null;
    }

    @Override
    public boolean deletar(String valor) {
        Iterator<Veiculo> iterator = veiculos.iterator();
        while (iterator.hasNext()) {
            Veiculo veiculo = iterator.next();
            if (veiculo.getPlaca().equalsIgnoreCase(valor)) {
                iterator.remove();
                salvarDados();
                return true;
            }
        }
        System.err.println("Erro ao excluir! Veículo não foi encontrado.");
        return false;
    }

    private boolean veiculoJaExiste(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    public List<Veiculo> listar() {
        return new ArrayList<>(veiculos); // Retorna uma cópia para evitar modificações externas
    }

    public void gerarComprovanteDevolucaoCSV(List<Aluguel>alugueis) {
        String filePath = "comprovante_devolucao.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("ID do Aluguel,Cliente,Veículo,Local de Retirada,Local de Devolução,Data Início,Data Fim,Valor Total\n");

            // Itera sobre os aluguéis e escreve os dados no CSV
            for (Aluguel aluguel : alugueis) {
                writer.append(String.valueOf(aluguel.getId())).append(",")
                        .append(aluguel.getCliente().getNome()).append(",")
                        .append(aluguel.getVeiculo().getModelo()).append(",")
                        .append(aluguel.getLocalRetirada()).append(",")
                        .append(aluguel.getLocalDevolucao()).append(",")
                        .append(aluguel.getDataInicio().toString()).append("\n");
                       // .append(aluguel.getDataFim().toString()).append(",")
                       // .append(String.valueOf(aluguel.getValorTotal())).append("\n");
            }

            System.out.println("Comprovante de devolução CSV gerado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o comprovante de devolução CSV: " + e.getMessage());
        }
    }

    public void lerComprovantesDoCSV(String caminhoArquivo) {
        String linha;
        String separador = ","; // O separador padrão é vírgula

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            // Ignora o cabeçalho
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dadosAluguel = linha.split(separador);

                // Verifica se a linha tem os dados esperados
                if (dadosAluguel.length >= 8) {
                    String id = dadosAluguel[0];
                    String nomeCliente = dadosAluguel[1];
                    String modeloVeiculo = dadosAluguel[2];
                    String localRetirada = dadosAluguel[3];
                    String localDevolucao = dadosAluguel[4];
                    long dataInicio = Long.parseLong(dadosAluguel[5]);
                    LocalDate dataFim = LocalDate.parse(dadosAluguel[6]);
                    double valorTotal = Double.parseDouble(dadosAluguel[7]);

                    // Cria um novo objeto Aluguel e adiciona à lista
                    Cliente cliente = new Cliente(); // Supondo que existe um construtor adequado
                    Veiculo veiculo = new Veiculo(modeloVeiculo); // Supondo que existe um construtor adequado
                    Aluguel aluguel = new Aluguel(id, veiculo, cliente,localRetirada,dataInicio) ;
                    alugueis.add(aluguel);

                } else {
                    System.out.println("Formato inválido na linha: " + linha);
                }
            }
            System.out.println("Importação dos comprovantes concluída com sucesso!");
        } catch (FileNotFoundException ex) {
            System.err.println("Arquivo não encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro ao importar o arquivo CSV: " + ex.getMessage());
        }
    }
}
