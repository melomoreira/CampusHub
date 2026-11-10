# Diagrama de sequência — cadastro de evento

Exemplo proposto de uma operação CRUD, usando a separação View, Controller e Model e uma coleção em memória.

```mermaid
sequenceDiagram
    actor Estudante
    participant View as Tela de Eventos
    participant Controller as EventoController
    participant Model as Evento
    participant Lista as Coleção de Eventos

    Estudante->>View: Preenche dados e seleciona Cadastrar
    View->>Controller: cadastrarEvento(dados)
    Controller->>Controller: validar dados
    alt Dados válidos
        Controller->>Model: criar Evento(dados)
        Model-->>Controller: evento criado
        Controller->>Lista: add(evento)
        Lista-->>Controller: confirmação
        Controller-->>View: informar sucesso
        View-->>Estudante: Exibir evento cadastrado
    else Dados inválidos
        Controller-->>View: informar erros
        View-->>Estudante: Exibir mensagens de validação
    end
```

O uso de uma coleção como `ArrayList<Evento>` é uma proposta coerente com a referência técnica do projeto. O diagrama não representa persistência em banco de dados.
