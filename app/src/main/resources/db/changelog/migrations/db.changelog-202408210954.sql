CREATE TABLE BOARDS_COLUMNS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- ID único gerado automaticamente.
    name VARCHAR(255) NOT NULL,           -- Nome da coluna (obrigatório).
    `order` int NOT NULL,                 -- Ordem da coluna no board (obrigatório).
    kind VARCHAR(7) NOT NULL,
    board_id BIGINT NOT NULL,

    -- vincula board_id à tabela BOARDS. CASCADE deleta colunas se o board for removido.
    CONSTRAINT boards__boards_columns_fk FOREIGN KEY (board_id) REFERENCES BOARDS(id) ON DELETE CASCADE,

    -- garante que a ordem das colunas seja única por board.
    CONSTRAINT id_order_uk UNIQUE KEY unique_board_id_order (board_id, `order`)
) ENGINE=InnoDB;  -- Usa InnoDB para suportar transações e chaves estrangeiras.
