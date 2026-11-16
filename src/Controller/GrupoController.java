package Controller;

// Bibliotecas
import Model.Grupo;
import Model.Usuario;

import java.util.ArrayList;

// Classe GrupoController
public class GrupoController {
    // Instância compartilhada
    private static final GrupoController instancia = new GrupoController();

    // Lista de grupos
    private final ArrayList<Grupo> grupos;

    // Construtor privado
    private GrupoController() {
        grupos = new ArrayList<>();

        cadastrarGruposIniciais();
    }

    // Singleton
    public static GrupoController getInstancia() {
        return instancia;
    }

    // Grupos iniciais do CampusHub
    private void cadastrarGruposIniciais() {
        grupos.add(new Grupo(
            "Tecnologia e Programação",
            "Um espaço para compartilhar projetos, aprender programação e trocar experiências.",
            null
        ));

        grupos.add(new Grupo(
            "Arte e Cultura",
            "Para quem gosta de música, literatura, cinema, desenho e outras expressões artísticas.",
            null
        ));

        grupos.add(new Grupo(
            "Ciência e Pesquisa",
            "Conecte-se com estudantes interessados em pesquisa, ciência e iniciação acadêmica.",
            null
        ));

        grupos.add(new Grupo(
            "Jogos e Cultura Geek",
            "Um ponto de encontro para fãs de jogos, RPG, tecnologia e cultura geek.",
            null
        ));
    }

    // =========================
    // CADASTRO
    // =========================

    public void cadastrarGrupo(Grupo grupo) {
        if (grupo != null) {
            grupos.add(grupo);
        }
    }

    // =========================
    // LISTAGEM
    // =========================

    public ArrayList<Grupo> listarGrupos() {
        return grupos;
    }

    // =========================
    // BUSCA
    // =========================

    public Grupo buscarGrupoPorId(String id) {
        for (Grupo grupo : grupos) {
            if (grupo.getId().equals(id)) {
                return grupo;
            }
        }

        return null;
    }

    // =========================
    // ATUALIZAÇÃO
    // =========================

    public boolean atualizarGrupo(String id, Grupo novosDados) {
        Grupo grupo = buscarGrupoPorId(id);

        if (grupo == null || novosDados == null) {
            return false;
        }

        grupo.setNome(novosDados.getNome());
        grupo.setDescricao(novosDados.getDescricao());

        return true;
    }

    // =========================
    // EXCLUSÃO
    // =========================

    public boolean excluirGrupo(String id) {
        Grupo grupo = buscarGrupoPorId(id);

        if (grupo == null) {
            return false;
        }

        return grupos.remove(grupo);
    }

    // =========================
    // PARTICIPAÇÃO
    // =========================

    public boolean participarGrupo(String id, Usuario usuario) {
        Grupo grupo = buscarGrupoPorId(id);

        if (grupo == null || usuario == null) {
            return false;
        }

        if (grupo.getParticipantes().contains(usuario)) {
            return false;
        }

        grupo.adicionarParticipante(usuario);

        return true;
    }

    // =========================
    // SAIR DO GRUPO
    // =========================

    public boolean sairGrupo(String id, Usuario usuario) {
        Grupo grupo = buscarGrupoPorId(id);

        if (grupo == null || usuario == null) {
            return false;
        }

        if (!grupo.getParticipantes().contains(usuario)) {
            return false;
        }

        grupo.removerParticipante(usuario);

        return true;
    }
}