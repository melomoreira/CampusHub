package Controller;

// Bibliotecas
import Model.Evento;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

// Classe EventoController
public class EventoController {
    // Atributos
    private static final EventoController instancia = new EventoController();

    private ArrayList<Evento> eventos;

    // Construtor
    private EventoController() {
        eventos = new ArrayList<>();
    }

    // Getter
    public static EventoController getInstancia() {
        return instancia;
    }

    // CREATE: cadastrar evento
    public boolean cadastrarEvento(Evento evento) {
        if (evento == null) {
            return false;
        }

        eventos.add(evento);
        return true;
    }

    // READ: listar todos os eventos
    public ArrayList<Evento> listarEventos() {
        return eventos;
    }

    // READ: buscar evento pelo ID
    public Evento buscarEventoPorId(String id) {
        if (id == null) {
            return null;
        }

        for (Evento evento : eventos) {
            if (evento.getId().equals(id)) {
                return evento;
            }
        }

        return null;
    }

    // UPDATE: atualizar evento
    public boolean atualizarEvento(String id, Evento novosDados) {
        Evento evento = buscarEventoPorId(id);

        if (evento == null || novosDados == null) {
            return false;
        }

        evento.setTitulo(novosDados.getTitulo());
        evento.setData(novosDados.getData());
        evento.setHorario(novosDados.getHorario());
        evento.setLocal(novosDados.getLocal());
        evento.setDescricao(novosDados.getDescricao());

        return true;
    }

    // DELETE: excluir evento
    public boolean excluirEvento(String id) {
        Evento evento = buscarEventoPorId(id);

        if (evento == null) {
            return false;
        }

        eventos.remove(evento);
        return true;
    }

    // Inscrever usuário em evento
    public boolean participarEvento(String id, Usuario usuario) {
        Evento evento = buscarEventoPorId(id);

        if (evento == null || usuario == null) {
            return false;
        }

        if (evento.getParticipantes().contains(usuario)) {
            return false;
        }

        evento.adicionarParticipante(usuario);
        return true;
    }

    // Remover inscrição
    public boolean sairEvento(String id, Usuario usuario) {
        Evento evento = buscarEventoPorId(id);

        if (evento == null || usuario == null) {
            return false;
        }

        if (!evento.getParticipantes().contains(usuario)) {
            return false;
        }

        evento.removerParticipante(usuario);
        return true;
    }
}