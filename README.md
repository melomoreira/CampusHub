# CampusHub

<p align="center">

**Um ecossistema digital para conectar a vida universitária.**

Um protótipo acadêmico desenvolvido em Java e JavaFX que simula a experiência visual e funcional de um aplicativo mobile, reunindo recursos, informações e ferramentas do ambiente universitário.

</p>

---

## Sobre o projeto

O **CampusHub** é um projeto acadêmico desenvolvido em Java, cuja proposta é centralizar serviços, informações, ferramentas e recursos do ambiente universitário em uma única plataforma.

A ideia é construir um ecossistema digital no qual estudantes possam acessar funcionalidades acadêmicas, acompanhar eventos, participar de grupos, compartilhar informações e interagir com outros integrantes da comunidade universitária.

Atualmente, essas informações costumam estar distribuídas entre diferentes plataformas, aplicativos, sites e canais de comunicação. O CampusHub busca reduzir essa fragmentação, oferecendo um ponto central de acesso aos diferentes aspectos da vida universitária.

Seu público-alvo principal são estudantes universitários, com possibilidade de expansão futura para outros integrantes da comunidade acadêmica.

---

## Objetivos

- Centralizar informações e recursos relacionados à vida universitária.
- Facilitar o acesso a eventos e atividades acadêmicas.
- Promover a interação entre estudantes por meio de grupos e publicações.
- Organizar funcionalidades acadêmicas em uma plataforma modular.
- Aplicar conceitos de programação orientada a objetos e desenvolvimento de software.
- Desenvolver uma base que permita futuras expansões e adaptações para dispositivos móveis.

---

## O CampusHub como protótipo de aplicativo mobile

> **Importante: o CampusHub atual é um protótipo funcional desenvolvido em JavaFX que simula a experiência de uso de um aplicativo de celular. Ele não é um aplicativo mobile nativo publicado ou executado diretamente em Android ou iOS.**

Embora tenha sido desenvolvido utilizando Java e JavaFX, o projeto foi concebido visualmente para reproduzir a experiência de navegação de um aplicativo mobile.

A janela principal utiliza dimensões próximas às de uma tela de smartphone, com referência aproximada de **390 × 844 pixels**. Sua interface incorpora elementos comuns em aplicativos móveis:

- Navegação inferior entre seções.
- Cartões e componentes organizados verticalmente.
- Botões de acesso rápido.
- Telas individuais para diferentes funcionalidades.
- Componentes interativos e carrosséis.
- Organização visual adaptada à proporção de uma tela de celular.
- Navegação intuitiva inspirada na interação por toque.

Essa abordagem permite prototipar e validar a organização das telas, a experiência de navegação e os principais fluxos de utilização antes de uma eventual implementação mobile nativa.

### Possível adaptação para Android e iOS

Uma futura versão mobile poderia ser desenvolvida a partir de tecnologias próprias para dispositivos móveis, como:

- **Android nativo:** Kotlin e Jetpack Compose.
- **Flutter:** desenvolvimento multiplataforma.
- **React Native:** desenvolvimento de interfaces móveis com tecnologias web.

Essa adaptação envolveria:

1. **Recriação da interface:** reconstrução das telas JavaFX utilizando componentes e padrões próprios das plataformas móveis.
2. **Preservação das regras de negócio:** aproveitamento dos modelos de dados e das regras implementadas, quando compatíveis com a nova arquitetura.
3. **Separação entre interface e dados:** manutenção de uma arquitetura organizada, evitando dependência excessiva entre a lógica do sistema e a camada visual.
4. **Persistência e sincronização:** possibilidade de manter armazenamento local e desenvolver futuramente um backend com API e banco de dados centralizado.
5. **Adaptação da experiência de uso:** adequação a diferentes resoluções, gestos, teclados virtuais, orientação de tela e recursos de acessibilidade.

Essas possibilidades representam caminhos de evolução do projeto, e não funcionalidades já concluídas.

---

## Funcionalidades e módulos

O CampusHub foi estruturado de maneira modular, contemplando diferentes áreas da experiência universitária.

### 1. Tela inicial

A tela inicial funciona como painel de entrada da aplicação, reunindo atalhos e informações de diferentes módulos.

Entre seus elementos estão:

- Saudação ao usuário.
- Carrossel de destaques e atalhos.
- Acesso rápido a diferentes seções.
- Prévia dos próximos eventos.
- Navegação inferior.

O carrossel apresenta categorias como Agenda, Notícias, Grupos, Mensagens e Atlética. Parte dessas informações possui caráter demonstrativo e não representa necessariamente módulos totalmente implementados.

### 2. Mural acadêmico

O Mural permite compartilhar informações e conteúdos com a comunidade universitária.

**Funcionalidades implementadas:**

- Criar publicações.
- Visualizar publicações.
- Editar as próprias publicações.
- Excluir as próprias publicações.
- Identificar o autor de cada publicação.
- Organizar publicações por data, exibindo as mais recentes primeiro.

