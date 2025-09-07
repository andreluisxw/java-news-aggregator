package br.com.andre.news_aggregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sources")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Garante que o nome da fonte seja único no banco de dados.
    @Column(unique = true, nullable = false)
    private String name;

    // Relacionamento Inverso: Uma Fonte (One) pode ter Muitos Artigos (Many).
    // mappedBy = "source": Indica que o lado "dono" do relacionamento está na entidade Article, no campo "source".
    // cascade = CascadeType.ALL: Operações (salvar, deletar) na Source serão propagadas para os Articles associados.
    // orphanRemoval = true: Se um Article for removido da lista de uma Source, ele deve ser deletado do banco.
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();


    // Construtor vazio para o JPA
    public Source() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
