# CampusHub

Sistema desenvolvido em Java com o objetivo de centralizar serviços, informações e recursos do ambiente universitário, facilitando a comunicação, a organização e a experiência dos estudantes.

## Sobre o projeto

O **CampusHub** é um projeto acadêmico desenvolvido em grupo, que propõe uma plataforma de apoio à vida universitária. A aplicação reúne diferentes funcionalidades em um único ambiente, permitindo que estudantes acessem informações, participem de grupos, acompanhem eventos e explorem recursos relacionados ao campus.

O projeto está sendo desenvolvido como um protótipo funcional, com foco na aplicação de conceitos de programação orientada a objetos, arquitetura MVC e desenvolvimento de interfaces gráficas. Sua estrutura busca permitir a inclusão de novas funcionalidades e a integração de outros serviços futuramente.

## Objetivos

- Centralizar informações e serviços relacionados à vida universitária.
- Facilitar a organização acadêmica e pessoal dos estudantes.
- Incentivar a comunicação e a interação entre estudantes.
- Oferecer acesso a informações sobre eventos, grupos e atividades universitárias.
- Desenvolver uma estrutura organizada e expansível, preparada para futuras implementações.

## Funcionalidades

### Funcionalidades implementadas ou em desenvolvimento

- **Autenticação e cadastro:** criação de contas e acesso à plataforma.
- **Perfil do estudante:** apresentação de informações pessoais e acadêmicas.
- **Eventos:** consulta e organização de eventos universitários.
- **Grupos:** criação, visualização e participação em grupos.
- **Mapa do campus:** visualização do mapa do Campus Samambaia da UFG, com pontos de interesse.
- **Missões:** sistema demonstrativo de missões associadas aos pontos de interesse do campus, com registro de conclusão e pontuação.

### Módulos em desenvolvimento

- **Acadêmico:** área destinada a recursos como mural, monitorias, agenda e materiais.
- **Atlética:** espaço dedicado a modalidades esportivas, equipes, campeonatos e notícias.
- **Notificações:** centralização de informações relevantes para o estudante, incluindo compromissos, novidades e atividades dos grupos e da Atlética.
- **Lembretes:** organização de compromissos e lembretes pessoais.

> Algumas áreas já possuem interfaces de navegação, mas ainda não contam com todas as funcionalidades previstas. O desenvolvimento ocorre de forma incremental.

## Tecnologias utilizadas

- **Java 21:** linguagem de programação.
- **JavaFX 23.0.2:** desenvolvimento das interfaces gráficas.
- **FXML:** estruturação das interfaces.
- **Scene Builder:** auxílio na construção visual das telas.
- **Git:** controle de versão.
- **GitHub:** hospedagem e colaboração no desenvolvimento.
- **BlueJ:** ambiente de desenvolvimento utilizado no projeto.

## Organização do projeto

O CampusHub utiliza o padrão arquitetural **MVC (Model-View-Controller)**, buscando separar a representação dos dados, as regras de negócio e as interfaces gráficas.

A organização geral contempla:

- **Model:** classes que representam as entidades e os dados do sistema.
- **Controller:** classes responsáveis pelas operações, regras de negócio e comunicação entre as camadas.
- **View:** interfaces gráficas desenvolvidas com JavaFX e FXML.

O projeto utiliza estruturas de dados em memória durante a fase de prototipagem. A separação de responsabilidades busca facilitar futuras mudanças, como a adoção de persistência em banco de dados e a expansão dos módulos existentes.

## Desenvolvimento e expansão

O desenvolvimento é realizado de maneira incremental, priorizando a construção de uma base funcional e organizada.

Entre as possibilidades de expansão estão:

- Implementação de persistência em banco de dados.
- Ampliação dos recursos acadêmicos.
- Integração entre agenda, lembretes e notificações.
- Expansão das funcionalidades esportivas e da Atlética.
- Inclusão de outros campi e instituições.
- Aprimoramento dos recursos de comunicação e interação entre estudantes.

## Equipe

Projeto desenvolvido em grupo como atividade acadêmica.

## Status

**Em desenvolvimento.**

O CampusHub encontra-se em fase de prototipagem e implementação progressiva de seus módulos. Algumas funcionalidades já estão disponíveis, enquanto outras possuem interfaces demonstrativas ou permanecem planejadas para etapas futuras.
