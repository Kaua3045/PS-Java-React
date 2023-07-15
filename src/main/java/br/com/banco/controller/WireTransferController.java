package br.com.banco.controller;

import br.com.banco.entity.WireTransfer;
import br.com.banco.service.WireTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transfers")
public class WireTransferController {

    @Autowired
    private WireTransferService wireTransferService;

    @GetMapping
    public ResponseEntity<List<WireTransfer>> findAllWireTransfers(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(required = false) String operatorName
    ) {
        return ResponseEntity.ok(wireTransferService.listAllWireTransfer(
                startDate,
                endDate,
                operatorName
        ));
    }
}
