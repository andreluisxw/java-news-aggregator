package br.com.andre.news_aggregator.repository;

import br.com.andre.news_aggregator.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // O Spring Data JPA cria os métodos CRUD básicos automaticamente.
    // Podemos adicionar métodos de busca customizados aqui no futuro, se necessário.
}