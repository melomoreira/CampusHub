package Model;

// Classe que representa uma missão do sistema
public class Missao {
    // Identificação da missão e seus relacionamentos
    private String id;
    private String campusId;
    private String pontoInteresseId;

    // Informações da missão
    private String titulo;
    private String descricao;
    private int recompensa;

    // Construtor com todas as informações da missão
    public Missao(String id, String campusId, String pontoInteresseId, String titulo, String descricao, int recompensa) {
        this.id = id;
        this.campusId = campusId;
        this.pontoInteresseId = pontoInteresseId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.recompensa = recompensa;
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getId() {
        return id;
    }

    public String getCampusId() {
        return campusId;
    }

    public String getPontoInteresseId() {
        return pontoInteresseId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getRecompensa() {
        return recompensa;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setId(String id) {
        this.id = id;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public void setPontoInteresseId(String pontoInteresseId) {
        this.pontoInteresseId = pontoInteresseId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setRecompensa(int recompensa) {
        this.recompensa = recompensa;
    }
}