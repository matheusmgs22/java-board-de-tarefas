package br.com.dio.service;

import br.com.dio.dto.BoardDetailsDTO;
import br.com.dio.persistence.dao.BoardColumnDAO;
import br.com.dio.persistence.dao.BoardDAO;
import br.com.dio.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardQueryService {

    private final Connection connection;

    // Busca um board pelo ID e carrega suas colunas associadas.
    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        var dao = new BoardDAO(connection); // DAO para acessar a tabela de boards.
        var boardColumnDAO = new BoardColumnDAO(connection); // DAO para acessar a tabela de colunas.
        var optional = dao.findById(id); // Busca o board pelo ID.

        if (optional.isPresent()) {
            var entity = optional.get();
            // Carrega as colunas associadas ao board e as define na entidade.
            entity.setBoardColumns(boardColumnDAO.findByBoardId(entity.getId()));
            return Optional.of(entity); // Retorna o board com as colunas carregadas.
        }
        return Optional.empty(); // Retorna vazio se o board não for encontrado.
    }

    // Busca detalhes de um board, incluindo suas colunas e informações adicionais.
    public Optional<BoardDetailsDTO> showBoardDetails(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findById(id);

        if (optional.isPresent()) {
            var entity = optional.get();
            // Busca as colunas com detalhes adicionais associadas ao board.
            var columns = boardColumnDAO.findByBoardIdWithDetails(entity.getId());
            // Cria um DTO com os detalhes do board e suas colunas.
            var dto = new BoardDetailsDTO(entity.getId(), entity.getName(), columns);
            return Optional.of(dto); // Retorna o DTO com os detalhes.
        }
        return Optional.empty();
    }
}
