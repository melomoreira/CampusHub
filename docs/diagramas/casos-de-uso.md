# Diagrama de casos de uso

O ator Estudante representa o público-alvo principal indicado no documento inicial. Cada caso de uso de gerenciamento contempla cadastrar, consultar, editar e excluir.

```mermaid
flowchart LR
    E[Estudante]
    subgraph CH[CampusHub]
      U((Gerenciar estudantes))
      V((Gerenciar eventos))
      G((Gerenciar comunidades))
      A((Gerenciar agenda acadêmica))
      T((Gerenciar atléticas))
    end
    E --- U
    E --- V
    E --- G
    E --- A
    E --- T
```

Este é um diagrama simplificado das funcionalidades. Os quatro comportamentos CRUD podem ser detalhados individualmente em uma versão posterior.
