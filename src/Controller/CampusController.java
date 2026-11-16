package Controller;

// Bibliotecas
import Model.Campus;

import java.util.ArrayList;

// Classe CampusController
public class CampusController {
    // Lista de campi cadastrados
    private ArrayList<Campus> campi;

    // Instância única
    private static CampusController instancia;

    // Construtor privado
    private CampusController() {
        campi = new ArrayList<>();

        // Cadastro inicial da UFG - Campus Samambaia
        Campus campusUFG = new Campus(
            "ufg-samambaia",
            "Universidade Federal de Goiás",
            "Campus Samambaia",
            "Campus localizado em Goiânia - GO.",
            "/imagens/Mapa_Campus_Samambaia.png"
        );

        campi.add(campusUFG);
    }

    // Retorna a instância do controlador
    public static CampusController getInstancia() {
        if (instancia == null) {
            instancia = new CampusController();
        }

        return instancia;
    }

    // CREATE - Cadastrar campus
    public boolean cadastrarCampus(Campus campus) {
        if (campus == null) {
            return false;
        }

        if (buscarCampusPorId(campus.getId()) != null) {
            return false;
        }

        campi.add(campus);

        return true;
    }

    // READ - Listar todos os campi
    public ArrayList<Campus> listarCampi() {
        return new ArrayList<>(campi);
    }

    // READ - Buscar campus pelo ID
    public Campus buscarCampusPorId(String id) {
        if (id == null) {
            return null;
        }

        for (Campus campus : campi) {
            if (campus.getId().equals(id)) {
                return campus;
            }
        }

        return null;
    }

    // READ - Listar campi de uma universidade
    public ArrayList<Campus> listarCampiDaUniversidade(String universidade) {
        ArrayList<Campus> resultado = new ArrayList<>();

        if (universidade == null) {
            return resultado;
        }

        for (Campus campus : campi) {
            if (campus.getUniversidade().equalsIgnoreCase(universidade.trim())) {
                resultado.add(campus);
            }
        }

        return resultado;
    }

    // UPDATE - Atualizar campus
    public boolean atualizarCampus(String id, Campus novosDados) {
        Campus campus = buscarCampusPorId(id);

        if (campus == null || novosDados == null) {
            return false;
        }

        campus.setUniversidade(novosDados.getUniversidade());
        campus.setNome(novosDados.getNome());
        campus.setDescricao(novosDados.getDescricao());
        campus.setCaminhoImagem(novosDados.getCaminhoImagem());

        return true;
    }

    // DELETE - Excluir campus
    public boolean excluirCampus(String id) {
        Campus campus = buscarCampusPorId(id);

        if (campus != null) {
            campi.remove(campus);

            return true;
        }

        return false;
    }

    // READ - Listar universidades cadastradas no CampusHub
    public ArrayList<String> listarUniversidades() {
        return TelaLoginController.getUsuarioController().listarUniversidades();
    }
}