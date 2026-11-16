package Controller;

// Bibliotecas
import Model.Missao;
import Model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Classe MissaoController
public class MissaoController {
    // Atributos
    private ArrayList<Missao> missoes;

    // Guarda as missões concluídas por cada usuário.
    // A chave é o e-mail do usuário.
    private Map<String, Set<String>> progressoUsuarios;

    private static MissaoController instancia;

    // Construtor privado
    private MissaoController() {
        missoes = new ArrayList<>();
        progressoUsuarios = new HashMap<>();

        cadastrarMissoesIniciais();
    }

    // Singleton
    public static MissaoController getInstancia() {
        if (instancia == null) {
            instancia = new MissaoController();
        }

        return instancia;
    }

    // Cadastro das missões iniciais
    private void cadastrarMissoesIniciais() {
        missoes.add(new Missao(
            "missao-001",
            "ufg-samambaia",
            "biblioteca-central",
            "Conhecimento em movimento",
            "Visite a Biblioteca Central e descubra um novo espaço de aprendizado.",
            50
        ));

        missoes.add(new Missao(
            "missao-002",
            "ufg-samambaia",
            "restaurante-universitario",
            "Pausa para recarregar",
            "Conheça o Restaurante Universitário e explore um dos espaços de convivência do campus.",
            30
        ));

        missoes.add(new Missao(
            "missao-003",
            "ufg-samambaia",
            "reitoria",
            "Onde tudo se conecta",
            "Visite a Reitoria e conheça um pouco mais sobre a administração da universidade.",
            40
        ));

        missoes.add(new Missao(
            "missao-004",
            "ufg-samambaia",
            "centro-convivencia",
            "Encontro no campus",
            "Explore o Centro de Convivência e descubra um espaço de interação universitária.",
            30
        ));
    }

    // Cadastro
    public boolean cadastrarMissao(Missao missao) {
        if (missao == null || buscarMissaoPorId(missao.getId()) != null) {
            return false;
        }

        missoes.add(missao);

        return true;
    }

    // Listagem
    public ArrayList<Missao> listarMissoes() {
        return new ArrayList<>(missoes);
    }

    // Listagem por campus
    public ArrayList<Missao> listarMissoesDoCampus(String campusId) {
        ArrayList<Missao> resultado = new ArrayList<>();

        for (Missao missao : missoes) {
            if (missao.getCampusId().equals(campusId)) {
                resultado.add(missao);
            }
        }

        return resultado;
    }

    // Busca por ID
    public Missao buscarMissaoPorId(String id) {
        for (Missao missao : missoes) {
            if (missao.getId().equals(id)) {
                return missao;
            }
        }

        return null;
    }

    // Conclusão de missão
    public boolean concluirMissao(Usuario usuario, String missaoId) {
        if (usuario == null || missaoId == null || buscarMissaoPorId(missaoId) == null) {
            return false;
        }

        String email = usuario.getEmail();

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        Set<String> concluidas = progressoUsuarios.computeIfAbsent(
            email.toLowerCase(),
            chave -> new HashSet<>()
        );

        return concluidas.add(missaoId);
    }

    // Verificação de missão concluída
    public boolean missaoFoiConcluida(Usuario usuario, String missaoId) {
        if (usuario == null || usuario.getEmail() == null) {
            return false;
        }

        Set<String> concluidas = progressoUsuarios.get(
            usuario.getEmail().toLowerCase()
        );

        return concluidas != null && concluidas.contains(missaoId);
    }

    // Cálculo de pontos
    public int calcularPontos(Usuario usuario) {
        if (usuario == null || usuario.getEmail() == null) {
            return 0;
        }

        int total = 0;

        for (Missao missao : missoes) {
            if (missaoFoiConcluida(usuario, missao.getId())) {
                total += missao.getRecompensa();
            }
        }

        return total;
    }

    // Contagem de missões concluídas
    public int contarMissoesConcluidas(Usuario usuario) {
        if (usuario == null || usuario.getEmail() == null) {
            return 0;
        }

        Set<String> concluidas = progressoUsuarios.get(
            usuario.getEmail().toLowerCase()
        );

        return concluidas == null ? 0 : concluidas.size();
    }
}