As publicações armazenam informações como autor, título, conteúdo e datas de criação e atualização.

### 3. Eventos

O módulo de Eventos permite organizar e acompanhar atividades universitárias.

**Funcionalidades implementadas:**

- Criar eventos.
- Listar eventos.
- Editar eventos.
- Excluir eventos.
- Informar título, data, horário, local e descrição.
- Identificar o organizador.
- Participar de eventos.
- Sair de eventos.
- Associar participantes aos respectivos eventos.

### 4. Grupos universitários

O módulo de Grupos permite reunir estudantes em comunidades organizadas por interesses e temas.

Exemplos de categorias iniciais:

- Tecnologia e Programação.
- Arte e Cultura.
- Ciência e Pesquisa.
- Jogos e Cultura Geek.

**Funcionalidades implementadas:**

- Criar grupos.
- Listar grupos disponíveis.
- Consultar grupos.
- Editar informações.
- Excluir grupos.
- Participar de grupos.
- Sair de grupos.
- Identificar o criador e os participantes.

A estrutura permite que estudantes encontrem comunidades relacionadas aos seus interesses acadêmicos e pessoais.

**Em desenvolvimento:** a funcionalidade de mensagens internas dos grupos ainda não foi implementada. A interface apresenta um espaço demonstrativo para essa possibilidade futura.

### 5. Perfil e autenticação

O sistema possui funcionalidades de cadastro, autenticação e gerenciamento de informações dos usuários.

**Funcionalidades implementadas:**

- Cadastro de usuários.
- Login.
- Armazenamento persistente dos dados.
- Atualização das informações do perfil.
- Registro de nome, e-mail, universidade e curso.
- Possibilidade de associar uma foto de perfil.
- Armazenamento de senhas utilizando hash e salt.

Também existe uma estrutura para armazenar interesses associados aos usuários.

### 6. Área acadêmica

A área acadêmica reúne recursos relacionados à organização e ao compartilhamento de informações de estudo.

| Módulo | Situação |
|---|---|
| Mural | Funcionalidades de publicação implementadas |
| Monitorias | Em desenvolvimento ou demonstrativo |
| Materiais | Em desenvolvimento ou demonstrativo |
| Agenda | Módulo de eventos funcional; agenda acadêmica completa ainda não concluída |

É importante destacar que a existência de uma tela ou opção de navegação não significa que todas as funcionalidades previstas para aquele módulo estejam implementadas.

### 7. Outros módulos planejados

A proposta de expansão do CampusHub contempla:

- **Lembretes:** avisos pessoais sobre provas, trabalhos, prazos e compromissos.
- **Atlética:** informações e atividades relacionadas às associações atléticas universitárias.
- **Notificações:** avisos sobre eventos, publicações e interações.
- **Mensagens:** comunicação entre usuários.
- **Mapa universitário:** localização de prédios, salas, serviços e pontos de interesse no campus.

Esses módulos representam possibilidades de expansão e não devem ser considerados funcionalidades integralmente concluídas.

---

## Tecnologias utilizadas

| Tecnologia | Aplicação no projeto |
|---|---|
| Java 21 | Linguagem principal |
| JavaFX 23.0.2 | Construção da interface gráfica |
| FXML | Estruturação das interfaces |
| Scene Builder | Apoio à construção visual das telas |
| SQLite | Banco de dados relacional local |
| JDBC | Comunicação entre Java e banco de dados |
| Xerial SQLite JDBC | Driver de conexão com SQLite |
| Git | Controle de versão |
| GitHub | Hospedagem e colaboração |
| BlueJ | Ambiente utilizado no desenvolvimento e execução |

**Observação:** o projeto não utiliza Maven. A dependência JDBC do SQLite é disponibilizada por meio de um arquivo JAR na pasta de bibliotecas do projeto.

---

## Arquitetura do sistema

O CampusHub utiliza uma organização inspirada no padrão arquitetural **MVC (Model-View-Controller)**.

A separação de responsabilidades organiza o código em três partes principais:

### Model

Contém as classes que representam as entidades e os dados do sistema.

Exemplos:

- Usuário.
- Evento.
- Grupo.
- Publicação.

### View

Contém as interfaces gráficas e as telas desenvolvidas com JavaFX e FXML.

É responsável pela apresentação das informações e pela interação visual com o usuário.

### Controller

Concentra a lógica de controle e coordena a comunicação entre as interfaces e os dados.

Também existem classes DAO (*Data Access Objects*), responsáveis por organizar as operações de acesso e persistência no banco de dados.

Essa organização busca facilitar a manutenção, a compreensão e a expansão do sistema, reduzindo o acoplamento entre suas diferentes partes.

---

## Banco de dados e persistência

O CampusHub utiliza **SQLite** para armazenar informações localmente de maneira persistente.

Entre as principais entidades e estruturas estão:

- Usuários.
- Interesses dos usuários.
- Publicações.
- Eventos.
- Participantes de eventos.
- Grupos.
- Membros dos grupos.

