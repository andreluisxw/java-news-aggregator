package br.com.andre.news_aggregator.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity: marca esta classe como uma entidade do JPA, ou seja, ela representa uma tabela no banco.
@Entity
// @Table: Especifica o nome da tabela no banco de dados. É uma boa prática usar nomes no plural.
@Table(name = "articles")
public class Article {

    // @Id: marca este campo como a chave primária da tabela.
    @Id
    // @GeneratedValue: configura como a chave primária será gerada.
    // GenerationType.IDENTITY é a melhor opção para PostgreSQL, deixando o banco controlar o auto-incremento.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceName;

    private String title;

    // @Column(length = 1000): Especifica que esta coluna no banco pode ter até 1000 caracteres.
    // Útil para campos de texto que podem ser um pouco mais longos.
    @Column(length = 1000)
    private String description;

    // Usamos @Column(unique = true) para garantir que não salvaremos o mesmo artigo duas vezes.
    @Column(unique = true)
    private String url;

    private String urlToImage;

    private LocalDateTime publishedAt;

    // @Lob ou @Column(columnDefinition = "TEXT"): Indica que este campo pode conter um texto muito longo.
    @Column(columnDefinition = "TEXT")
    private String content;

    // Construtor vazio é exigido pelo JPA
    public Article() {
    }

    // Getters
    // Você pode gerar automaticamente na sua IDE (Alt+Insert no IntelliJ > Getter and Setter).

    public Long getId() {
        return id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }
}