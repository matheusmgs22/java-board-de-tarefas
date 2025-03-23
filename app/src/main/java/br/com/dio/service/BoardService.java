package br.com.dio.service;

import br.com.dio.persistence.dao.BoardColumnDAO;
import br.com.dio.persistence.dao.BoardDAO;
import br.com.dio.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class BoardService {

    private final Connection connection;

    // Insere um board e suas colunas no banco de dados
    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);

        try {
            dao.insert(entity); // Insere o board no banco

            // Associa as colunas ao board e as insere no banco
            var columns = entity.getBoardColumns().stream().map(c -> {
                c.setBoard(entity); // Define o board como pai da coluna
                return c;
            }).toList();

            for (var column : columns) {
                boardColumnDAO.insert(column); // Insere cada coluna no banco
            }

            connection.commit(); // Confirma a transação
        } catch (SQLException e) {
            connection.rollback(); // Desfaz a transação em caso de erro
            throw e;
        }
        return entity; // Retorna o board inserido
    }

    // Deleta um board pelo ID.
    public boolean delete(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);

        try {
            if (!dao.exists(id)) { // Verifica se o board existe
                return false; // Retorna false se o board não existir
            }

            dao.delete(id); // Deleta o board
            connection.commit();
            return true; // Retorna true indicando sucesso
        } catch (SQLException e) {
            connection.rollback(); // Desfaz a transação em caso de erro
            throw e;
        }
    }
}
