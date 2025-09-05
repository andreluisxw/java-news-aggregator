package br.com.andre.news_aggregator.repository;

import br.com.andre.news_aggregator.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    // O Spring Data JPA vai criar a implementação deste método para nós.
    // Ele permitirá que a gente busque uma fonte pelo nome, o que será muito útil.
    Optional<Source> findByName(String name);

}
