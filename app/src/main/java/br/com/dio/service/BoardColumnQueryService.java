package br.com.dio.service;

import br.com.dio.persistence.dao.BoardColumnDAO;
import br.com.dio.persistence.entity.BoardColumnEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor // Gera um construtor com todos os atributos (via Lombok).
public class BoardColumnQueryService {

    private final Connection connection; // Conexão com o banco de dados.

    // Método para buscar uma coluna (BoardColumnEntity) pelo ID.
    public Optional<BoardColumnEntity> findById(final Long id) throws SQLException {
        var dao = new BoardColumnDAO(connection); // Instancia o DAO para acessar o banco.
        return dao.findById(id); // Retorna um Optional com a coluna encontrada (ou vazio).
    }
}
