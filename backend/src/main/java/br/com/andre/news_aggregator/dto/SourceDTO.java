package br.com.andre.news_aggregator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceDTO {
    private String name;

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
