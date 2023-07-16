package br.com.banco.repository;

import br.com.banco.entity.Account;
import br.com.banco.entity.WireTransfer;
import br.com.banco.repository.AccountRepository;
import br.com.banco.repository.WireTransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void givenAnValidValues_whenCallsRepositorySave_shouldReturnAccount() {
        final var account = Account.builder()
                .accountId(1L)
                .accoutName("Kaua")
                .build();
        final var result = accountRepository.save(account);

        Assertions.assertEquals(result, account);
    }

    @Test
    public void givenAnValidId_whenCallsRepositoryFindById_shouldReturnAccount() {
        final var account = Account.builder()
                .accountId(1L)
                .accoutName("Kaua")
                .build();
        accountRepository.save(account);

        final var result = accountRepository.findById(account.getAccountId());

        Assertions.assertEquals(result.get(), account);
    }
}
