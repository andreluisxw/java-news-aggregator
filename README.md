# News Aggregator (Full-Stack) 🚀

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Next.js](https://img.shields.io/badge/Next.js-14.x-black.svg?logo=next.js&logoColor=white)](https://nextjs.org/)
[![React](https://img.shields.io/badge/React-18.x-61DAFB.svg?logo=react&logoColor=white)](https://react.dev/)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue.svg)](https://www.postgresql.org/)

**News Aggregator** é uma aplicação web full-stack completa que busca as notícias mais recentes de uma API externa, as processa e as exibe em uma interface limpa e moderna. O projeto foi desenvolvido utilizando uma arquitetura Monorepo, separando o backend (`api`) e o frontend (`client`) em módulos distintos.

---

### 📜 Sobre o Projeto

O objetivo deste projeto é demonstrar a construção de uma aplicação web moderna de ponta a ponta, integrando um backend robusto com um frontend reativo. O sistema lida com consumo de APIs de terceiros, persistência de dados em um banco relacional, exposição de uma API REST e a renderização de dados tanto no lado do servidor (SSR) quanto no lado do cliente (CSR) para interatividade.

---

### ✨ Funcionalidades

* **Backend (`api`):**
    * **Consumo de API Externa:** Conecta-se à [NewsAPI.org](https://newsapi.org/) para buscar notícias em tempo real.
    * **Persistência Inteligente:** Salva artigos e suas fontes em um banco de dados PostgreSQL, evitando duplicatas.
    * **Validação de Dados:** Filtra e ignora artigos com dados incompletos ou inválidos antes da persistência.
    * **API REST:** Expõe endpoints para acionar a busca de notícias, para listar todos os artigos e para pesquisar artigos por palavra-chave.
    * **Segurança:** Utiliza variáveis de ambiente para gerenciar credenciais sensíveis.

* **Frontend (`client`):**
    * **Renderização Híbrida:** Utiliza Next.js para carregar a lista inicial de notícias no servidor (SSR) e para buscar resultados de pesquisa no cliente (CSR).
    * **Interface Reativa:** Construído com React e o hook `useState` para gerenciar o estado da busca e exibir os resultados dinamicamente.
    * **Busca Funcional:** Permite que o usuário pesquise em tempo real por artigos que contenham uma palavra-chave no título.
    * **Design Moderno:** Interface com tema escuro e layout em cartões, focada na legibilidade.

---

### 🛠️ Tecnologias Utilizadas

| Área         | Tecnologia                                    |
|--------------|-----------------------------------------------|
| **Backend** | Java 17, Spring Boot, Spring Data JPA, Hibernate, Maven |
| **Frontend** | Next.js, React, JavaScript, CSS Modules, Node.js |
| **Banco de Dados**| PostgreSQL                                    |
| **Arquitetura** | Monorepo, API REST, Arquitetura em Camadas    |

---

### 🏗️ Arquitetura do Sistema

O projeto utiliza uma **Arquitetura em Camadas** no backend e uma arquitetura baseada em componentes no frontend.

#### Fluxo de Dados (Busca):
`Usuário digita no Input` ➔ `React (useState)` ➔ `Evento de Submit` ➔ `Fetch para a API de Busca` ➔ `Controller de Busca` ➔ `Service de Busca` ➔ `Repository de Busca` ➔ `Banco de Dados` ➔ `Retorno do JSON` ➔ `Atualização do Estado no React` ➔ `Renderização dos Resultados`

#### Detalhamento das Camadas

| Camada | Responsabilidade Principal | Exemplo no Projeto | Interação |
| :--- | :--- | :--- | :--- |
| **Controller** <br> (Presentation Layer) | Ponto de entrada da API. Gerencia endpoints HTTP e delega a lógica de negócio. | `NewsController.java` | Recebe requisições do Cliente, chama o `Service`, e retorna o `DTO` como JSON. |
| **Service** <br> (Business Logic Layer)| Núcleo da aplicação. Orquestra a lógica de negócio e gerencia transações. | `NewsService.java` | É chamado pelo `Controller`. Usa os `Repositories` para acessar dados e mapeia `Entidades` para `DTOs`. |
| **Repository** <br> (Data Access Layer) | Abstrai o acesso ao banco de dados. Fornece métodos para operações CRUD. | `ArticleRepository.java` | É chamado pelo `Service`. O Spring Data JPA implementa a interface para executar queries SQL. |
| **Model/Entity** <br> (Domain Layer) | Representa as tabelas do banco de dados e as estruturas de dados do domínio. | `Article.java`, `Source.java` | Usado por todas as camadas para transportar dados internamente. É o objeto persistido pelo `Repository`. |
| **DTO** <br> (Data Transfer Object) | Contrato de dados da API. Desacopla a representação interna da externa. | `ArticleResponseDTO.java`| Criado pelo `Service` e retornado pelo `Controller` para garantir uma resposta segura e estável. |

---

### 🗄️ Modelo do Banco de Dados

* **Tabela `sources`**: Armazena as fontes das notícias (`id`, `name`) de forma única.
* **Tabela `articles`**: Armazena os artigos (`id`, `title`, etc.) e se relaciona com `sources` através de uma chave estrangeira (`source_id`).
