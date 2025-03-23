package br.com.dio.persistence.entity;

import java.util.stream.Stream;

public enum BoardColumnKindEnum {

    INITIAL, // Coluna inicial
    FINAL, // Coluna final
    CANCEL, // Coluna para tarefas canceladas
    PENDING; // Coluna para tarefas pendentes

    public static BoardColumnKindEnum findByName(final String name) {
        return Stream.of(BoardColumnKindEnum.values()) // Converte os valores do enum em um stream
                .filter(b -> b.name().equals(name)) // Filtra pelo nome fornecido
                .findFirst() // Retorna o primeiro encontrado, se existir
                .orElseThrow(); // Lanca uma excecao se nao encontrar
    }

}
