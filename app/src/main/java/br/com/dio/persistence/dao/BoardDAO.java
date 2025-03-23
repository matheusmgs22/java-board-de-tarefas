package br.com.dio.persistence.dao;

import br.com.dio.persistence.entity.BoardEntity;
import com.mysql.cj.jdbc.StatementImpl;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardDAO {

    private Connection connection;

    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        var sql = "INSERT INTO BOARDS (name) VALUES (?);";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName()); // Define o nome do board
            statement.executeUpdate(); // Executa a inserção no banco

            // Obtém o ID gerado automaticamente pelo banco de dados
            if (statement instanceof StatementImpl impl) {
                entity.setId(impl.getLastInsertID());
            }
        }
        return entity;
    }

    public void delete(final Long id) throws SQLException {
        var sql = "DELETE FROM BOARDS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id); // Define o ID do board a ser deletado
            statement.executeUpdate(); // Executa a exclusão no banco
        }
    }

    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        var sql = "SELECT id, name FROM BOARDS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id); // Define o ID do board a ser buscado
            statement.executeQuery(); // Executa a consulta

            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                var entity = new BoardEntity();
                entity.setId(resultSet.getLong("id")); // Obtém o ID do board
                entity.setName(resultSet.getString("name")); // Obtém o nome do board
                return Optional.of(entity);
            }
            return Optional.empty(); // Retorna vazio se não encontrar
        }
    }

    public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM BOARDS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id); // Define o ID do board a ser verificado
            statement.executeQuery(); // Executa a consulta
            return statement.getResultSet().next(); // Retorna true se houver resultado
        }
    }

}
