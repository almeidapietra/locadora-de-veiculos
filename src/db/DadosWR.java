package db;

import dominio.Agencia;
import dominio.Aluguel;
import dominio.Cliente;
import dominio.Veiculo;

import java.io.*;
import java.util.List;

public class DadosWR {

    public static void carregarDados(File file, List l, String c) {
        if (file.exists()) {
            try (ObjectInputStream arquivo = new ObjectInputStream(new FileInputStream(file))) {
                if (c == "Agencia"){
                    l = (List<Agencia>) arquivo.readObject();
                }
                if (c == "Aluguel"){
                    l = (List<Aluguel>) arquivo.readObject();
                }
                if (c == "Cliente"){
                    l = (List<Cliente>) arquivo.readObject();
                }
                if (c == "Veiculo"){
                    l = (List<Veiculo>) arquivo.readObject();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void salvarDados(File file, List l) {
        try (ObjectOutputStream arquivo = new ObjectOutputStream(new FileOutputStream(file))) {
            arquivo.writeObject(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
