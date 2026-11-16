package Controller;

// Bibliotecas
import Model.AtividadeAcademica;
import java.util.ArrayList;

// Classe AtividadeAcademicaController
public class AtividadeAcademicaController {
    // Atributos
    private ArrayList<AtividadeAcademica> atividades;

    // Construtor
    public AtividadeAcademicaController() {
        atividades = new ArrayList<>();
    }

    // CREATE - Cadastrar atividade
    public void cadastrarAtividade(AtividadeAcademica atividade) {
        atividades.add(atividade);
    }

    // READ - Listar atividades
    public ArrayList<AtividadeAcademica> listarAtividades() {
        return new ArrayList<>(atividades);
    }

    // READ - Buscar atividade pelo tipo
    public AtividadeAcademica buscarAtividade(String tipo) {
        for (AtividadeAcademica atividade : atividades) {
            if (atividade.getTipo().equalsIgnoreCase(tipo)) {
                return atividade;
            }
        }

        return null;
    }

    // UPDATE - Atualizar atividade
    public boolean atualizarAtividade(String tipo, String novoTipo, String novaDescricao) {
        AtividadeAcademica atividade = buscarAtividade(tipo);

        if (atividade != null) {
            atividade.setTipo(novoTipo);
            atividade.setDescricao(novaDescricao);
            return true;
        }

        return false;
    }

    // DELETE - Excluir atividade
    public boolean excluirAtividade(String tipo) {
        AtividadeAcademica atividade = buscarAtividade(tipo);

        if (atividade != null) {
            atividades.remove(atividade);
            return true;
        }

        return false;
    }
}