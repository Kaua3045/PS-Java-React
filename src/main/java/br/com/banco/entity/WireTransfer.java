package br.com.banco.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WireTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long wireTransferId;

    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime wireTransferDate;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @Column(name = "tipo", nullable = false)
    private String type;

    @Column(name = "nome_operador_transacao")
    private String transactionOperatorName;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Account account;
}
