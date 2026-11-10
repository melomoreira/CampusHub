# 2. Planejamento e escopo

## Objetivos específicos
- Permitir registrar, consultar, editar e excluir perfis de estudantes.
- Gerenciar eventos, incluindo data, horário, local e descrição.
- Criar, consultar, editar e excluir comunidades relacionadas a cursos, turmas e interesses.
- Gerenciar compromissos acadêmicos, como aulas, provas e trabalhos.
- Cadastrar, consultar, editar e excluir informações de atléticas.
- Aplicar conceitos de Programação Orientada a Objetos em Java.

## Módulos

| Módulo | Entidade | Informações previstas |
|---|---|---|
| Estudantes | `Usuario` | Nome, universidade, curso e interesses |
| Eventos | `Evento` | Data, horário, local e descrição |
| Comunidades | `Grupo` | Grupos relacionados a cursos, turmas e interesses |
| Agenda | `AtividadeAcademica` | Aulas, provas e trabalhos |
| Atléticas | `Atletica` | Informações básicas |

## Fora do escopo inicial
Chat em tempo real, rankings esportivos, gamificação avançada e integração entre universidades ficam como possibilidades futuras.

## Tecnologias
Java, JavaFX, FXML, Git e GitHub. Collections, especialmente `ArrayList`, poderão organizar registros em memória.

## Decisões pendentes
O documento inicial não especifica todos os atributos de `Grupo`, `AtividadeAcademica` e `Atletica`, nem define relacionamentos entre as entidades. A equipe deve validar esses detalhes antes da implementação.
