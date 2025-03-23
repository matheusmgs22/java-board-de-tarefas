package br.com.dio.persistence.entity;

import lombok.Data;
import java.time.OffsetDateTime;

@Data // Gera automaticamente getters, setters, equals, hashCode e toString
public class BlockEntity {

    private Long id; // Identificador único do bloqueio
    private OffsetDateTime blockedAt; // Data e hora em que o card foi bloqueado
    private String blockReason; // Motivo do bloqueio do card
    private OffsetDateTime unblockedAt; // Data e hora em que o card foi desbloqueado
    private String unblockReason; // Motivo do desbloqueio do card

}
