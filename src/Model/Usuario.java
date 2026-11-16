package Model;

// Bibliotecas utilizadas
import java.util.ArrayList;
import java.util.List;

// Classe que representa um usuário do sistema
public class Usuario {
    // Atributos do usuário
    private String nome;
    private String email;
    private String senha;
    private String universidade;
    private String curso;
    private List<String> interesses;
    private String fotoPerfilPath;

    // Construtor com informações básicas do usuário
    public Usuario(String nome, String universidade, String curso, List<String> interesses) {
        this.nome = nome;
        this.universidade = universidade;
        this.curso = curso;
        this.interesses = interesses == null ? new ArrayList<>() : new ArrayList<>(interesses);
    }

    // Construtor com todas as informações principais do usuário
    public Usuario(String nome, String email, String senha, String universidade, String curso, List<String> interesses) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.universidade = universidade;
        this.curso = curso;
        this.interesses = interesses == null ? new ArrayList<>() : new ArrayList<>(interesses);
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getUniversidade() {
        return universidade;
    }

    public String getCurso() {
        return curso;
    }

    public List<String> getInteresses() {
        return new ArrayList<>(interesses);
    }

    public String getFotoPerfilPath() {
        return fotoPerfilPath;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setInteresses(List<String> interesses) {
        this.interesses = interesses == null ? new ArrayList<>() : new ArrayList<>(interesses);
    }

    public void setFotoPerfilPath(String fotoPerfilPath) {
        this.fotoPerfilPath = fotoPerfilPath;
    }
}