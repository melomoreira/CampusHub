package Model;

// Bibliotecas utilizadas
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe que representa um grupo do sistema
public class Grupo {
    // Identificador único do grupo
    private final String id;

    // Informações do grupo
    private String nome;
    private String descricao;

    // Criador e lista de participantes
    private final Usuario criador;
    private final List<Usuario> participantes;

    // Construtor com nome e descrição
    public Grupo(String nome, String descricao) {
        this(nome, descricao, null);
    }

    // Construtor com nome, descrição e criador
    public Grupo(String nome, String descricao, Usuario criador) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.criador = criador;
        this.participantes = new ArrayList<>();
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getCriador() {
        return criador;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Métodos para gerenciar os participantes do grupo
    public void adicionarParticipante(Usuario usuario) {
        if (usuario != null && !participantes.contains(usuario)) {
            participantes.add(usuario);
        }
    }

    public void removerParticipante(Usuario usuario) {
        participantes.remove(usuario);
    }
}