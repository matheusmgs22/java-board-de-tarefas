package br.com.dio.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardColumnEntity {

    private Long id;
    private String name;
    private int order;
    private BoardColumnKindEnum kind; // Tipo da coluna (definido por enum)
    private BoardEntity board = new BoardEntity(); // Associacao com o board ao qual pertence

    @ToString.Exclude // Evita inclusao da lista no metodo toString para prevenir loops infinitos
    @EqualsAndHashCode.Exclude // Exclui a lista do equals e hashCode para evitar comparação recursiva
    private List<CardEntity> cards = new ArrayList<>(); // Lista de cards dentro da coluna

}
