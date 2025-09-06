package br.com.andre.news_aggregator.repository;

import br.com.andre.news_aggregator.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // O Spring Data JPA cria os métodos CRUD básicos automaticamente.
    // Podemos adicionar métodos de busca customizados aqui no futuro, se necessário.

    // O Spring Data JPA entende o nome do método e cria a query automaticamente:
    // "Encontre todos os artigos, ordenados pela data de publicação em ordem decrescente"
    List<Article> findAllByOrderByPublishedAtDesc();
}