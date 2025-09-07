package br.com.andre.news_aggregator.dto;

public class SourceResponseDTO {
    private String name;

    public SourceResponseDTO(String name) {
        this.name = name;
    }

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}