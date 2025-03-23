CREATE TABLE BLOCKS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data/hora do bloqueio (automática).
    block_reason VARCHAR(255) NOT NULL,   -- Motivo do bloqueio
    unblocked_at TIMESTAMP NULL,         -- Data/hora do desbloqueio
    unblock_reason VARCHAR(255) NOT NULL, -- Motivo do desbloqueio
    card_id BIGINT NOT NULL,              -- ID do card bloqueado.

    CONSTRAINT cards__blocks_fk FOREIGN KEY (card_id) REFERENCES CARDS(id) ON DELETE CASCADE
) ENGINE=InnoDB;
