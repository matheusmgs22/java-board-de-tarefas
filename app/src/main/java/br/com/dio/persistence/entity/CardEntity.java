package br.com.dio.persistence.entity;

import lombok.Data;

@Data
public class CardEntity {

    private Long id;
    private String title; // Título do card
    private String description; // Descricao detalhada da tarefa
    private BoardColumnEntity boardColumn = new BoardColumnEntity();

}
