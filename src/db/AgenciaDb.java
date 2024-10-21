package db;

import dominio.Agencia;
import interfaces.IBancoDeDados;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;


public class AgenciaDb implements IBancoDeDados<Agencia> {
    private List<Agencia> agencias;
    File file;

    public AgenciaDb() {
        agencias = new ArrayList<>();
        file = new File("agencia.ser");
        DadosWR<Agencia> DadosWR = new DadosWR<Agencia>();
        DadosWR.carregarDados(file, agencias);
        Agencia.setUltimoId(agencias.size());
    }

    public void salvarDados() {
        DadosWR.salvarDados(file, agencias);
    }

    @Override
    public boolean cadastrar(Agencia agencia) {
        // RN5: Agências não podem estar duplicadas;
        for (Agencia a : agencias) {
            if (a.getNome().equals(agencia.getNome()) && a.getEndereco().equals(agencia.getEndereco())) {
                return false; // Agencia duplicada
            }
        }
        agencias.add(agencia);
        salvarDados();
        return true;
    }

    @Override
    public boolean alterar(Agencia agencia) {
        for (Agencia a : agencias) {
            if (a.getId().equals(agencia.getId())) {
                agencias.set(agencias.indexOf(a), agencia);
                salvarDados();
                return true;
            }
        }
        // Exceção se a agência não for encontrada
        throw new IllegalArgumentException("Erro! Agência não encontrada.");
    }

    @Override
    public Agencia buscar(String valor) {
        for (Agencia agencia : agencias) {
            if (agencia.getNome().toLowerCase().contains(valor.toLowerCase()) ||
                    agencia.getEndereco().toLowerCase().contains(valor.toLowerCase())) {
                return agencia;
            }
        }
        return null;
    }

    @Override
    public boolean deletar(String nome) {
        boolean removed = false;
        for (Agencia agencia : agencias) {
            if (agencia.getNome().equalsIgnoreCase(nome)) {
                agencias.remove(agencia);
                salvarDados();
                removed = true;
                break;
            }
        }
        if (!removed) {
            throw new IllegalArgumentException("Erro ao excluir! Agência não foi encontrada.");
        }
        return removed;
    }

    public List<Agencia> listar() {
        return new ArrayList<>(agencias);  // Vai retornar lista de agências
    }

    public void gerarRelatorioAgenciasCSV() {
        String filePath = "relatorio_agencias.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Nome da Agência, Endereço\n");

            // Iterar pelas agências e escrever os dados corretamente no CSV
            for (Agencia agencia : agencias) {
                writer.append(agencia.getNome()).append(",")
                        .append(agencia.getEndereco()).append("\n");
            }

            System.out.println("Relatório CSV gerado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o relatório CSV: " + e.getMessage());
        }
    }

    public void lerAgenciasDoCSV(String caminhoArquivo) {
        String linha = "";
        String separador = ","; // Usualmente, o separador padrão é a vírgula
        boolean novaAgenciaImportada = false;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            while ((linha = br.readLine()) != null) {
                String[] dadosAgencia = linha.split(separador);

                // Supondo que o CSV tenha o formato: Nome, Endereço
                if (dadosAgencia.length >= 2) {
                    String nomeAgencia = dadosAgencia[0];
                    String enderecoAgencia = dadosAgencia[1];

                    // Criar uma nova instância de Agência
                    Agencia agencia = new Agencia(nomeAgencia, enderecoAgencia);

                    // Verificar se a agência já existe
                    if (agenciaJaExiste(agencia)) {
                        System.out.println("Erro: A agência '" + nomeAgencia + "' já foi cadastrada.");
                    } else {
                        // Adicionar à lista se não existir e exibir no console
                        agencias.add(agencia);
                        System.out.println("Nome: " + nomeAgencia + ", Endereço: " + enderecoAgencia);
                        novaAgenciaImportada = true;
                    }
                } else {
                    System.out.println("Formato inválido no CSV para a linha: " + linha);
                }
            }

            if (novaAgenciaImportada) {
                System.out.println("Importação do CSV concluída com sucesso!");
            } else {
                System.out.println("Nenhuma nova agência foi importada.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }

    // Função auxiliar para verificar se a agência já existe usando Stream API
    private boolean agenciaJaExiste(Agencia novaAgencia) {
        return agencias.stream()
                .anyMatch(agencia -> agencia.getNome().equalsIgnoreCase(novaAgencia.getNome()) &&
                        agencia.getEndereco().equalsIgnoreCase(novaAgencia.getEndereco()));
    }


}




