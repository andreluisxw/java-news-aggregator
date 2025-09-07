package br.com.andre.news_aggregator.dto;

import br.com.andre.news_aggregator.model.Article;
import java.time.LocalDateTime;

public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private LocalDateTime publishedAt;
    private String content;
    private SourceResponseDTO source;

    // Construtor que converte uma Entidade Article para este DTO
    public ArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.url = article.getUrl();
        this.urlToImage = article.getUrlToImage();
        this.publishedAt = article.getPublishedAt();
        this.content = article.getContent();
        this.source = new SourceResponseDTO(article.getSource().getName());
    }

    // Getters e Setters para todos os campos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getUrlToImage() { return urlToImage; }
    public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public SourceResponseDTO getSource() { return source; }
    public void setSource(SourceResponseDTO source) { this.source = source; }
}
