package br.com.andre.news_aggregator.service;

import br.com.andre.news_aggregator.dto.ArticleDTO;
import br.com.andre.news_aggregator.dto.NewsApiResponseDTO;
import br.com.andre.news_aggregator.model.Article;
import br.com.andre.news_aggregator.model.Source;
import br.com.andre.news_aggregator.repository.ArticleRepository;
import br.com.andre.news_aggregator.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NewsService {

    private final RestTemplate restTemplate;
    private final ArticleRepository articleRepository;
    private final SourceRepository sourceRepository;

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.url}")
    private String apiUrl;

    // Injeção de dependências via construtor (melhor prática)
    public NewsService(RestTemplate restTemplate, ArticleRepository articleRepository, SourceRepository sourceRepository) {
        this.restTemplate = restTemplate;
        this.articleRepository = articleRepository;
        this.sourceRepository = sourceRepository;
    }

    public void fetchAndSaveNews() {
        // Monta a URL para buscar as principais notícias de tecnologia do Brasil
        String url = apiUrl + "?country=us&apiKey=" + apiKey;

        // 1. Criamos os "cabeçalhos" da nossa requisição.
        HttpHeaders headers = new HttpHeaders();
        // 2. nos identificamos como um navegador comum para evitar bloqueios.
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        // 3. Empacotamos os cabeçalhos em uma "entidade de requisição".
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 4. Usamos exchange() para fazer a requisição GET com os cabeçalhos customizados.
        ResponseEntity<NewsApiResponseDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, NewsApiResponseDTO.class);

        NewsApiResponseDTO response = responseEntity.getBody();

        if (response != null && response.getArticles() != null) {
            List<ArticleDTO> articlesFromApi = response.getArticles();

            System.out.println(">>> [INFO] Artigos recebidos da API: " + articlesFromApi.size());

            for (ArticleDTO articleDto : articlesFromApi) {
                // Validação para pular artigos com dados essenciais faltando
                if (articleDto.getUrl() == null || articleDto.getSource() == null || articleDto.getSource().getName() == null || articleDto.getTitle() == null) {
                    continue;
                }

                // Lógica para encontrar ou criar a Fonte (Source)
                String sourceName = articleDto.getSource().getName();
                Source source = sourceRepository.findByName(sourceName).orElseGet(() -> {
                    Source newSource = new Source();
                    newSource.setName(sourceName);
                    return sourceRepository.save(newSource);
                });

                // Cria a nossa entidade Article a partir do DTO
                Article article = new Article();
                article.setTitle(articleDto.getTitle());
                article.setDescription(articleDto.getDescription());
                article.setUrl(articleDto.getUrl());
                article.setUrlToImage(articleDto.getUrlToImage());
                article.setPublishedAt(articleDto.getPublishedAt());
                article.setContent(articleDto.getContent());
                article.setSource(source);

                try {
                    // Tenta salvar o artigo. A constraint UNIQUE no campo 'url' do banco de dados
                    // vai prevenir a inserção de duplicatas.
                    articleRepository.save(article);
                } catch (DataIntegrityViolationException e) {
                    // Apenas informamos no console que o artigo já existe e continuamos.
                    System.out.println("Artigo já existe no banco: " + article.getUrl());
                }
            }
        }
    }
}