## News Aggregator (Full-Stack)
News Aggregator é uma aplicação web full-stack completa que busca as notícias mais recentes de uma API externa, as processa e as exibe em uma interface limpa e moderna. O projeto foi desenvolvido utilizando uma arquitetura Monorepo, separando o backend (api) e o frontend (client) em módulos distintos, mas mantendo-os em uma base de código unificada.

##📜 Visão Geral do Projeto
O objetivo deste projeto é demonstrar a construção de uma aplicação web moderna de ponta a ponta, integrando um backend robusto e escalável com um frontend reativo e performático. O sistema lida com consumo de APIs de terceiros, persistência de dados em um banco relacional, exposição de uma API REST segura e a renderização de dados no lado do cliente.

##✨ Funcionalidades Principais
Backend (api):

Consumo de API Externa: Conecta-se à NewsAPI.org para buscar notícias em tempo real.

Persistência Inteligente: Salva artigos e suas fontes em um banco de dados PostgreSQL, evitando duplicatas.

Validação de Dados: Filtra e ignora artigos com dados incompletos ou inválidos antes da persistência.

API REST: Expõe endpoints para acionar a busca de notícias e para listar os artigos já salvos.

Segurança: Utiliza variáveis de ambiente para gerenciar credenciais sensíveis (chave de API e senha do banco).

Frontend (client):

Renderização no Servidor (SSR): Utiliza Next.js para buscar os dados do backend no servidor, garantindo carregamentos rápidos e bom SEO.

Interface Reativa: Construído com React para uma experiência de usuário fluida.

Design Moderno: Interface com tema escuro e layout em cartões, focada na legibilidade.

Consumo de API: Faz chamadas para a API do backend para buscar e exibir a lista de notícias.

##🏗️ Arquitetura do Sistema
O projeto utiliza uma Arquitetura em Camadas no backend para garantir a separação de responsabilidades. O frontend atua como um consumidor dessa API.

Fluxo de Dados:
Cliente (Navegador) ➔ Frontend (client) ➔ Backend (api - Controller) ➔ Backend (api - Service) ➔ Backend (api - Repository) ➔ Banco de Dados (PostgreSQL)

O Frontend (client) solicita os artigos.

O NewsController (backend) recebe a requisição.

O NewsService (backend) é acionado, contendo a lógica de negócio.

O NewsService usa o ArticleRepository (backend), que por sua vez busca os dados no PostgreSQL.

Os dados retornam como Entidades JPA, são convertidos para DTOs pelo Service, e enviados como JSON pelo Controller.

O Frontend recebe o JSON e renderiza os componentes visuais.

##🗄️ Modelo do Banco de Dados
O banco de dados foi projetado de forma relacional para garantir a integridade dos dados.

Tabela sources: Armazena as fontes das notícias de forma única (id, name).

Tabela articles: Armazena os artigos. Cada artigo possui uma chave estrangeira (source_id) que o conecta obrigatoriamente a uma fonte na tabela sources.
