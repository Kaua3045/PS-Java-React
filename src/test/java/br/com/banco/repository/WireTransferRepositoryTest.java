package br.com.banco.repository;

import br.com.banco.entity.Account;
import br.com.banco.entity.WireTransfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
public class WireTransferRepositoryTest {

    @Autowired
    private WireTransferRepository wireTransferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void givenAnValidValues_whenCallsRepositorySave_shouldReturnWireTransfer() {
        final var account = Account.builder()
                .accountId(1L)
                .accoutName("Kaua")
                .build();

        final var wireTransferId = 1L;
        final var wireTransferDate = LocalDateTime.now();
        final var value = BigDecimal.valueOf(100.000);
        final var type = "DEPOSITO";
        final var transactionOperatorName = "Kaua";

        accountRepository.save(account);

        WireTransfer wireTransfer = WireTransfer.builder()
                .wireTransferId(wireTransferId)
                .wireTransferDate(wireTransferDate)
                .value(value)
                .type(type)
                .transactionOperatorName(transactionOperatorName)
                .account(account)
                .build();

        final var result = wireTransferRepository.save(wireTransfer);

        Assertions.assertEquals(result, wireTransfer);
    }

    @Test
    public void givenAnValidId_whenCallsRepositoryFindById_shouldReturnWireTransfer() {
        final var account = Account.builder()
                .accountId(1L)
                .accoutName("Kaua")
                .build();

        final var wireTransferId = 1L;
        final var wireTransferDate = LocalDateTime.now();
        final var value = BigDecimal.valueOf(100.000);
        final var type = "DEPOSITO";
        final var transactionOperatorName = "Kaua";

        accountRepository.save(account);

        WireTransfer wireTransfer = WireTransfer.builder()
                .wireTransferId(wireTransferId)
                .wireTransferDate(wireTransferDate)
                .value(value)
                .type(type)
                .transactionOperatorName(transactionOperatorName)
                .account(account)
                .build();

        wireTransferRepository.save(wireTransfer);

        final var result = wireTransferRepository.findById(wireTransfer.getWireTransferId());

        Assertions.assertEquals(result.get(), wireTransfer);
    }
}
