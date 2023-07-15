package br.com.banco.controller;

import br.com.banco.entity.Account;
import br.com.banco.entity.WireTransfer;
import br.com.banco.service.WireTransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WireTransferControllerTest {

    @Mock
    private WireTransferService wireTransferService;

    @InjectMocks
    private WireTransferController wireTransferController;

    @Test
    public void givenAValidStartDateAndEndDateAndName_shouldReturnWireTransfers() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);
        String name = "John doe";
        Account account = Account
                .builder()
                .accoutName(name)
                .accountId(1L)
                .build();

        List<WireTransfer> wireTransfers = new ArrayList<>();
        wireTransfers.add(WireTransfer
                .builder()
                        .wireTransferId(1L)
                        .wireTransferDate(LocalDateTime.now())
                        .transactionOperatorName(name)
                        .value(new BigDecimal("10.000000"))
                        .type("DEPOSITO")
                        .account(account)
                .build());

        Mockito.when(wireTransferService.listAllWireTransfer(startDate, endDate, name))
                .thenReturn(wireTransfers);

        ResponseEntity<List<WireTransfer>> result = wireTransferController.findAllWireTransfers(
                startDate, endDate, name
        );

        System.out.println(result);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(wireTransfers, result.getBody());
        Mockito.verify(wireTransferService, Mockito.times(1)).listAllWireTransfer(
                startDate,
                endDate,
                name
        );
    }

    @Test
    public void givenAValidStartDateAndEndDate_shouldReturnWireTransfers() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);
        Account account = Account
                .builder()
                .accoutName("John doe")
                .accountId(1L)
                .build();

        List<WireTransfer> wireTransfers = new ArrayList<>();
        wireTransfers.add(WireTransfer
                .builder()
                .wireTransferId(1L)
                .wireTransferDate(LocalDateTime.now())
                .value(new BigDecimal("200.000000"))
                .type("DEPOSITO")
                .transactionOperatorName(account.getAccoutName())
                .account(account)
                .build());
        wireTransfers.add(WireTransfer
                .builder()
                .wireTransferId(2L)
                .wireTransferDate(LocalDateTime.now())
                .value(new BigDecimal("100.000000"))
                .type("SAQUE")
                .transactionOperatorName(account.getAccoutName())
                .account(account)
                .build());

        Mockito.when(wireTransferService.listAllWireTransfer(startDate, endDate, null))
                .thenReturn(wireTransfers);

        ResponseEntity<List<WireTransfer>> result = wireTransferController.findAllWireTransfers(
                startDate, endDate, null
        );

        System.out.println(result);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(wireTransfers, result.getBody());
        Mockito.verify(wireTransferService, Mockito.times(1)).listAllWireTransfer(
                startDate,
                endDate,
                null
        );
    }

    @Test
    public void givenAValidStartDate_shouldReturnWireTransfers() {
        LocalDate startDate = LocalDate.now();
        Account account = Account
                .builder()
                .accoutName("John doe")
                .accountId(1L)
                .build();

        List<WireTransfer> wireTransfers = new ArrayList<>();
        wireTransfers.add(WireTransfer
                .builder()
                .wireTransferId(1L)
                .wireTransferDate(LocalDateTime.now())
                .value(new BigDecimal("10.000000"))
                .type("TRANSFERENCIA")
                .transactionOperatorName(account.getAccoutName())
                .account(account)
                .build());

        Mockito.when(wireTransferService.listAllWireTransfer(startDate, null, null))
                .thenReturn(wireTransfers);

        ResponseEntity<List<WireTransfer>> result = wireTransferController.findAllWireTransfers(
                startDate, null, null
        );

        System.out.println(result);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(wireTransfers, result.getBody());
        Mockito.verify(wireTransferService, Mockito.times(1)).listAllWireTransfer(
                startDate,
                null,
                null
        );
    }

    @Test
    public void givenAValidEndDate_shouldReturnWireTransfers() {
        LocalDate endDate = LocalDate.now().plusDays(1);
        Account account = Account
                .builder()
                .accoutName("John doe")
                .accountId(1L)
                .build();

        List<WireTransfer> wireTransfers = new ArrayList<>();
        wireTransfers.add(WireTransfer
                .builder()
                .wireTransferId(1L)
                .wireTransferDate(LocalDateTime.now())
                .value(new BigDecimal("10.000000"))
                .type("DEPOSITO")
                .transactionOperatorName(account.getAccoutName())
                .account(account)
                .build());

        Mockito.when(wireTransferService.listAllWireTransfer(null, endDate, null))
                .thenReturn(wireTransfers);

        ResponseEntity<List<WireTransfer>> result = wireTransferController.findAllWireTransfers(
                null, endDate, null
        );

        System.out.println(result);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(wireTransfers, result.getBody());
        Mockito.verify(wireTransferService, Mockito.times(1)).listAllWireTransfer(
                null,
                endDate,
                null
        );
    }
}
