package br.com.andre.news_aggregator.controller;

import br.com.andre.news_aggregator.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController: marca a classe como um Controller de API REST.
// Isso significa que os métodos aqui retornarão dados (JSON, XML, texto) diretamente no corpo da resposta.
@RestController
// @RequestMapping: define o caminho base para todos os endpoints neste controller.
// Todos os endereços começarão com /api/news
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    // Injeção de dependência do nosso NewsService. O Spring vai nos fornecer a instância dele.
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // @GetMapping: Mapeia este método para requisições HTTP GET.
    // O caminho será "/fetch", resultando na URL completa: /api/news/fetch
    @GetMapping("/fetch")
    public String fetchNews() {
        // Chama o método principal do nosso serviço.
        newsService.fetchAndSaveNews();

        // Retorna uma mensagem simples de sucesso para o navegador.
        return "Notícias buscadas e salvas com sucesso!";
    }
}
