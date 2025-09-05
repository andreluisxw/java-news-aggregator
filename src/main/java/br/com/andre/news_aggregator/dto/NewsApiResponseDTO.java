package br.com.andre.news_aggregator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// Anotação para ignorar campos do JSON que não mapeamos na nossa classe. Muito útil!
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponseDTO {
    private String status;
    private int totalResults;
    private List<ArticleDTO> articles;

    // Getters e Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getTotalResults() { return totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }
    public List<ArticleDTO> getArticles() { return articles; }
    public void setArticles(List<ArticleDTO> articles) { this.articles = articles; }
}