O sistema utiliza relacionamentos e chaves estrangeiras para contribuir com a integridade dos dados.

A persistência permite que informações cadastradas permaneçam disponíveis mesmo após o encerramento e a reabertura da aplicação.

As operações de acesso ao banco são organizadas por meio de classes DAO, utilizando JDBC para a comunicação com o SQLite.

---

## Estrutura do projeto

A organização geral segue a separação entre Model, Controller e View, além das classes de acesso a dados.

```text
CampusHub/
│
├── Model/
│   └── Classes que representam as entidades
│
├── Controller/
│   └── Classes responsáveis pela lógica de controle
│
├── View/
│   └── Interfaces gráficas e arquivos FXML
│
├── Bibliotecas/
│   └── Dependências utilizadas pelo projeto
│
└── README.md
```

*Representação conceitual da organização. Os nomes e caminhos exatos das pastas devem ser conferidos na estrutura atual do repositório.*

---

## Como executar o projeto

O projeto utiliza Java 21, JavaFX 23.0.2 e o driver JDBC do SQLite.

### Pré-requisitos

- Java Development Kit (JDK) 21.
- JavaFX 23.0.2.
- Driver Xerial SQLite JDBC disponibilizado na pasta de bibliotecas.
- Ambiente de desenvolvimento compatível, como o BlueJ.

### Execução

O projeto foi desenvolvido e executado utilizando o BlueJ.

**Instruções específicas de abertura e execução:** preencher com os caminhos e procedimentos confirmados na versão atual do repositório.

> Os comandos de execução e os caminhos das bibliotecas dependem da configuração efetiva do projeto. Por isso, não são apresentados comandos genéricos que possam não funcionar no ambiente configurado.

---

## Estado atual do projeto

O CampusHub encontra-se em desenvolvimento. A tabela abaixo diferencia as funcionalidades implementadas das que ainda possuem caráter parcial, demonstrativo ou planejado.

### Implementado

- Cadastro e login.
- Persistência dos usuários.
- Atualização de perfil.
- Mural com operações de criação, consulta, edição e exclusão.
- Eventos com operações de criação, consulta, edição e exclusão.
- Participação e saída de eventos.
- Grupos com operações de criação, consulta, edição e exclusão.
- Participação e saída de grupos.
- Persistência dos dados no SQLite.
- Interface JavaFX com proposta visual inspirada em aplicativos mobile.

### Parcial, demonstrativo ou em desenvolvimento

- Mensagens internas dos grupos.
- Monitorias.
- Materiais acadêmicos.
- Agenda acadêmica completa.
- Notícias e conteúdos dinâmicos.
- Notificações.
- Lembretes.
- Atlética.
- Mapa universitário.

A presença de elementos visuais relacionados a esses módulos não significa que suas funcionalidades estejam integralmente operacionais.

---

## Objetivos acadêmicos

Além de sua proposta como ecossistema universitário, o CampusHub é um projeto acadêmico voltado à aplicação prática de conceitos de desenvolvimento de software.

Seu desenvolvimento envolve:

- Programação Orientada a Objetos.
- Organização modular.
- Arquitetura MVC.
- Interfaces gráficas.
- Banco de dados relacionais.
- Persistência de dados.
- Operações CRUD.
- Controle de versão.
- Colaboração em equipe.
- Documentação técnica.

O GitHub é utilizado para organizar o código-fonte, acompanhar alterações, registrar commits e apoiar o desenvolvimento colaborativo.

---

## Evolução futura

O CampusHub possui uma proposta de expansão que pode envolver:

- Implementação de mensagens e notificações.
- Desenvolvimento dos módulos acadêmicos ainda demonstrativos.
- Ampliação das funcionalidades de agenda e lembretes.
- Expansão das funcionalidades relacionadas ao campus e à atlética.
- Desenvolvimento de um backend com API.
- Sincronização de informações entre diferentes dispositivos.
- Adaptação da interface para Android e iOS.

Essas possibilidades representam a visão de evolução do projeto e dependem de etapas futuras de desenvolvimento.

---

## Considerações finais

O CampusHub representa uma proposta de centralização da experiência universitária em um único ambiente digital.

Desenvolvido atualmente em Java e JavaFX, o projeto utiliza uma interface que simula a experiência visual e funcional de um aplicativo mobile, permitindo prototipar e validar sua proposta sem depender, nesta etapa, de uma implementação nativa para Android ou iOS.

Com funcionalidades reais já implementadas, persistência local em SQLite e uma organização modular inspirada no padrão MVC, o CampusHub estabelece uma base para futuras expansões.

**Mais do que uma aplicação acadêmica, o CampusHub é a prototipação de um ecossistema digital universitário: um espaço central de conexão entre estudantes, informações, atividades e oportunidades.**

---

<p align="center">

**CampusHub — Conectando a vida universitária.**

Projeto acadêmico em desenvolvimento.

</p>
