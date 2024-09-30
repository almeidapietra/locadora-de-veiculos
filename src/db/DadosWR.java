package db;


import java.io.*;
import java.util.List;

public class DadosWR<T> {

    public DadosWR() {
    }

    public static <T> void carregarDados(File file, List<T> parametroLista) {
        if (file.exists()) {
            try (ObjectInputStream arquivo = new ObjectInputStream(new FileInputStream(file))) {
                List<T> dadosCarregados = (List<T>) arquivo.readObject();
                parametroLista.clear();
                parametroLista.addAll(dadosCarregados);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void salvarDados(File file, List<T> parametroLista) {
        try (ObjectOutputStream arquivo = new ObjectOutputStream(new FileOutputStream(file))) {
            arquivo.writeObject(parametroLista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
