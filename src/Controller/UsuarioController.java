package Controller;

// Bibliotecas utilizadas
import Model.Usuario;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// Classe responsável pelo gerenciamento dos usuários
public class UsuarioController {
    // Lista que armazena os usuários cadastrados
    private ArrayList<Usuario> usuarios;

    // Construtor que inicializa a lista de usuários
    public UsuarioController() {
        usuarios = new ArrayList<>();
    }

    // CREATE - Cadastrar usuário
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // CREATE - Cadastrar usuário verificando e-mails duplicados
    public boolean cadastrarUsuarioSeguro(Usuario usuario) {
        if (usuario == null || usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            return false;
        }

        if (buscarUsuarioPorEmail(usuario.getEmail()) != null) {
            return false;
        }

        usuarios.add(usuario);
        return true;
    }

    // READ - Listar todos os usuários cadastrados
    public ArrayList<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    // READ - Buscar usuário pelo nome
    public Usuario buscarUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }

        return null;
    }

    // READ - Buscar usuário pelo e-mail
    public Usuario buscarUsuarioPorEmail(String email) {
        if (email == null) {
            return null;
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail() != null && usuario.getEmail().equalsIgnoreCase(email.trim())) {
                return usuario;
            }
        }

        return null;
    }

    // READ - Listar universidades sem repetir nomes
    public ArrayList<String> listarUniversidades() {
        Map<String, String> universidades = new LinkedHashMap<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getUniversidade() != null && !usuario.getUniversidade().trim().isEmpty()) {
                String nome = usuario.getUniversidade().trim();

                universidades.putIfAbsent(nome.toLowerCase(), nome);
            }
        }

        return new ArrayList<>(universidades.values());
    }

    // LOGIN - Autenticar usuário utilizando e-mail e senha
    public Usuario autenticarUsuario(String email, String senha) {
        if (email == null || senha == null) {
            return null;
        }

        Usuario usuario = buscarUsuarioPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }

        return null;
    }

    // UPDATE - Atualizar informações do usuário
    public boolean atualizarUsuario(String nome, String novoNome, String novaUniversidade, String novoCurso) {
        Usuario usuario = buscarUsuario(nome);

        if (usuario != null) {
            usuario.setNome(novoNome);
            usuario.setUniversidade(novaUniversidade);
            usuario.setCurso(novoCurso);
            return true;
        }

        return false;
    }

    // DELETE - Excluir usuário pelo nome
    public boolean excluirUsuario(String nome) {
        Usuario usuario = buscarUsuario(nome);

        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }

        return false;
    }
}