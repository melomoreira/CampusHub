package Controller;

// Bibliotecas
import Model.Atletica;
import java.util.ArrayList;

// Classe AtleticaController
public class AtleticaController {
    // Atributos
    private ArrayList<Atletica> atleticas;

    // Construtor
    public AtleticaController() {
        atleticas = new ArrayList<>();
    }

    // CREATE - Cadastrar atlética
    public void cadastrarAtletica(Atletica atletica) {
        atleticas.add(atletica);
    }

    // READ - Listar atléticas
    public ArrayList<Atletica> listarAtleticas() {
        return new ArrayList<>(atleticas);
    }

    // READ - Buscar atlética pelo nome
    public Atletica buscarAtletica(String nome) {
        for (Atletica atletica : atleticas) {
            if (atletica.getNome().equalsIgnoreCase(nome)) {
                return atletica;
            }
        }

        return null;
    }

    // UPDATE - Atualizar atlética
    public boolean atualizarAtletica(String nome, String novoNome, String novaDescricao) {
        Atletica atletica = buscarAtletica(nome);

        if (atletica != null) {
            atletica.setNome(novoNome);
            atletica.setDescricao(novaDescricao);
            return true;
        }

        return false;
    }

    // DELETE - Excluir atlética
    public boolean excluirAtletica(String nome) {
        Atletica atletica = buscarAtletica(nome);

        if (atletica != null) {
            atleticas.remove(atletica);
            return true;
        }

        return false;
    }
}