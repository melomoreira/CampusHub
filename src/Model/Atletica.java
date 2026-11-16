package Model;

// Classe que representa uma atlética universitária
public class Atletica {
    // Atributos da atlética
    private String nome;
    private String descricao;

    // Construtor com as informações da atlética
    public Atletica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}