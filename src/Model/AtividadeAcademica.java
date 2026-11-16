package Model;

// Classe que representa uma atividade acadêmica
public class AtividadeAcademica {
    // Atributos da atividade
    private String tipo;
    private String descricao;

    // Construtor com as informações da atividade
    public AtividadeAcademica(String tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    // Métodos getters: utilizados para consultar os atributos
    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    // Métodos setters: utilizados para modificar os atributos
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}