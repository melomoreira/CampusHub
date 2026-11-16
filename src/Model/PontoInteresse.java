package Model;

// Classe que representa um ponto de interesse no campus
public class PontoInteresse {
    // Identificação do ponto de interesse
    private String id;
    private String campusId;

    // Informações do local
    private String nome;
    private String descricao;
    private String categoria;
    private String icone;

    // Posição do marcador no mapa (valores de 0.0 a 1.0)
    private double posX;
    private double posY;

    // Construtor com todas as informações do ponto de interesse
    public PontoInteresse(String id, String campusId, String nome, String descricao, String categoria, String icone, double posX, double posY) {
        this.id = id;
        this.campusId = campusId;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.icone = icone;
        this.posX = posX;
        this.posY = posY;
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getId() {
        return id;
    }

    public String getCampusId() {
        return campusId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIcone() {
        return icone;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setId(String id) {
        this.id = id;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}