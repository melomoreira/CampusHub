package Model;

// Bibliotecas utilizadas
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe que representa um evento do sistema
public class Evento {
    // Atributos do evento
    private final String id;
    private String titulo;
    private String data;
    private String horario;
    private String local;
    private String descricao;
    private Usuario organizador;
    private List<Usuario> participantes;

    // Construtor com informações básicas do evento
    public Evento(String data, String horario, String local, String descricao) {
        this("Evento", data, horario, local, descricao, null);
    }

    // Construtor com todas as informações do evento
    public Evento(String titulo, String data, String horario, String local, String descricao, Usuario organizador) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.descricao = descricao;
        this.organizador = organizador;
        this.participantes = new ArrayList<>();
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getLocal() {
        return local;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    // Métodos para gerenciar os participantes do evento
    public void adicionarParticipante(Usuario usuario) {
        if (usuario != null && !participantes.contains(usuario)) {
            participantes.add(usuario);
        }
    }

    public void removerParticipante(Usuario usuario) {
        participantes.remove(usuario);
    }
}