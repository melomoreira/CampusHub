package Model;

// Classe que representa um campus universitário
public class Campus {
    // Identificação do campus
    private String id;

    // Informações do campus
    private String universidade;
    private String nome;
    private String descricao;

    // Caminho da imagem do mapa do campus
    private String caminhoImagem;

    // Construtor com as informações do campus
    public Campus(String id, String universidade, String nome, String descricao, String caminhoImagem) {
        this.id = id;
        this.universidade = universidade;
        this.nome = nome;
        this.descricao = descricao;
        this.caminhoImagem = caminhoImagem;
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getId() {
        return id;
    }

    public String getUniversidade() {
        return universidade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setId(String id) {
        this.id = id;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}